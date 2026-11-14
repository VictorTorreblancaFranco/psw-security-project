#!/usr/bin/env bash
set -euo pipefail

PORT="${1:-59086}"
OUTPUT_DIR="$(pwd)/zap-output/manual"

mkdir -p "$OUTPUT_DIR"

docker run --rm \
  -v "$OUTPUT_DIR:/zap/wrk/:rw" \
  -t zaproxy/zap-stable \
  zap-baseline.py \
  -t "http://host.docker.internal:${PORT}/login" \
  -m 2 \
  -J zap-report.json \
  -r zap-report.html \
  -w zap-report.md \
  -I

echo "Reporte generado en: $OUTPUT_DIR/zap-report.html"
