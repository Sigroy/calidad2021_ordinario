package com.anahuac.calidad.doubles;

public class Dependency {
	private final SubDependency subDependency;

	public Dependency(SubDependency subDependency) {
		super();
		this.subDependency = subDependency;
	}

	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	public String getSubdependencyClassname() {
		return subDependency.getClassName();
	}

	public int addTwo(int i) {
		return i + 2;
	}

	public int multiply(int i, int j) {
		return i * j;
	}

	public String getClassNameUpperCase() {
		return getClassName().toUpperCase();
	}
}