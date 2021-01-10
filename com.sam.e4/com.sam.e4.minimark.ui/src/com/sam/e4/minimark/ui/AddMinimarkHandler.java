package com.sam.e4.minimark.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;


/**
 * 네이처를 추가하기 위한 핸들러. 
 * <p>
 * 핸들러는 커맨드와 항상 같이 다닌다.
 * @author dololgun
 *
 */
public class AddMinimarkHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// 
		ISelection sel = HandlerUtil.getCurrentSelection(event);
		if (sel instanceof IStructuredSelection) {
			Iterator<?> it = ((IStructuredSelection) sel).iterator();
			while (it.hasNext()) {
				
				Object object = it.next();
				if (object instanceof IProject) {
					try {
						addProjectNature((IProject) object, MinimarkNature.ID);
					} catch (CoreException e) {
						throw new ExecutionException("Failed to set nature on" + object, e);
					}
				}
			}
		}
		return null;
	}
	
	private void addProjectNature(IProject project, String natureId) throws CoreException {
		IProjectDescription desc = project.getDescription();
		List<String> natures = new ArrayList<String>(Arrays.asList(desc.getNatureIds()));
		if (!natures.contains(natureId)) {
			natures.add(natureId);
			desc.setNatureIds(natures.toArray(new String[0]));
			project.setDescription(desc, null);
			
		}
	}

}
