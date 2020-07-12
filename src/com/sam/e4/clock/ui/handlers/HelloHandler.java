package com.sam.e4.clock.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressConstants2;
import org.eclipse.ui.statushandlers.StatusManager;

import com.sam.e4.clock.ui.Activator;

public class HelloHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		/*
		 * 오래 걸리는 작업은 화면을 block함으로 job을 생성하여 비동기적으로 수행되게 한다. Job은 non ui 백그라운드 스레드로
		 * 동작한다.
		 */
		Job job = new Job("About to Say hello") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {

				try {
					// 아래 로직은 subMonitor를 사용하면서 필요가 없어진다.
					// monitor.beginTask("Preparing", 5000);
					SubMonitor subMonitor = SubMonitor.convert(monitor, "Preparing", 5000);
					subMonitor = null;

					/*
					 * progress bar가 동작하는 동안 사용자가 cancel 버튼을 클릭하면 동작하지 않도록 한다.
					 */
					for (int i = 0; i < 5 && !subMonitor.isCanceled(); i++) {

						if (i <= 1) {
							subMonitor.subTask("1초까지 진행되는 일");
						} else if (i <= 3) {
							subMonitor.subTask("3초까지 진행되는 일");
						} else {
							subMonitor.subTask("마지막에 진행되는 일");
						}

						if (i == 1) {

							// SubProgressMonitor는 deprecated 되었다. 대신에 subMonitor를 사용한다.
							// checkDozen(new SubProgressMonitor(monitor, 1000));
							checkDozen(subMonitor.newChild(1000));

							// conitnue함으로써 아래 worked 1000
							continue;
						}

						Thread.sleep(1000);
						subMonitor.worked(1000);
					}

				} catch (InterruptedException e) {
				} catch (NullPointerException e) {
					// status를 변경하는 방법
					Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Programaming bug?", e);
					// return status;
					
					// statusmanager를 이용하여 log를 남기는 방법
					StatusManager statusManager = StatusManager.getManager();
					statusManager.handle(status, StatusManager.LOG | StatusManager.SHOW);
					
				} finally {
					if (monitor != null) {
						monitor.done();
					}
				}

				if (!monitor.isCanceled()) {
					/*
					 * 일반 스레드에서 UI스레드에 접근하면 오류가 발생하기 때문에 다음 처럼 UI스레드를 통하여 접근해야 한다.
					 */
					Display.getDefault().asyncExec(() -> {
						MessageDialog.openInformation(null, "hello", "world");
					});
				}
				return Status.OK_STATUS;
			}
		};

		/*
		 * job property를 사용하는 부분
		 */
		ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command = (service == null) ? null : service.getCommand("com.sam.e4.clock.ui.command.hello");
		if (command != null) {
			job.setProperty(IProgressConstants2.COMMAND_PROPERTY, ParameterizedCommand.generateCommand(command, null));
		}
		job.setProperty(IProgressConstants2.ICON_PROPERTY,
				ImageDescriptor.createFromURL(HelloHandler.class.getResource("/icons/sample.png")));

		job.schedule();
		return null;
	}

	private void checkDozen(IProgressMonitor monitor) {

		// 모니터가 null 인경우 에 대비할 때 좀더 elegant 하게 사용하기 위해 nullProgressMonitor를 사용한다.
		// 이 장치는 다른 로직은 변경없이 null에 대해 안전한 로직이 되도록 한다.
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}

		try {
			monitor.beginTask("Checking a dozen", 12);
			for (int i = 0; i < 12; i++) {
				Thread.sleep(100);
				monitor.worked(1);
			}
		} catch (InterruptedException e) {

		} finally {
			monitor.done();
		}
	}

}
