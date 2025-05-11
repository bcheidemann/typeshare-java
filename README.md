# typeshare-java

`typeshare-java` is a CLI tool for converting Rust types into Java types. It is
part of the wider [typeshare](https://crates.io/crates/typeshare) ecosystem.

## Installation

First, install the CLI:

```sh
cargo install typeshare-java
```

Then, install the annotations:

```sh
cargo add typeshare
```

## Feature Support

| Feature         | Status | Comment                                         |
|-----------------|--------|-------------------------------------------------|
| Structs         | ✅     | -                                               |
| Struct Generics | ✅     | -                                               |
| Unit Enums      | ✅     | -                                               |
| Algebraic Enums | ✅     | Gson only.                                      |
| Enum Generics   | ❌     | -                                               |
| Type Aliases    | ❌     | -                                               |
| Constants       | 🚧     | Only with namespace class option.               |

## Data Types

| Rust Type       | Java Type                 | Comment                                                                                    |
| --------------- | ------------------------- | ------------------------------------------------------------------------------------------ |
| `Vec<T>`        | `java.util.ArrayList<T>`  | -                                                                                          |
| `[T; N]`        | `T[]`                     | There is no fixed length array type in Java.                                               |
| `&[T]`          | `T[]`                     | -                                                                                          |
| `HashMap<K, V>` | `java.util.HashMap<K, V>` | -                                                                                          |
| `Option<T>      | `T`                       | All types in Java are nullable.                                                            |
| `Unit`          | `Void`                    | -                                                                                          |
| `String`        | `String`                  | -                                                                                          |
| `char`          | `Character`               | -                                                                                          |
| `i8`            | `Byte`                    | -                                                                                          |
| `i16`           | `Short`                   | -                                                                                          |
| `ISize`, `i32`  | `Integer`                 | -                                                                                          |
| `I54`, `i64`    | `Long`                    | -                                                                                          |
| `u8`            | `Short`                   | `Byte` in Java is signed, so we need to use `short` to represent all possible values.      |
| `u16`           | `Integer`                 | `Short` in Java is signed, so we need to use `int` to represent all possible values.       |
| `u32`           | `Long`                    | `Integer` in Java is signed, so we need to use `long` to represent all possible values.    |
| `u64`           | `java.math.BigInteger`    | `Long` in Java is signed, so we need to use `BigInteger` to represent all possible values. |
| `bool`          | `Boolean`                 | -                                                                                          |
| `f32`           | `Float`                   | -                                                                                          |
| `f64`           | `Double`                  | -                                                                                          |

> [!NOTE]
> We prefer to use classes over primitive types (e.g. `Integer` over `int`).
> This is because primitve types can't be used as generics in Java. In future,
> we may use primitive types outside of generic contexts.

> [!NOTE]
> In general, for a given Rust type (`Tr`) we choose a Java type (`Tj`) such
> that the Rust type is subset of the Java type (`Tr⊆Tj`). This way, all
> possible Rust values can be represented in Java. However, in some cases, it is
> also possible to represent values in Java which would be invalid in Rust. For
> example, in Java there is no unsigned 8-bit integer type. Therefore, we use
> the `Short` Java type to represent `u8` in Rust. All possible `u8` values can
> be represented in a `Short`, but `Short` additionally allows negative values.
> It is up to the developer to ensure that the value used in Java is valid
> when deserialized in Rust.

## Usage

### CLI

To get started, run:

```sh
cd <your-rust-project>
typeshare-java --output-file output.java ./
```

### Annotations

#### Getting Started

For `typeshare-java` to generate types from your Rust code, it requires you to
add a special annotation:

```rs
#[typeshare]
struct Color {
    r: u8,
    g: u8,
    b: u8,
}
```

In some cases, you may also need to add serde annotations:

```rs
#[typeshare]
#[serde(tag = "type", content = "content")]
pub enum BestNflTeam {
    KansasCity,
    Lies(String),
}
```

#### Adding Java Annotations

Java annotations can be added as follows:

```rs
#[typeshare(java(annotations = "@Getter"))]
pub enum Color {
    Red,
    Blue,
    Green,
}
```

Multiple annotations can be added like this:

```rs
#[typeshare(java(annotations = "
    @Getter
    @JsonAdapter(MyCustomAdapter.class)
"))]
pub enum Color {
    Red,
    Blue,
    Green,
}
```

### Config

In most cases, config options can be passed via the command line. However, some
options can only be added in a `typeshare.toml` file. Here is an example config
file:

```toml
[java]
package = "com.typeshare.java"
namespace_class = true

[java.header_comment]
type = "None"

[java.serializer]
type = "Gson"
```

#### Options

##### `header-comment`

A header comment can be added to each generated file.

```toml
[java.header_comment]
type = "Default" // Generated by typeshare-java <version>
// Or...
type = "None"
// Or...
type = "Custom"
comment = "This comment will be included at the top of each generated file"
```

##### `package`

The package name of the output.

```java
package <package>; // Added to the top of output files
```

##### `prefix`

An optional prefix for type names.

```rs
#[typeshare]
struct Example {}
```

With prefix set to "Tada":

```java
public record TadaExample() {}
```

##### `namespace_class`

Java only supports one top level file per class. We can get around this by
wrapping classes in a namespace class. This will have the same name as the
crate, converted to pascal case.

```rs
#[typeshare]
struct Inner {}

#[typeshare]
struct Outer {
    pub inner: Inner,
}
```

If the crate is named `my_crate` and namespace classes are enabled, the
following Java code will be generated:

```java
public class MyCrate {

    public record Inner() {}

    public record Outer(Inner inner) {}

}
```

##### `serializer`

Java has several serialization/deserialization packages. Depending on the Rust
code you're working with, serializer specific code may need to be emitted.
Currently, only `None` and `Gson` are supported. If `None` (default), then
some data structures, such as algebraic enums, cannot be converted to Java code.

```toml
[java.serializer]
type = "None"
// Or...
type = "Gson"
```

#### `indent`

The indent type and size can be configured as follows:

```toml
[java.indent]
type = "Spaces"
size = 4
```
