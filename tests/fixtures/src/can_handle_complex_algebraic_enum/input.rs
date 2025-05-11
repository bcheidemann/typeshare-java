/// This is a comment (yareek sameek wuz here)
#[typeshare]
#[serde(tag = "type", content = "content")]
pub enum EnumWithManyVariants {
    UnitVariant,
    TupleVariantString(String),
    AnonVariant { uuid: String },
    TupleVariantInt(i32),
    AnotherUnitVariant,
    AnotherAnonVariant { uuid: String, thing: i32 },
}
