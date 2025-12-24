#!/bin/bash

DEFAULT_VERSION="0.0.1"

if [ -z "$1" ]; then
    VERSION=$DEFAULT_VERSION
    echo "Get Version : $DEFAULT_VERSION"
else
    VERSION=$1
    echo "Using provided version: $VERSION"
fi


docker build -t inventory-service:$VERSION inventory-service/.
docker tag inventory-service:$VERSION swr.cn-north-4.myhuaweicloud.com/itau-demo/inventory-service:$VERSION

# Push image to swr
docker push swr.cn-north-4.myhuaweicloud.com/itau-demo/inventory-service:$VERSION

