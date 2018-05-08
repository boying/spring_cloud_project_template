git pull

kill -9 `ps aux | grep gradlew | grep user-service | awk '{print $2}' `
kill -9 `ps aux | grep gradlew | grep web | awk '{print $2}' `

BASE_PATH=`pwd`


cd $BASE_PATH/projects/user-service
sh start.sh


cd $BASE_PATH/projects/web
sh start.sh

ps aux | grep gradlew | grep user-service
ps aux | grep gradlew | grep web

cd $BASE_PATH
