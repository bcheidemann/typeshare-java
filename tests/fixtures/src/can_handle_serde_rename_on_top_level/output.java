package com.typeshare.java;

public class Namespace {

	public record OtherType() {}

	/// This is a comment.
	public record PersonTwo(
		String name,
		Short age,
		Integer extraSpecialFieldOne,
		java.util.ArrayList<String> extraSpecialFieldTwo,
		OtherType nonStandardDataType,
		java.util.ArrayList<OtherType> nonStandardDataTypeInArray
	) {}

}
