use std::{collections::HashMap, io::Write};

use serde::{Deserialize, Serialize};

use crate::util::indented_writer::IndentedWriter;

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
    /// Output code for a specific serializer. Currently only Gson is supported.
    pub serializer: JavaSerializerOptions,
    /// Determines the type and size of indentation.
    pub indent: IndentOptions,
}

impl Default for JavaConfig {
    fn default() -> Self {
        Self {
            header_comment: HeaderComment::None,
            package: None,
            prefix: None,
            type_mappings: HashMap::with_capacity(0),
            namespace_class: true,
            serializer: JavaSerializerOptions::None,
            indent: IndentOptions::default(),
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

#[derive(PartialEq, Eq, Debug, Serialize, Deserialize)]
#[serde(tag = "type")]
pub enum JavaSerializerOptions {
    None,
    Gson,
}

#[derive(Debug, Serialize, Deserialize)]
#[serde(tag = "type")]
pub enum IndentOptions {
    Tabs { size: usize },
    Spaces { size: usize },
}

impl IndentOptions {
    pub fn char(&self) -> char {
        match self {
            Self::Tabs { .. } => '\t',
            Self::Spaces { .. } => ' ',
        }
    }

    pub fn size(&self) -> usize {
        match self {
            IndentOptions::Tabs { size } => *size,
            IndentOptions::Spaces { size } => *size,
        }
    }

    pub fn to_indented_writer<'a, W: Write>(&self, w: &'a mut W) -> IndentedWriter<'a, W> {
        IndentedWriter::new(w, self.char(), self.size())
    }

    pub fn indent_whitespace(&self, indent: usize) -> String {
        self.char().to_string().repeat(indent * self.size())
    }

    pub fn indent<S: AsRef<str>>(&self, line: S, indent: usize) -> String {
        format!("{}{}", self.indent_whitespace(indent), line.as_ref())
    }

    pub fn indent_once<S: AsRef<str>>(&self, line: S) -> String {
        format!("{}{}", self.indent_whitespace(1), line.as_ref())
    }
}

impl Default for IndentOptions {
    fn default() -> Self {
        Self::Tabs { size: 1 }
    }
}
