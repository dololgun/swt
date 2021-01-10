package com.sam.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.service.log.LogService;

import com.sam.e4.application.RandomFuntion;

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

	// log를 남기도록 해주는 osgi 제공 서비스
	// e4 runtime infrastructure do DI
	@Inject
	private LogService log;

	// window객체 얻기
	// window객체도 injection을 통하여 얻을 수 있다.
	// 하지만, e4에서는 GUI객체를 직접 참조하기보다 model을 통하여 접근하는 것을 추천한다.
	// 예를 들어, MPart, MWindow, MPerspetive는 GUI객체에 접근하기 위한 모델이다.
	@Inject
	private MWindow window;
	
	//  
	// 
	/**
	 * 컨텍스트 펑션 서비스를 e4런타임에 등록하고 DI받아 사용할 수 있다.
	 * {@link RandomFuntion}를 참조한다. 
	 * 이 서비스를 등록하기 위해 random.xml을 생성하였고 MANIFEST.MF 에 서비스로 추가하였다.
	 * @param parent
	 */
	@Inject
	@Named("math.random")
	private Object random;
	

	/**
	 * e4 개발은 스프링 프레임워크 기반의 개발과 비슷한 환경을 제공한다. postConstuct 어노테이션은 객체가 생성된 이후 프레임워크에서
	 * 자동으로 해당 메소드를 호출한다. 이런 효과를 내기 위해 별도의 인터페이스를 확장할 필요가 없는 효과를 준다.
	 */
	@PostConstruct
	public void create(Composite parent) {

		label = new Label(parent, SWT.NONE);
		label.setText(window.getLabel() + " " + random);

		// log를 남긴다.
		log.log(LogService.LOG_ERROR, "Hello");
	}

	@Focus
	public void focus() {

		label.setFocus();
	}

	/**
	 * 셀렉션 서비스로부터 셀렉션 이벤트를 받기 위해 inject를 사용한다.
	 */
	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		if (selection != null) {
			label.setText(selection.toString());
		}
	}
	
	/**
	 * {@link EventTopic}을 사용하면 non ui thread를 사용하기 때문에 오류가 발생한다.
	 * @param data
	 */
	@Inject
	@Optional
	public void receiveEvent(@UIEventTopic("rainbow/color") String data) {
		label.setText(data + " by event broker");
	}
}
