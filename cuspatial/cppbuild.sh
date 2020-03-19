#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" cuspatial
    popd
    exit
fi

case $PLATFORM in
    linux-x86*|linux-ppc*)
        if [[ -z "$CUSPATIAL_HOME" ]]; then
            echo "Please install cuSpatial and dependencies following the instructions at https://github.com/rapidsai/cuspatial"
            exit 1
        fi
        if [[ ! -d "/usr/local/cuda/" ]]; then
            echo "Please install CUDA under the default installation directory"
            exit 1
        fi
        ;;
    macosx-*)
        if [[ -z "$CUSPATIAL_HOME" ]]; then
            echo "Please install cuSpatial and dependencies following the instructions at https://github.com/rapidsai/cuspatial"
            exit 1
        fi
        if [[ ! -d "/usr/local/cuda/" ]]; then
            echo "Please install CUDA under the default installation directory"
            exit 1
        fi
        ;;
    windows-*)
        if [[ -z "$CUSPATIAL_HOME" ]]; then
            echo "Please install cuSpatial and dependencies following the instructions at https://github.com/rapidsai/cuspatial"
            exit 1
        fi
        if [[ ! -d "/C/Program Files/NVIDIA GPU Computing Toolkit/CUDA/" ]]; then
            echo "Please install CUDA under the default installation directory"
            exit 1
        fi
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac
