package com.sam.e4.application.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * .e4xmi파일을 전용뷰어로 보기 위해서 e4 tool 설치가 필요하다.
 * 
 * <p>
 * (http://download.eclipse.org/releases/latest)
 * 
 * <p>
 * general purpose tools > Eclipse e4 Tools Developer resources
 * 
 * <p>
 * 구동과정 이클립스 어플리케이션은 런타임에서 org.eclipse.e4.ui.workbench.swt.E4Application과 함께
 * 구동된다. E4Applicatino 클래스는 xmi파일의 프로퍼티를 읽는다.
 * 
 * @author dololgun
 *
 */
public class Hello {

	private Label label;

	/**
	 * e4 개발은 스프링 프레임워크 기반의 개발과 비슷한 환경을 제공한다. postConstuct 어노테이션은 객체가 생성된 이후 프레임워크에서
	 * 자동으로 해당 메소드를 호출한다. 이런 효과를 내기 위해 별도의 인터페이스를 확장할 필요가 없는 효과를 준다.
	 */
	@PostConstruct
	public void create(Composite parent) {

		label = new Label(parent, SWT.NONE);
		label.setText("Hello");
	}

	@Focus
	public void focus() {

		label.setFocus();
	}
}
