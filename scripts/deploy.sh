REPOSITORY=/home/ec2-user/app/step2
PACKAGE_NAME=marketing

echo "> build file copy"

cp $REPOSITORY/zip/build/libs/*.war $REPOSITORY

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl ${PACKAGE_NAME} | grep war | awk '${print $1}')

echo "현재 구동 중인 애플리케이션 pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> 새 애플리케이션 배포"

WAR_NAME=$(ls -tr $REPOSITORY/*.war | tail -n 1)

echo "> WAR Name: $WAR_NAME"

echo "> $WAR_NAME 실행권한 추가"

chmod +x $WAR_NAME

echo "> $WAR_NAME 실행"

nohup java -jar $REPOSITORY/$WAR_NAME 2>&1 &