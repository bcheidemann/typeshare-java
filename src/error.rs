use thiserror::Error;
use typeshare_model::{decorator, prelude::SpecialRustType};

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
