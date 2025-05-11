package com.typeshare.java;

public class Namespace {

	public record AdminUser(
		java.util.UUID id
	) {}

	/**
	 * Generated type representing the anonymous struct variant `Admin` of the `User` Rust enum
	 */
	public record UserAdminInner(
		java.util.UUID id
	) {}

	@com.google.gson.annotations.JsonAdapter(_UserAdapter.class)
	public sealed interface User
		permits
			User.Admin {

		@com.google.gson.annotations.JsonAdapter(_UserAdapter.class)
		public record Admin(UserAdminInner content) implements User {}

	}

	private final class _UserAdapter extends com.google.gson.TypeAdapter<User> {

		private static com.google.gson.Gson gson = new com.google.gson.Gson();

		@Override
		public void write(
			com.google.gson.stream.JsonWriter out,
			User value
		) throws java.io.IOException {
			if (value instanceof User.Admin) {
				var content = ((User.Admin) value).content();
				out.beginObject();
				out.name("type");
				out.value("Admin");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			throw new RuntimeException("unreachable!");
		}

		@Override
		public User read(
			com.google.gson.stream.JsonReader in
		) throws java.io.IOException {
			JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
			JsonElement tagElement = jsonObject.get("type");

			if (tagElement == null) {
				throw new java.io.IOException("Missing 'type' field for User");
			}

			if (!tagElement.isJsonPrimitive()) {
				throw new java.io.IOException("Invalid 'type' field for User");
			}

			String tag = tagElement.getAsString();

			return switch (tag) {
				case "Admin" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'Admin' variant missing 'content'");
					}
					UserAdminInner content = gson.fromJson(contentElement, UserAdminInner.class);
					yield new User.Admin(content);
				}
				default -> throw new java.io.IOException("Unknown variant: " + tag);
			};
		}

	}

	@com.google.gson.annotations.JsonAdapter(_UserIdAdapter.class)
	public sealed interface UserId
		permits
			UserId.Admin {

		@com.google.gson.annotations.JsonAdapter(_UserIdAdapter.class)
		public record Admin(java.util.UUID content) implements UserId {}

	}

	private final class _UserIdAdapter extends com.google.gson.TypeAdapter<UserId> {

		private static com.google.gson.Gson gson = new com.google.gson.Gson();

		@Override
		public void write(
			com.google.gson.stream.JsonWriter out,
			UserId value
		) throws java.io.IOException {
			if (value instanceof UserId.Admin) {
				var content = ((UserId.Admin) value).content();
				out.beginObject();
				out.name("type");
				out.value("Admin");
				out.name("content");
				gson.toJson(gson.toJsonTree(content), out);
				out.endObject();
				return;
			}

			throw new RuntimeException("unreachable!");
		}

		@Override
		public UserId read(
			com.google.gson.stream.JsonReader in
		) throws java.io.IOException {
			JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
			JsonElement tagElement = jsonObject.get("type");

			if (tagElement == null) {
				throw new java.io.IOException("Missing 'type' field for UserId");
			}

			if (!tagElement.isJsonPrimitive()) {
				throw new java.io.IOException("Invalid 'type' field for UserId");
			}

			String tag = tagElement.getAsString();

			return switch (tag) {
				case "Admin" -> {
					JsonElement contentElement = jsonObject.get("content");
					if (contentElement == null) {
						throw new java.io.IOException("'Admin' variant missing 'content'");
					}
					java.util.UUID content = gson.fromJson(contentElement, java.util.UUID.class);
					yield new UserId.Admin(content);
				}
				default -> throw new java.io.IOException("Unknown variant: " + tag);
			};
		}

	}

}
