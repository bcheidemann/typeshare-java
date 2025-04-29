package com.typeshare.java;

public class Namespace {

	/**
	 * A color enum
	 */
	@JsonAdapter(ColorAdapter.class)
	public enum Color {
		Red,
		Blue,
		/**
		 * Green is a cool color
		 */
		Green
	}

}
