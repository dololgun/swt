package com.sam.e4.application;

import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;


/**
 * 이클립스 컨텍스트는 서비스 이외에 동적으로 계산이 가능한 함수를 제공한다.
 * 이기능을 사용하기 위해 {@link IContextFunction}인터페이스를 구현하는 클래스를 생성한다.
 * 
 * <p>
 * 생성한 클래스를 e4 런타임에 등록해야 한다. Activator class를 사용한다.
 * @author dololgun
 *
 */
public class RandomFuntion implements IContextFunction {

	@Override
	public Object compute(IEclipseContext context, String arg1) {

		return Math.random();
	}

}
