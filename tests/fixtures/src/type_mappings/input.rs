#[typeshare]
struct AdminUser {
    pub id: Uuid,
}

#[typeshare]
#[serde(tag = "type", content = "content")]
enum UserId {
    Admin(Uuid),
}

#[typeshare]
#[serde(tag = "type", content = "content")]
enum User {
    Admin { id: Uuid },
}
