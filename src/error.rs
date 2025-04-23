use thiserror::Error;
use typeshare_model::prelude::SpecialRustType;

#[derive(Error, Debug)]
pub enum FormatSpecialTypeError {
    #[error("Unsupported special type: {0:?}")]
    UnsupportedSpecialType(SpecialRustType),
}
