package com.typeshare.java;

public class Namespace {

	public record Location() {}

	/**
	 * This is a comment.
	 */
	public record Person(
		/**
		 * This is another comment
		 */
		String name,
		Short age,
		String info,
		java.util.ArrayList<String> emails,
		Location location
	) {}

}
