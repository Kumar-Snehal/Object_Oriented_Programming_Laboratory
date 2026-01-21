#!/usr/bin/env bash
set -euo pipefail

[ "$#" -eq 2 ] || { echo "Usage: $0 <lab_folder> <question>"; exit 1; }

Folder="$1"
Question="$2"
   
javac -d . "$Folder"/*.java

java -cp . "${Folder}.${Question}"
