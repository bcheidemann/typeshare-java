package com.typeshare.java;

public record EditItemViewModelSaveRequest(
	String context,
	java.util.ArrayList<EditItemSaveValue> values,
	AutoFillItemActionRequest fill_action
) {}
