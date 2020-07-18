package com.sam.e4.minimark.ui;

import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.texteditor.AbstractTextEditor;

/**
 * https://download.eclipse.org/e4/snapshots/org.eclipse.e4.tools/latest/
 * http://download.eclipse.org/releases/latest
 * marker의 오류가 있으면 editor에서도 보여지도록 하기 위해 {@link AbstractTextEditor}
 * {@link AbstractDecoratedTextEditor} 변경한다.
 * 
 * @author jh
 *
 */
public class MinimarkEditor extends AbstractDecoratedTextEditor {
	public MinimarkEditor() {
		// documentProvider를 지정해야 이클립스에서 파일을 열때 이 에디터가 연결되어 실행된다.
		setDocumentProvider(new TextFileDocumentProvider());

	}

}
