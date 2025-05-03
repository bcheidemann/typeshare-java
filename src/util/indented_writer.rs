use std::io::{self, Write};

pub struct IndentedWriter<'a, W: Write> {
    inner: &'a mut W,
    at_line_start: bool,
    indent_char: char,
    indent_count: usize,
}

impl<'a, W: Write> IndentedWriter<'a, W> {
    pub fn new(inner: &'a mut W, indent_char: char, indent_count: usize) -> Self {
        Self {
            inner,
            at_line_start: true,
            indent_char,
            indent_count,
        }
    }
}

impl<W: Write> IndentedWriter<'_, W> {
    pub fn indent(&mut self) {
        self.indent_count += 1;
    }

    pub fn dedent(&mut self) {
        assert!(self.indent_count != 0, "cannot decrement below 0");
        self.indent_count -= 1;
    }
}

impl<W: Write> Write for IndentedWriter<'_, W> {
    fn write(&mut self, buf: &[u8]) -> io::Result<usize> {
        let indent = self.indent_char.to_string().repeat(self.indent_count);
        let indent_bytes = indent.as_bytes();
        let mut written_to = 0;

        for (position, byte) in buf.iter().enumerate() {
            if *byte == b'\n' {
                self.at_line_start = true;
                self.inner.write_all(&buf[written_to..position])?;
                written_to = position;
            } else if self.at_line_start {
                self.at_line_start = false;
                self.inner.write_all(indent_bytes)?;
            }
        }

        if written_to < buf.len() {
            self.inner.write_all(&buf[written_to..])?;
        }

        Ok(buf.len())
    }

    fn flush(&mut self) -> io::Result<()> {
        self.inner.flush()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 1);

        // Act
        write!(indented_writer, "").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "");
    }

    #[test]
    fn test_one_line_0() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 0);

        // Act
        writeln!(indented_writer, "first line").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "first line\n");
    }

    #[test]
    fn test_one_line_1() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 1);

        // Act
        writeln!(indented_writer, "first line").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "\tfirst line\n");
    }

    #[test]
    fn test_one_line_2() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 2);

        // Act
        writeln!(indented_writer, "first line").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "\t\tfirst line\n");
    }

    #[test]
    fn test_two_lines_0() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 0);

        // Act
        writeln!(indented_writer, "first line").unwrap();
        writeln!(indented_writer, "second line").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "first line\nsecond line\n");
    }

    #[test]
    fn test_two_lines_1() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 1);

        // Act
        writeln!(indented_writer, "first line").unwrap();
        writeln!(indented_writer, "second line").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "\tfirst line\n\tsecond line\n");
    }

    #[test]
    fn test_two_lines_2() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 2);

        // Act
        writeln!(indented_writer, "first line").unwrap();
        writeln!(indented_writer, "second line").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "\t\tfirst line\n\t\tsecond line\n");
    }

    #[test]
    fn test_empty_lines() {
        // Arrange
        let mut buffer = Vec::new();
        let mut writer = io::Cursor::new(&mut buffer);
        let mut indented_writer = IndentedWriter::new(&mut writer, '\t', 2);

        // Act
        writeln!(indented_writer, "first line").unwrap();
        writeln!(indented_writer).unwrap();
        writeln!(indented_writer, "second line").unwrap();

        // Assert
        let result = String::from_utf8(buffer).expect("Invalid UTF-8");
        pretty_assertions::assert_eq!(result, "\t\tfirst line\n\n\t\tsecond line\n");
    }
}
