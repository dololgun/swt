package com.sam.e4.minimark.ui;

import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.texteditor.AbstractTextEditor;

public class MinimarkEditor extends AbstractTextEditor {
	public MinimarkEditor() {
		// documentProvider를 지정해야 이클립스에서 파일을 열때 이 에디터가 연결되어 실행된다.
		setDocumentProvider(new TextFileDocumentProvider());

	}

}
