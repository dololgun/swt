package com.sam.e4.minimark.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

public class MinimarkVisitor implements IResourceProxyVisitor, IResourceDeltaVisitor {

	/**
	 * 프레임웍에서 파일들을 방문할 때 발생하는 이벤트
	 */
	@Override
	public boolean visit(IResourceProxy proxy) throws CoreException {
		//
		String name = proxy.getName();
		if (name != null && name.endsWith(".minimark")) {
//			System.out.println("Processing " + name);
			processResource(proxy.requestResource());
		}

		// 자식 리소스를 계속 방문하기 위해 true를 리턴한다.
		return true;
	}

	/**
	 * 델타를 통해 변경된 파일만 빌드하는 과정
	 */
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		
		// 삭제 이벤트인 경우
		boolean deleted = (IResourceDelta.REMOVED & delta.getKind()) != 0;
		
		// 델타로부터 리소를 얻는다. 
		IResource resource = delta.getResource();
		String name = resource.getName(); 
		if (name.endsWith(".minimark")) {
			System.out.println("incremental build");
			if (deleted) {
				String htmlName = name.replace(".minimark", ".html");
				IFile htmlFile = resource.getParent().getFile(new Path(htmlName));
				if (htmlFile.exists()) {
					htmlFile.delete(true, null);
				}
			} else {
				processResource(resource);
			}
		}
		
		// html 파일을 삭제한 경우 이에 대응하는 .minimark 파일이 존재한다면 html이 다시 생성되어야 한다.
		// html 을 사용자가 강제로 수정하는 경우도 마찬가지이다.
		if (name.endsWith(".html")) {
			String minimarkName = name.replace(".html", ".minimark");
			IFile minimarkFile = resource.getParent().getFile(new Path(minimarkName));
			if (minimarkFile.exists()) {
				processResource(minimarkFile);
			}
		}
		return true;
	}

	private void processResource(IResource resource) throws CoreException {
		
		/*
		 * 프로젝트와 연관된 모든 마커를 삭제한다.
		 */
		resource.deleteMarkers("com.sam.e4.minimark.ui.MinimarkMarker", true, IResource.DEPTH_INFINITE);

		/*
		 *  파일 삭제 이벤트가 발생할 경우에 대비하여 리소스의 존재여부를 체크하여 nullpointerException을 방지한다
		 *  이렇게 하여도 이미 생성된 html파일은 자동으로 삭제 되지 않는다. 
		 *  
		 */
		if (resource instanceof IFile && resource.exists()) {
			IFile file = (IFile) resource;

			// 생성할 파일의 이름을 정하자
			System.out.println("file.getName() = " + file.getName());
			String htmlName = file.getName().replace(".minimark", ".html");

			// 원본파일로 부터 컨테이너를 얻는다.
			IContainer container = file.getParent();

			// 컨테이너로 부터 새로 생성할 파일을 얻는다.
			IFile htmlFile = container.getFile(new Path(htmlName));
			InputStream in = file.getContents();
			try {
//				MinimarkTranslator.convert(new InputStreamReader(in), new OutputStreamWriter(System.out));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				MinimarkTranslator.convert(new InputStreamReader(in), new OutputStreamWriter(baos));

				ByteArrayInputStream contents = new ByteArrayInputStream(baos.toByteArray());
				
				if (baos.size() < 100) {
					System.out.println("Minimark file is emtpy");
//					IMarker marker = resource.createMarker(IMarker.PROBLEM);
					// maker를 사용하기 위해 익스텐션에 설정한 마커의 ID를 추가한다.
					IMarker marker = resource.createMarker("com.sam.e4.minimark.ui.MinimarkMarker");
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					marker.setAttribute(IMarker.MESSAGE, "Minimark file is empty");
					marker.setAttribute(IMarker.LINE_NUMBER, 0);
					marker.setAttribute(IMarker.CHAR_START, 0);
					marker.setAttribute(IMarker.CHAR_END, 0);
				}

				if (htmlFile.exists()) {
					htmlFile.setContents(contents, true, false, null);
				} else {
					htmlFile.create(contents, true, null);
				}
				
				// 이클립스에게 htmlFile이 도출됨을 알린다.
				// 사용자가 편집한 것이 아니고 자동으로 생성된 것이라는 의미 
				htmlFile.setDerived(true, null);
				
			} catch (IOException e) {
				throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, "Fail to generate resource", e));
			}
		}
	}

}
