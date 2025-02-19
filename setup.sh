#!/bin/bash

# Load config values
CONFIG_FILE="setup.config"
if [[ ! -f "$CONFIG_FILE" ]]; then
    echo "$CONFIG_FILE was not found"
    exit 1
fi

# Read values from the config file
MOD_ID=""
MOD_NAME=""
MAVEN_GROUP=""
while IFS='=' read -r key value; do
    key=$(echo "$key" | tr -d ' ')
    value=$(echo "$value" | tr -d ' ')
    case "$key" in
        mod_id) MOD_ID="$value" ;;
        mod_name) MOD_NAME="$value" ;;
        maven_group) MAVEN_GROUP="$value" ;;
    esac
done < "$CONFIG_FILE"

if [[ -z "$MOD_ID" ]]; then
    echo "mod_id is not set in $CONFIG_FILE"
    exit 1
fi

if [[ -z "$MOD_NAME" ]]; then
    echo "mod_name is not set in $CONFIG_FILE"
    exit 1
fi

if [[ -z "$MAVEN_GROUP" ]]; then
    echo "maven_group is not set in $CONFIG_FILE"
    exit 1
fi

# Rename directories
find . -depth -type d -name "*pistonmodtemplate*" | while read -r dir; do
    newdir=$(echo "$dir" | sed "s/pistonmodtemplate/$MOD_ID/g")
    if [ "$dir" != "$newdir" ]; then
        mv "$dir" "$newdir"
    fi
done

# Rename files
find . -depth -type f -name "*PistonModTemplate*" | while read -r file; do
    newfile=$(echo "$file" | sed "s/PistonModTemplate/$MOD_NAME/g")
    if [ "$file" != "$newfile" ]; then
        mv "$file" "$newfile"
    fi
done

# Replace mod_id occurrences in files
find . -type f -exec sed -i "s/pistonmodtemplate/$MOD_ID/g" {} +

# Replace mod_name occurrences in files
find . -type f -exec sed -i "s/PistonModTemplate/$MOD_NAME/g" {} +

# Change Maven Group
if [[ "$MAVEN_GROUP" != "ca.fxco" ]]; then
    OLD_PACKAGE_PATH="$(echo ca.fxco | tr '.' '/')"
    NEW_PACKAGE_PATH="$(echo $MAVEN_GROUP | tr '.' '/')"
    mkdir -p "src/main/java/$NEW_PACKAGE_PATH"
    mv "src/main/java/$OLD_PACKAGE_PATH"/* "src/main/java/$NEW_PACKAGE_PATH/"
    rm -rf "src/main/java/$OLD_PACKAGE_PATH"
    find src/main/java -type f -exec sed -i "/ca\.fxco\.pistonlib/!s/ca\.fxco/$MAVEN_GROUP/g" {} +
    # Replace maven_group in gradle.properties
    sed -i "/ca\.fxco/$MAVEN_GROUP/g" gradle.properties
fi

# Remove setup files
rm -- "setup.bat" "setup.sh" "setup.config"

echo "Setup completed successfully."