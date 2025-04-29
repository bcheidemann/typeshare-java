/// Struct comment
#[typeshare]
pub struct ItemDetailsFieldValue {}

/// Enum comment
#[typeshare]
#[serde(tag = "type", content = "content")]
pub enum AdvancedColors {
    None,
    /// This is a case comment
    MyString(String),
    MyNumber(i32),
    UnsignedNumber(u32),
    NumberArray(Vec<i32>),
    /// Comment on the last element
    ReallyCoolType(ItemDetailsFieldValue),
    MaybeString(Option<String>),
}
