use std::collections::HashMap;

use serde::{Deserialize, Serialize};

#[derive(Debug, Serialize, Deserialize)]
#[serde(default)]
pub struct JavaConfig {
    /// The header comment to prepend to generated files
    pub header_comment: HeaderComment,
    /// Name of the Java package
    pub package: Option<String>,
    /// The prefix to append to user-defined types
    pub prefix: Option<String>,
    /// Conversions from Rust type names to Java type names
    pub type_mappings: HashMap<String, String>,
    /// Whether generated Java classes should be wrapped in a namespace class
    pub namespace_class: bool,
}

impl Default for JavaConfig {
    fn default() -> Self {
        Self {
            header_comment: HeaderComment::None,
            package: None,
            prefix: None,
            type_mappings: HashMap::with_capacity(0),
            namespace_class: true,
        }
    }
}

#[derive(Debug, Serialize, Deserialize)]
#[serde(tag = "type")]
pub enum HeaderComment {
    None,
    Custom { comment: String },
    Default,
}
