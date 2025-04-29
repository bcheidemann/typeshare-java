fn main() {
    println!("cargo:rerun-if-changed=build.rs");

    // Watch the fixtures directory to re-run tests if new fixtures are added
    println!("cargo:rerun-if-changed=tests/fixtures");
}
