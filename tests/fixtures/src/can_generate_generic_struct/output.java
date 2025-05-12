package com.typeshare.java;

public class Namespace {

	public record GenericStruct<A, B>(
		A field_a,
		java.util.ArrayList<B> field_b
	) {}

	public record GenericStructUsingGenericStruct<T>(
		GenericStruct<String, T> struct_field,
		GenericStruct<T, String> second_struct_field,
		GenericStruct<T, java.util.ArrayList<T>> third_struct_field
	) {}

	@com.google.gson.annotations.JsonAdapter(_EnumUsingGenericStructAdapter.class)
	public sealed interface EnumUsingGenericStruct
		permits
			EnumUsingGenericStruct.VariantA,
			EnumUsingGenericStruct.VariantB,
			EnumUsingGenericStruct.VariantC,
			EnumUsingGenericStruct.VariantD {

		@com.google.gson.annotations.JsonAdapter(_EnumUsingGenericStructAdapter.class)
		public record VariantA(GenericStruct<String, Float> content) implements EnumUsingGenericStruct {}

		@com.google.gson.annotations.JsonAdapter(_EnumUsingGenericStructAdapter.class)
		public record VariantB(GenericStruct<String, Integer> content) implements EnumUsingGenericStruct {}

		@com.google.gson.annotations.JsonAdapter(_EnumUsingGenericStructAdapter.class)
		public record VariantC(GenericStruct<String, Boolean> content) implements EnumUsingGenericStruct {}

		@com.google.gson.annotations.JsonAdapter(_EnumUsingGenericStructAdapter.class)
		public record VariantD(GenericStructUsingGenericStruct<Void> content) implements EnumUsingGenericStruct {}

	}

	private final class _EnumUsingGenericStructAdapter extends com.google.gson.TypeAdapter<EnumUsingGenericStruct> {

		private static com.google.gson.Gson gson = new com.google.gson.Gson();

		@Override
		public void write(
			com.google.gson.stream.JsonWriter out,
			EnumUsingGenericStruct value
		) throws java.io.IOException {
			if (value instanceof EnumUsingGenericStruct.VariantA) {
				var content = ((EnumUsingGenericStruct.VariantA) value).content();
				out.beginObject();
				out.name("type");
				out.value("VariantA");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof EnumUsingGenericStruct.VariantB) {
				var content = ((EnumUsingGenericStruct.VariantB) value).content();
				out.beginObject();
				out.name("type");
				out.value("VariantB");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof EnumUsingGenericStruct.VariantC) {
				var content = ((EnumUsingGenericStruct.VariantC) value).content();
				out.beginObject();
				out.name("type");
				out.value("VariantC");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof EnumUsingGenericStruct.VariantD) {
				var content = ((EnumUsingGenericStruct.VariantD) value).content();
				out.beginObject();
				out.name("type");
				out.value("VariantD");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			throw new RuntimeException("unreachable!");
		}

		@Override
		public EnumUsingGenericStruct read(
			com.google.gson.stream.JsonReader in
		) throws java.io.IOException {
			JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
			JsonElement tagElement = jsonObject.get("type");

			if (tagElement == null) {
				throw new java.io.IOException("Missing 'type' field for EnumUsingGenericStruct");
			}

			if (!tagElement.isJsonPrimitive()) {
				throw new java.io.IOException("Invalid 'type' field for EnumUsingGenericStruct");
			}

			String tag = tagElement.getAsString();

			return switch (tag) {
				case "VariantA" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'VariantA' variant missing 'content'");
					}
					com.google.gson.reflect.TypeToken<GenericStruct<String, Float>> contentType = new com.google.gson.reflect.TypeToken<GenericStruct<String, Float>>() {};
					GenericStruct<String, Float> content = gson.fromJson(contentElement, contentType);
					yield new EnumUsingGenericStruct.VariantA(content);
				}
				case "VariantB" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'VariantB' variant missing 'content'");
					}
					com.google.gson.reflect.TypeToken<GenericStruct<String, Integer>> contentType = new com.google.gson.reflect.TypeToken<GenericStruct<String, Integer>>() {};
					GenericStruct<String, Integer> content = gson.fromJson(contentElement, contentType);
					yield new EnumUsingGenericStruct.VariantB(content);
				}
				case "VariantC" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'VariantC' variant missing 'content'");
					}
					com.google.gson.reflect.TypeToken<GenericStruct<String, Boolean>> contentType = new com.google.gson.reflect.TypeToken<GenericStruct<String, Boolean>>() {};
					GenericStruct<String, Boolean> content = gson.fromJson(contentElement, contentType);
					yield new EnumUsingGenericStruct.VariantC(content);
				}
				case "VariantD" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'VariantD' variant missing 'content'");
					}
					com.google.gson.reflect.TypeToken<GenericStructUsingGenericStruct<Void>> contentType = new com.google.gson.reflect.TypeToken<GenericStructUsingGenericStruct<Void>>() {};
					GenericStructUsingGenericStruct<Void> content = gson.fromJson(contentElement, contentType);
					yield new EnumUsingGenericStruct.VariantD(content);
				}
				default -> throw new java.io.IOException("Unknown variant: " + tag);
			};
		}

	}

}
