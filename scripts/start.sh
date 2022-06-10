#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh #profile.sh를 import

REPOSITORY=/home/ec2-user/app/step3
PACKAGE_NAME=marketing

echo "> build file copy"

cp $REPOSITORY/zip/*.war $REPOSITORY

echo "> 새 애플리케이션 배포"

WAR_NAME=$(ls -tr $REPOSITORY/ | grep war | tail -n 1)

echo "> WAR Name: $WAR_NAME"

echo "> $WAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $WAR_NAME profile=$IDLE_PROFILE 로 실행합니다."

nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE $REPOSITORY/$WAR_NAME > $REPOSITORY./nohup.out 2>&1 $