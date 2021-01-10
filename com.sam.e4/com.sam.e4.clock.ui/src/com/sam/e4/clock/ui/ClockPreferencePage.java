package com.sam.e4.clock.ui;

import java.util.Arrays;
import java.util.TimeZone;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.ScaleFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ClockPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ClockPreferencePage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		// 필드 에티터를 추가한다.
		addField(new IntegerFieldEditor("launchCount", "Number of time it has been launched", getFieldEditorParent()));

		// 값의 범위 지정
		IntegerFieldEditor offset = new IntegerFieldEditor("offset", "current offset from GMT", getFieldEditorParent());
		offset.setValidRange(-14, +12);
		addField(offset);

		// 콤보박스
		String[][] data;
		String[] ids = TimeZone.getAvailableIDs();
		Arrays.sort(ids);
		data = new String[ids.length][];
		for (int i = 0; i < ids.length; i++) {
			data[i] = new String[] { ids[i], ids[i] };
		}
		addField(new ComboFieldEditor("favorite", "favorite time zone", data, getFieldEditorParent()));
		addField(new BooleanFieldEditor("tick", "boolean value", getFieldEditorParent()));
		addField(new ColorFieldEditor("color", "color value", getFieldEditorParent()));
		addField(new ScaleFieldEditor("scale", "scale", getFieldEditorParent(), 0, 360, 10, 90));
		addField(new FileFieldEditor("file", "pick a file", getFieldEditorParent()));
		addField(new DirectoryFieldEditor("dir", "pick a directory", getFieldEditorParent()));
		addField(new PathEditor("path", "path", "directory", getFieldEditorParent()));
		addField(new RadioGroupFieldEditor("group", "Radio choices", 3, data, getFieldEditorParent(), true));
	}

	@Override
	public void init(IWorkbench arg0) {
		// FieldEditorPreferencePage를 알맞은 저장소와 연결해야 한다.
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

}