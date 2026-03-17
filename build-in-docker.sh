#!/usr/bin/env bash
set -euo pipefail

# Build the project inside Docker (OpenJDK 17 + Maven).
# Usage: ./build-in-docker.sh

IMAGE_NAME=virtual-engine-build

docker build -t "$IMAGE_NAME" . 

echo "Docker build finished. If the image built successfully, the project was packaged inside the image (mvn -DskipTests package)."

# Optionally run the packaged jar (requires network ports mapped and Postgres/Redis available if used at runtime)
# docker run --rm -p 8443:8443 "$IMAGE_NAME"
