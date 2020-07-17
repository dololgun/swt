package com.sam.e4.minimark.ui;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * <p>
 * 소스가 변경되면 이클립스는 프레임워크는 빌드 이벤트를 발생한다. 이 빌드 이벤트를 받기 위해
 * IncrementalProjectBuilder를 상속받은 builder를 만든다. IncrementalProjectBuilder를 추가하기
 * 위해서는 org.eclipse.core.resources를 디펜던시에 추가해야 한다. builder를 hook up하기 위해서
 * org.eclipse.core.resources.builders를 extension point로 추가해야 한다.
 * 
 * <P>
 * incrementalBuilder는 file delete에 대해서 동작하지 않는다. file delete를 감지하기 위해 delta의
 * 체크하여 파일 변경의 종류를 찾아야 한다. 삭제애 대한 처리를 하지 않으면 파일 삭제시 오류가 발생한다.
 * 
 * @author leegeonho
 */
public class MinimarkBuilder extends IncrementalProjectBuilder {
	
	// 네이처와 상호작용하기 위해 ID상수를 추가한다.
	public static final String ID = "com.sam.e4.minimark.ui.MinimarkBuilder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		//
		if (kind == FULL_BUILD) {
			// 전체파일을 빌드한다.
			fullBuild(getProject(), monitor);
		} else {
			// 변경된 파일만 빌드한다.
			incrementalBuild(getProject(), monitor, getDelta(getProject()));
		}
		return null;
	}

	private void incrementalBuild(IProject project, IProgressMonitor monitor, IResourceDelta delta)
			throws CoreException {
		if (delta == null) {
			fullBuild(project, monitor);
		} else {
//			System.out.println("Doing an incremental build");
			delta.accept(new MinimarkVisitor());
		}
	}

	private void fullBuild(IProject project, IProgressMonitor monitor) throws CoreException {
//		System.out.println("Doing a full build");
		project.accept(new MinimarkVisitor(), IResource.NONE);
	}

	/**
	 * 프로젝트를 clean할때 원본 소스와 연관된 파일은 삭제되어야 한다.
	 */
	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		super.clean(monitor);
		deleteAll(getProject(), monitor);
	}
	
	private void deleteAll(IProject project, IProgressMonitor monitor) throws CoreException {
//		TODO ???
//		project.accept(new MinimarkVisitor(), IResource.NONE);
	}

}
