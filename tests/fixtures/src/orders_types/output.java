package com.typeshare.java;

public class Namespace {

	public record A(
		Long field
	) {}

	public record B(
		A dependsOn
	) {}

	public record C(
		B dependsOn
	) {}

	public record E(
		D dependsOn
	) {}

	public record D(
		C dependsOn,
		E alsoDependsOn
	) {}

}
