git pull

BASE_PATH=`pwd`

cd $BASE_PATH/projects/common-utils
./gradlew clean build uploadArchives

cd $BASE_PATH/projects/user-service-api
./gradlew clean build uploadArchives

echo "
cd $BASE_PATH/projects/user-service
./gradlew clean build 
sh start.sh

cd $BASE_PATH/projects/web
./gradlew clean build 
sh start.sh
" > /dev/null
