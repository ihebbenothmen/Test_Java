The decode() method doesn't validate input so if input - 0x1e6 is outside the range of mapping it will throw an error.

Files.write assumes the file exists but doesn’t check its presence or create it if missing.

HTTP requests lack a timeout which may hang indefinitely.