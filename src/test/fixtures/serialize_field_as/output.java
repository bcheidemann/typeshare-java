package com.typeshare.java;

public class Namespace {

	public record EditItemViewModelSaveRequest(
		String context,
		java.util.ArrayList<EditItemSaveValue> values,
		AutoFillItemActionRequest fill_action
	) {}

}

