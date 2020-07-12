package com.sam.e4.clock.ui.views;

import java.net.URL;
import java.util.TimeZone;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySource;

import com.sam.e4.clock.ui.internal.TimeZoneComparator;
import com.sam.e4.clock.ui.internal.TimeZoneDialog;
import com.sam.e4.clock.ui.internal.TimeZoneSelectionListener;
import com.sam.e4.clock.ui.internal.TimeZoneViewerComparator;
import com.sam.e4.clock.ui.internal.TimeZoneViewerFilter;

public class TimeZoneTreeView extends ViewPart {

	// jface 에서 제공하는 트리뷰어를 사용한다.
	private TreeViewer treeViewer;
	private TimeZoneSelectionListener selectionListener;

	@Override
	public void createPartControl(Composite parent) {
		
		ResourceManager rm = JFaceResources.getResources();
		LocalResourceManager lrm = new LocalResourceManager(rm, parent);
		ImageRegistry ir = new ImageRegistry(lrm);

		URL sample = getClass().getResource("/icons/sample.png");
		ir.put("sample", ImageDescriptor.createFromURL(sample));

		FontRegistry fr = JFaceResources.getFontRegistry();

		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new TimeZoneLabelProvider(ir, fr)));
		treeViewer.setContentProvider(new TimeZoneContentProvider());
		treeViewer.setInput(new Object[] { TimeZoneComparator.getTimeZones() });
		treeViewer.setData("REVERSE", Boolean.TRUE);
		treeViewer.setComparator(new TimeZoneViewerComparator());
		treeViewer.setFilters(new ViewerFilter[] { new TimeZoneViewerFilter("GMT") });
		treeViewer.setExpandPreCheckFilters(true);

		treeViewer.addDoubleClickListener(e -> {
			Viewer viewer = e.getViewer();
			Shell shell = viewer.getControl().getShell();

			// 아래 메세지로 더블클릭 이벤트를 감지할 수 있다.
//			MessageDialog.openInformation(shell, "Double click", "Double click detected");

			ISelection sel = viewer.getSelection();
			Object selectedValue = null;
			if ((sel instanceof StructuredSelection) && !sel.isEmpty()) {
				StructuredSelection structuredSelection = (StructuredSelection) sel;
				selectedValue = structuredSelection.getFirstElement();
			}
			
			if (selectedValue instanceof TimeZone) {
				TimeZone timeZone = (TimeZone)selectedValue;
//				MessageDialog.openInformation(shell, timeZone.getID(), timeZone.toString());
				new TimeZoneDialog(shell, timeZone).open();
			}

		});
		
		System.out.println("Adapter is " + Platform.getAdapterManager().getAdapter(TimeZone.getDefault(), IPropertySource.class));		
		getSite().setSelectionProvider(treeViewer);
		
		// 셀력션 리스너 생성
		selectionListener = new TimeZoneSelectionListener(treeViewer, getSite().getPart());
		
		// 샐력션 리스너 워크벤치.셀렉션 서비스에 등록
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
	}

	@Override
	public void setFocus() {

		treeViewer.getControl().setFocus();
	}

	@Override
	public void dispose() {

		// 셀렉션 리스너 삭제
		if (selectionListener != null) {
			getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			selectionListener = null;
		}
		super.dispose();
	}

}
