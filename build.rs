use fixtures::build::watch_fixture_dir;

fn main() {
    watch_fixture_dir("cargo:rerun-if-changed=tests/fixtures");
}
