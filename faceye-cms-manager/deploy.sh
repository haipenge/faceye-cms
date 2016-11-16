mvn clean compile war:war -P product -D maven.test.skip=true
/app/resin//resin-search-manager/bin/resin.sh stop
sleep 15
echo `ps aux|grep resin-search-manager |grep -v grep  | awk '{print $2}'|xargs kill -9`
echo '... resin-search-maanger is stop now.'
rm -rf   /data/deploy/faceye-cms-manager/faceye-cms-manager
rm -rf /data/logs/faceye-cms-manager/*
cp target/faceye-cms-manager.war  /data/deploy/faceye-cms-manager//
/app/resin/resin-search-manager/bin/resin.sh start
