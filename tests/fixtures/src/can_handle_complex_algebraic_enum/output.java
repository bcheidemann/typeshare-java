package com.typeshare.java;

public class Namespace {

	/**
	 * Generated type representing the anonymous struct variant `AnonVariant` of the `EnumWithManyVariants` Rust enum
	 */
	public record EnumWithManyVariantsAnonVariantInner(
		String uuid
	) {}

	/**
	 * Generated type representing the anonymous struct variant `AnotherAnonVariant` of the `EnumWithManyVariants` Rust enum
	 */
	public record EnumWithManyVariantsAnotherAnonVariantInner(
		String uuid,
		Integer thing
	) {}

	/**
	 * This is a comment (yareek sameek wuz here)
	 */
	@com.google.gson.annotations.JsonAdapter(_EnumWithManyVariantsAdapter.class)
	public sealed interface EnumWithManyVariants
		permits
			EnumWithManyVariants.UnitVariant,
			EnumWithManyVariants.TupleVariantString,
			EnumWithManyVariants.AnonVariant,
			EnumWithManyVariants.TupleVariantInt,
			EnumWithManyVariants.AnotherUnitVariant,
			EnumWithManyVariants.AnotherAnonVariant {

		@com.google.gson.annotations.JsonAdapter(_EnumWithManyVariantsAdapter.class)
		public record UnitVariant() implements EnumWithManyVariants {}

		@com.google.gson.annotations.JsonAdapter(_EnumWithManyVariantsAdapter.class)
		public record TupleVariantString(String content) implements EnumWithManyVariants {}

		@com.google.gson.annotations.JsonAdapter(_EnumWithManyVariantsAdapter.class)
		public record AnonVariant(EnumWithManyVariantsAnonVariantInner content) implements EnumWithManyVariants {}

		@com.google.gson.annotations.JsonAdapter(_EnumWithManyVariantsAdapter.class)
		public record TupleVariantInt(Integer content) implements EnumWithManyVariants {}

		@com.google.gson.annotations.JsonAdapter(_EnumWithManyVariantsAdapter.class)
		public record AnotherUnitVariant() implements EnumWithManyVariants {}

		@com.google.gson.annotations.JsonAdapter(_EnumWithManyVariantsAdapter.class)
		public record AnotherAnonVariant(EnumWithManyVariantsAnotherAnonVariantInner content) implements EnumWithManyVariants {}

	}

	private final class _EnumWithManyVariantsAdapter extends com.google.gson.TypeAdapter<EnumWithManyVariants> {

		private static com.google.gson.Gson gson = new com.google.gson.Gson();

		@Override
		public void write(
			com.google.gson.stream.JsonWriter out,
			EnumWithManyVariants value
		) throws java.io.IOException {
			if (value instanceof EnumWithManyVariants.UnitVariant) {
				out.beginObject();
				out.name("type");
				out.value("UnitVariant");
				out.endObject();
				return;
			}

			if (value instanceof EnumWithManyVariants.TupleVariantString) {
				var content = ((EnumWithManyVariants.TupleVariantString) value).content();
				out.beginObject();
				out.name("type");
				out.value("TupleVariantString");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof EnumWithManyVariants.AnonVariant) {
				var content = ((EnumWithManyVariants.AnonVariant) value).content();
				out.beginObject();
				out.name("type");
				out.value("AnonVariant");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof EnumWithManyVariants.TupleVariantInt) {
				var content = ((EnumWithManyVariants.TupleVariantInt) value).content();
				out.beginObject();
				out.name("type");
				out.value("TupleVariantInt");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof EnumWithManyVariants.AnotherUnitVariant) {
				out.beginObject();
				out.name("type");
				out.value("AnotherUnitVariant");
				out.endObject();
				return;
			}

			if (value instanceof EnumWithManyVariants.AnotherAnonVariant) {
				var content = ((EnumWithManyVariants.AnotherAnonVariant) value).content();
				out.beginObject();
				out.name("type");
				out.value("AnotherAnonVariant");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			throw new RuntimeException("unreachable!");
		}

		@Override
		public EnumWithManyVariants read(
			com.google.gson.stream.JsonReader in
		) throws java.io.IOException {
			JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
			JsonElement tagElement = jsonObject.get("type");

			if (tagElement == null) {
				throw new java.io.IOException("Missing 'type' field for EnumWithManyVariants");
			}

			if (!tagElement.isJsonPrimitive()) {
				throw new java.io.IOException("Invalid 'type' field for EnumWithManyVariants");
			}

			String tag = tagElement.getAsString();

			return switch (tag) {
				case "UnitVariant" -> new EnumWithManyVariants.UnitVariant();
				case "TupleVariantString" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'TupleVariantString' variant missing 'content'");
					}
					String content = gson.fromJson(contentElement, String.class);
					yield new EnumWithManyVariants.TupleVariantString(content);
				}
				case "AnonVariant" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'AnonVariant' variant missing 'content'");
					}
					EnumWithManyVariantsAnonVariantInner content = gson.fromJson(contentElement, EnumWithManyVariantsAnonVariantInner.class);
					yield new EnumWithManyVariants.AnonVariant(content);
				}
				case "TupleVariantInt" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'TupleVariantInt' variant missing 'content'");
					}
					Integer content = gson.fromJson(contentElement, Integer.class);
					yield new EnumWithManyVariants.TupleVariantInt(content);
				}
				case "AnotherUnitVariant" -> new EnumWithManyVariants.AnotherUnitVariant();
				case "AnotherAnonVariant" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'AnotherAnonVariant' variant missing 'content'");
					}
					EnumWithManyVariantsAnotherAnonVariantInner content = gson.fromJson(contentElement, EnumWithManyVariantsAnotherAnonVariantInner.class);
					yield new EnumWithManyVariants.AnotherAnonVariant(content);
				}
				default -> throw new java.io.IOException("Unknown variant: " + tag);
			};
		}

	}

}
