package com.typeshare.java;

public class Namespace {

	/**
	 * Not much color in this enum
	 */
	@SomeAnnotation
	@com.google.gson.annotations.JsonAdapter(_ColorAdapter.class)
	public sealed interface Color
		permits
			Color.grey {

		@com.google.gson.annotations.JsonAdapter(_ColorAdapter.class)
		public record grey(Short content) implements Color {}

	}

	private final class _ColorAdapter extends com.google.gson.TypeAdapter<Color> {

		private static com.google.gson.Gson gson = new com.google.gson.Gson();

		@Override
		public void write(
			com.google.gson.stream.JsonWriter out,
			Color value
		) throws java.io.IOException {
			if (value instanceof Color.grey) {
				var content = ((Color.grey) value).content();
				out.beginObject();
				out.name("type");
				out.value("grey");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			throw new RuntimeException("unreachable!");
		}

		@Override
		public Color read(
			com.google.gson.stream.JsonReader in
		) throws java.io.IOException {
			JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
			JsonElement tagElement = jsonObject.get("type");

			if (tagElement == null) {
				throw new java.io.IOException("Missing 'type' field for Color");
			}

			if (!tagElement.isJsonPrimitive()) {
				throw new java.io.IOException("Invalid 'type' field for Color");
			}

			String tag = tagElement.getAsString();

			return switch (tag) {
				case "grey" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'grey' variant missing 'content'");
					}
					Short content = gson.fromJson(contentElement, Short.class);
					yield new Color.grey(content);
				}
				default -> throw new java.io.IOException("Unknown variant: " + tag);
			};
		}

	}

}
