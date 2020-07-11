package com.sam.e4.clock.ui.views;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class TimeZoneLabelProvider extends LabelProvider implements IStyledLabelProvider, IFontProvider {
	
	private final ImageRegistry ir;
	private final FontRegistry fr;

	public TimeZoneLabelProvider(ImageRegistry ir, FontRegistry fr) {

		this.ir = ir;
		this.fr = fr;
	}

	@Override
	public Image getImage(Object element) {

		if (element instanceof Entry<?, ?>) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		} else if (element instanceof TimeZone) {
			return ir.get("sample");
		} else {
			return super.getImage(element);
		}
	}

	@Override
	public String getText(Object element) {

		if (element instanceof Map<?, ?>) {
			return "Time Zone Map";
		} else if (element instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (Entry<?, ?>) element;
			return entry.getKey().toString();
		} else if (element instanceof TimeZone) {
			TimeZone timeZone = (TimeZone) element;
			return timeZone.getID().split("/")[1];
		} else {
			return "Unknown type: " + element.getClass();
		}
	}

	@Override
	public StyledString getStyledText(Object element) {
		
		String text = getText(element);
		StyledString ss = new StyledString(text);
		if (element instanceof TimeZone) {
			TimeZone timeZone = (TimeZone)element;
			int offset = - timeZone.getOffset(0);
			ss.append(" (" + offset / 3600000 + "h)", StyledString.DECORATIONS_STYLER);
		} 
		return ss;
	}

	@Override
	public Font getFont(Object element) {
		Font italic = fr.getItalic(JFaceResources.DEFAULT_FONT);
		return italic;
	}
}
