#[typeshare(java(annotations = "@JsonAdapter(ColorAdapter.class)"))]
/// A color enum
pub enum Color {
    Red,
    Blue,
    /// Green is a cool color
    Green,
}
