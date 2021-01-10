package com.sam.e4.minimark.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

/**
 * 네이처의 목적은 프로젝트(.proejct)에 자동으로 빌더를 추가해주는 것이다. 우리가 프로젝트의 어떤 설정을 하면(예를 들어 이
 * 프로젝트는 플러그인 프로젝트, 이 프로젝트는 메이븐 프로젝트 등)프로젝트의 설정 파일이 자동으로 변경되는 것과 같은 역할이다.
 * 빌더를 추가할때 연관되는 것이 ID이다. 상호참조가 가능하게 하기 위해 minimarkBuilder에도 ID상수를 적용한다.
 * @author dololgun
 *
 */
public class MinimarkNature implements IProjectNature {

	public static final String ID = "com.sam.e4.minimark.ui.MinimarkNature";

	private IProject project;

	/**
	 * 프로젝트 디스크립터에 접근하여 자동으로 buildeCommand를 설정하는 기능을 수행한다.
	 */
	@Override
	public void configure() throws CoreException {
		// 
		IProjectDescription desc = project.getDescription();
		
		// .proejct 파일에 정의된 빌드 커맨드를 얻는다.
		List<ICommand> commands = new ArrayList<ICommand>(Arrays.asList(desc.getBuildSpec()));
		
		Iterator<ICommand> iterator = commands.iterator();
		while (iterator.hasNext()) {
			ICommand command = iterator.next();
			// 프로젝트 디스크립터에 minimarkBuilder가 설정되어있는지 확인힌다.
			if (MinimarkBuilder.ID.equals(command.getBuilderName())) {
				// 이미 있다면 추가할 필요 없음.
				return;
			}
		}
		
		// 새로운 커맨드를 생성한다.
		ICommand newCommand = desc.newCommand();

		newCommand.setBuilderName(MinimarkBuilder.ID);
		commands.add(newCommand);
		// 커맨드를 빌드 스펙에 적용한다. 제네릭을 이용하기 때문에 타입을 맞추기 위해 new ICommand[0] 한다.
		desc.setBuildSpec(commands.toArray(new ICommand[0]));
		project.setDescription(desc, null);
		
	}

	/**
	 * 프로젝트 디스크립터에 접근하여 자동으로 buildCommand를 삭제하는 기능을 수행한다.
	 */
	@Override
	public void deconfigure() throws CoreException {
		// 
		IProjectDescription desc = project.getDescription();
		
		// .proejct 파일에 정의된 빌드 커맨드를 얻는다.
		List<ICommand> commands = new ArrayList<ICommand>(Arrays.asList(desc.getBuildSpec()));
		
		Iterator<ICommand> iterator = commands.iterator();
		while (iterator.hasNext()) {
			ICommand command = iterator.next();
			// 프로젝트 디스크립터에 minimarkBuilder가 설정되어있는지 확인힌다.
			if (MinimarkBuilder.ID.equals(command.getBuilderName())) {
				// 이미 있다면 삭제해야한다.
				iterator.remove();
			}
		}
		// 커맨드를 빌드 스펙에 적용한다. 제네릭을 이용하기 때문에 타입을 맞추기 위해 new ICommand[0] 한다.
		desc.setBuildSpec(commands.toArray(new ICommand[0]));
		project.setDescription(desc, null);
	}

	@Override
	public IProject getProject() {

		return project;
	}

	@Override
	public void setProject(IProject project) {

		this.project = project;
	}

}
