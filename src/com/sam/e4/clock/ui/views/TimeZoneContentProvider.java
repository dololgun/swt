package com.sam.e4.clock.ui.views;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ITreeContentProvider;

public class TimeZoneContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {

		// 중요! 자식노드를 가지고 오는 매소드
		if (parentElement instanceof Map<?, ?>) {
			Map<?, ?> map = (Map<?, ?>) parentElement;
			return map.entrySet().toArray();
		} else if (parentElement instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (Entry<?, ?>)parentElement;
			return getChildren(entry.getValue());
		} else if (parentElement instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>)parentElement;
			return collection.toArray();
		} else {
			return new Object[0];
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {

		// 중요! 루트객체를 가지고 오는 매소드
		// 멀티 루트를 가지고 올수 있다
		// 배열이라면 배열을 리턴하고 배열이 아니면 빈 배열을 리턴한다.
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		} else {
			return new Object[0];
		}
	}

	@Override
	public Object getParent(Object arg0) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object parentElement) {

		// 중요! 자식노드가 있는지 확인하는 매소드
		if (parentElement instanceof Map<?, ?>) {
			Map<?, ?> map = (Map<?, ?>) parentElement;
			return !map.entrySet().isEmpty();
		} else if (parentElement instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (Entry<?, ?>)parentElement;
			return hasChildren(entry.getValue());
		} else if (parentElement instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>)parentElement;
			return !collection.isEmpty();
		} else {
			return false;
		}
	}

}
