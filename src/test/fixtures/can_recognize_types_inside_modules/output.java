package com.typeshare.java;

public class Namespace {

	public record A(
		Long field
	) {}

	public record AB(
		Long field
	) {}

	public record ABC(
		Long field
	) {}

	public record OutsideOfModules(
		Long field
	) {}

}
