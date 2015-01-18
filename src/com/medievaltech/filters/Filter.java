package com.medievaltech.filters;

public abstract class Filter<T> {
	
	public static enum Comparator {
		GREATER_THAN,
		LESS_THAN,
		EQUAL,
		GREATER_THAN_OR_EQUAL,
		LESS_THAN_OR_EQUAL
	}
	
	protected Comparator comp;
	
	public abstract boolean matches(T o);
}
