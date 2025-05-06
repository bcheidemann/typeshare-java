use std::fs;

use assert_cmd::Command;
use fixtures::fixtures;

#[fixtures(["tests/fixtures/src/*.skip"])]
#[ignore]
fn ignored_integration_test(_: &std::path::Path) {}

#[fixtures([
    "tests/fixtures/src/*",
    "!tests/fixtures/src/*.skip",
])]
fn integration_test(test_case_path: &std::path::Path) {
    let override_config_path = test_case_path.join("typeshare.toml");
    let default_config_path = "tests/typeshare.toml";
    let effective_config_path = {
        override_config_path
            .exists()
            .then(|| override_config_path.to_str().expect("to be valid UTF-8"))
            .unwrap_or(default_config_path)
    };
    let stdout = fs::read_to_string(test_case_path.join("stdout")).ok();
    let stderr = fs::read_to_string(test_case_path.join("stderr")).ok();
    let exitcode = fs::read_to_string(test_case_path.join("exitcode"))
        .ok()
        .map(|exitcode| exitcode.parse::<i32>().expect("to be a valid usize"));
    let output = fs::read_to_string(test_case_path.join("output.java")).ok();

    let temp_output_path = tempfile::tempdir()
        .expect("output directory to be created")
        .path()
        .join("output.java");

    let mut assert = Command::cargo_bin(env!("CARGO_PKG_NAME"))
        .expect("binary to execute")
        .args(vec![
            "--config",
            effective_config_path,
            "--output-file",
            temp_output_path.to_str().expect("to be UTF-8"),
            test_case_path.to_str().expect("to be UTF-8"),
        ])
        .assert();

    if let Some(stdout) = stdout {
        assert = assert.stdout(stdout);
    }

    if let Some(stderr) = stderr {
        assert = assert.stderr(stderr);
    }

    if let Some(exitcode) = exitcode {
        assert = assert.code(exitcode);
    }

    if let Some(output) = output {
        let actual_output = match fs::read_to_string(&temp_output_path) {
            Ok(output) => output,
            Err(_) => {
                eprintln!();
                eprintln!("--stdout--");
                eprintln!(
                    "{}",
                    String::from_utf8_lossy(assert.get_output().stdout.as_slice())
                );
                eprintln!("--end stdout--");
                eprintln!("--stderr--");
                eprintln!(
                    "{}",
                    String::from_utf8_lossy(assert.get_output().stderr.as_slice())
                );
                eprintln!("--end stderr--");
                panic!(
                    "Failed to open {}",
                    temp_output_path.to_str().expect("to be UTF-8")
                );
            }
        };
        if std::env::var_os("UPDATE").is_some() {
            if output != actual_output {
                eprintln!(
                    "Updating {}...",
                    test_case_path
                        .join("output.java")
                        .to_str()
                        .expect("to be UTF-8")
                );
                fs::write(test_case_path.join("output.java"), actual_output)
                    .expect("to successfully update output.java");
            }
        } else {
            pretty_assertions::assert_eq!(output, actual_output);
        }
    }

    drop(assert);
}
