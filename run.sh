#!/usr/bin/env bash
set -euo pipefail

[ "$#" -eq 2 ] || { echo "Usage: $0 <lab_folder> <question>"; exit 1; }

Folder="$1"      # e.g., Lab_A3
Question="$2"    # e.g., Q6

# Compile into proper package folder structure
javac -d . "$Folder"/*.java   # outputs ./Lab_A3/*.class if package Lab_A3; [web:27]

# Run with classpath = current directory (the package root)
java -cp . "${Folder}.${Question}"  # e.g., Lab_A3.Q6 [web:20]
