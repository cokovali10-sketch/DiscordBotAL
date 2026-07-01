#!/usr/bin/env sh
DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
GRADLE_BIN="$DIR/gradle-8.10.2/bin/gradle"
if [ -x "$GRADLE_BIN" ]; then
  exec "$GRADLE_BIN" "$@"
else
  echo "Gradle distribution not found. Run the setup step again." >&2
  exit 1
fi
