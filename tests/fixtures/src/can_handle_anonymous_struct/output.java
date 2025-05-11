package com.typeshare.java;

public class Namespace {

	/**
	 * Generated type representing the anonymous struct variant `Us` of the `AutofilledBy` Rust enum
	 */
	public record AutofilledByUsInner(
		/**
		 * The UUID for the fill
		 */
		String uuid
	) {}

	/**
	 * Generated type representing the anonymous struct variant `SomethingElse` of the `AutofilledBy` Rust enum
	 */
	public record AutofilledBySomethingElseInner(
		/**
		 * The UUID for the fill
		 */
		String uuid,
		/**
		 * Some other thing
		 */
		Integer thing
	) {}

	/**
	 * Enum keeping track of who autofilled a field
	 */
	@com.google.gson.annotations.JsonAdapter(_AutofilledByAdapter.class)
	public sealed interface AutofilledBy
		permits
			AutofilledBy.Us,
			AutofilledBy.SomethingElse {

		/**
		 * This field was autofilled by us
		 */
		@com.google.gson.annotations.JsonAdapter(_AutofilledByAdapter.class)
		public record Us(AutofilledByUsInner content) implements AutofilledBy {}

		/**
		 * Something else autofilled this field
		 */
		@com.google.gson.annotations.JsonAdapter(_AutofilledByAdapter.class)
		public record SomethingElse(AutofilledBySomethingElseInner content) implements AutofilledBy {}

	}

	private final class _AutofilledByAdapter extends com.google.gson.TypeAdapter<AutofilledBy> {

		private static com.google.gson.Gson gson = new com.google.gson.Gson();

		@Override
		public void write(
			com.google.gson.stream.JsonWriter out,
			AutofilledBy value
		) throws java.io.IOException {
			if (value instanceof AutofilledBy.Us) {
				var content = ((AutofilledBy.Us) value).content();
				out.beginObject();
				out.name("type");
				out.value("Us");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			if (value instanceof AutofilledBy.SomethingElse) {
				var content = ((AutofilledBy.SomethingElse) value).content();
				out.beginObject();
				out.name("type");
				out.value("SomethingElse");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			throw new RuntimeException("unreachable!");
		}

		@Override
		public AutofilledBy read(
			com.google.gson.stream.JsonReader in
		) throws java.io.IOException {
			JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
			JsonElement tagElement = jsonObject.get("type");

			if (tagElement == null) {
				throw new java.io.IOException("Missing 'type' field for AutofilledBy");
			}

			if (!tagElement.isJsonPrimitive()) {
				throw new java.io.IOException("Invalid 'type' field for AutofilledBy");
			}

			String tag = tagElement.getAsString();

			return switch (tag) {
				case "Us" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'Us' variant missing 'content'");
					}
					AutofilledByUsInner content = gson.fromJson(contentElement, AutofilledByUsInner.class);
					yield new AutofilledBy.Us(content);
				}
				case "SomethingElse" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'SomethingElse' variant missing 'content'");
					}
					AutofilledBySomethingElseInner content = gson.fromJson(contentElement, AutofilledBySomethingElseInner.class);
					yield new AutofilledBy.SomethingElse(content);
				}
				default -> throw new java.io.IOException("Unknown variant: " + tag);
			};
		}

	}

}
