use thiserror::Error;
use typeshare_model::{decorator, prelude::*};

#[derive(Error, Debug)]
pub enum FormatSpecialTypeError {
    #[error("Unsupported special type: {0:?}")]
    UnsupportedSpecialType(SpecialRustType),
}

#[derive(Error, Debug)]
pub enum WriteDecoratorError {
    #[error("Invalid annotation: {0:?}")]
    InvalidAnnotation(decorator::Value),
}

#[derive(Error, Debug)]
pub enum WriteEnumError {
    #[error(
        "Failed to generate Java type for {name:?}: A serializer must be specified to serialize algebraic enums"
    )]
    SerializerRequiredForAlgebraicEnums { name: String },
    #[error("Unsupported enum variant: {0:?}")]
    UnsupportedEnumVariant(RustEnumVariant),
}

#[derive(Error, Debug)]
pub enum AssertJavaIdentifierError {
    #[error("Invalid Java identifier ({name}): Invalid character ({char})")]
    InvalidCharacter { name: String, char: char },
    #[error("Invalid Java identifier: Empty string")]
    EmptyString,
}

#[derive(Error, Debug)]
pub enum WriteConstError {
    #[error("Unsupported const expression {0:?}")]
    UnsupportedConstExpression(RustConstExpr),
}
