package com.typeshare.java;

public class Namespace {

	/**
	 * Struct comment
	 */
	public record ItemDetailsFieldValue() {}

	/**
	 * Enum comment
	 */
	@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
	public sealed interface AdvancedColors
		permits
			AdvancedColors.None,
			AdvancedColors.MyString,
			AdvancedColors.MyNumber,
			AdvancedColors.UnsignedNumber,
			AdvancedColors.NumberArray,
			AdvancedColors.ReallyCoolType,
			AdvancedColors.MaybeString {

		@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
		public record None() implements AdvancedColors {}

		/**
		 * This is a case comment
		 */
		@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
		public record MyString(String content) implements AdvancedColors {}

		@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
		public record MyNumber(Integer content) implements AdvancedColors {}

		@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
		public record UnsignedNumber(Long content) implements AdvancedColors {}

		@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
		public record NumberArray(java.util.ArrayList<Integer> content) implements AdvancedColors {}

		/**
		 * Comment on the last element
		 */
		@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
		public record ReallyCoolType(ItemDetailsFieldValue content) implements AdvancedColors {}

		@com.google.gson.annotations.JsonAdapter(_AdvancedColorsAdapter.class)
		public record MaybeString(String content) implements AdvancedColors {}

	}

	private final class _AdvancedColorsAdapter extends com.google.gson.TypeAdapter<AdvancedColors> {

		private static com.google.gson.Gson gson = new com.google.gson.Gson();

		@Override
		public void write(
			com.google.gson.stream.JsonWriter out,
			AdvancedColors value
		) throws java.io.IOException {
			if (value instanceof AdvancedColors.None) {
				out.beginObject();
				out.name("type");
				out.value("None");
				out.endObject();
				return;
			}

			if (value instanceof AdvancedColors.MyString) {
				var content = ((AdvancedColors.MyString) value).content();
				out.beginObject();
				out.name("type");
				out.value("MyString");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof AdvancedColors.MyNumber) {
				var content = ((AdvancedColors.MyNumber) value).content();
				out.beginObject();
				out.name("type");
				out.value("MyNumber");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof AdvancedColors.UnsignedNumber) {
				var content = ((AdvancedColors.UnsignedNumber) value).content();
				out.beginObject();
				out.name("type");
				out.value("UnsignedNumber");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof AdvancedColors.NumberArray) {
				var content = ((AdvancedColors.NumberArray) value).content();
				out.beginObject();
				out.name("type");
				out.value("NumberArray");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof AdvancedColors.ReallyCoolType) {
				var content = ((AdvancedColors.ReallyCoolType) value).content();
				out.beginObject();
				out.name("type");
				out.value("ReallyCoolType");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof AdvancedColors.MaybeString) {
				var content = ((AdvancedColors.MaybeString) value).content();
				out.beginObject();
				out.name("type");
				out.value("MaybeString");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			throw new RuntimeException("unreachable!");
		}

		@Override
		public AdvancedColors read(
			com.google.gson.stream.JsonReader in
		) throws java.io.IOException {
			JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
			JsonElement tagElement = jsonObject.get("type");

			if (tagElement == null) {
				throw new java.io.IOException("Missing 'type' field for AdvancedColors");
			}

			if (!tagElement.isJsonPrimitive()) {
				throw new java.io.IOException("Invalid 'type' field for AdvancedColors");
			}

			String tag = tagElement.getAsString();

			return switch (tag) {
				case "None" -> new AdvancedColors.None();
				case "MyString" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'MyString' variant missing 'content'");
					}
					String content = gson.fromJson(contentElement, String.class);
					yield new AdvancedColors.MyString(content);
				}
				case "MyNumber" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'MyNumber' variant missing 'content'");
					}
					Integer content = gson.fromJson(contentElement, Integer.class);
					yield new AdvancedColors.MyNumber(content);
				}
				case "UnsignedNumber" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'UnsignedNumber' variant missing 'content'");
					}
					Long content = gson.fromJson(contentElement, Long.class);
					yield new AdvancedColors.UnsignedNumber(content);
				}
				case "NumberArray" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'NumberArray' variant missing 'content'");
					}
					var contentType = new com.google.gson.reflect.TypeToken<java.util.ArrayList<Integer>>() {};
					java.util.ArrayList<Integer> content = gson.fromJson(contentElement, contentType);
					yield new AdvancedColors.NumberArray(content);
				}
				case "ReallyCoolType" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'ReallyCoolType' variant missing 'content'");
					}
					ItemDetailsFieldValue content = gson.fromJson(contentElement, ItemDetailsFieldValue.class);
					yield new AdvancedColors.ReallyCoolType(content);
				}
				case "MaybeString" -> {
					JsonElement contentElement = jsonObject.get("content");
					String content = gson.fromJson(contentElement, String.class);
					yield new AdvancedColors.MaybeString(content);
				}
				default -> throw new java.io.IOException("Unknown variant: " + tag);
			};
		}

	}

}
