#!/bin/bash

# Load config values
GRADLE_PROPERTIES="gradle.properties"
if [[ ! -f "$GRADLE_PROPERTIES" ]]; then
    echo "gradle.properties was not found"
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
        archives_base_name) MOD_NAME="$value" ;;
        maven_group) MAVEN_GROUP="$value" ;;
    esac
done < "$GRADLE_PROPERTIES"

if [[ -z "$MOD_ID" ]]; then
    echo "mod_id is not set in $GRADLE_PROPERTIES"
    exit 1
fi

if [[ -z "$MOD_NAME" ]]; then
    echo "archives_base_name is not set in $GRADLE_PROPERTIES"
    exit 1
fi

if [[ -z "$MAVEN_GROUP" ]]; then
    echo "maven_group is not set in $GRADLE_PROPERTIES"
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
fi

# Remove setup files
rm -- "setup.bat" "setup.sh"

echo "Setup completed successfully."