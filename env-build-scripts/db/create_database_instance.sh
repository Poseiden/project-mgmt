#!/bin/bash
set -e

docker run --name project-mgmt -p 23306:3306 -e MYSQL_ROOT_PASSWORD=admin -d mysql:8
