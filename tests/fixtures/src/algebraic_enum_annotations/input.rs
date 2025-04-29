#[typeshare(java(annotations = "@SomeAnnotation"))]
#[serde(tag = "type", content = "content", rename_all = "camelCase")]
/// Not much color in this enum
pub enum Color {
    Grey(u8),
}
