package com.sam.e4.minimark.ui;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author leegeonho
 * 소스가 변경되면 이클립스는 프레임워크는 빌드 이벤트를 발생한다.
 * 이 빌드 이벤트를 받기 위해  IncrementalProjectBuilder를 상속받은 builder를 만든다. 
 * IncrementalProjectBuilder를 추가하기 위해서는 org.eclipse.core.resources를 디펜던시에 추가해야 한다.
 * builder를 hook up하기 위해서 org.eclipse.core.resources.builders를 extension point로 추가해야 한다.
 *
 */
public class MinimarkBuilder extends IncrementalProjectBuilder {

	@Override 
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		// 
		if (kind == FULL_BUILD) {
			fullBuild(getProject(), monitor);
		} else {
			incrementalBuild(getProject(), monitor, getDelta(getProject()));
		}
		return null;
	}
	
	private void incrementalBuild(IProject project, IProgressMonitor monitor, IResourceDelta delta) throws CoreException {
		if (delta == null) {
			
		} else {
			System.out.println("Doing an incremental build");
		}
	}
	
	private void fullBuild(IProject project, IProgressMonitor monitor) throws CoreException {
		System.out.println("Doing a full build");
	}

}
