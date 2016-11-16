mvn clean compile war:war -P test -D maven.test.skip=true
/tools/resin/resin-search/bin/resin.sh stop
sleep 15
echo `ps aux|grep resin-search |grep -v grep  | awk '{print $2}'|xargs kill -9`
echo '... resin-search-maanger is stop now.'
rm -rf   /tools/resin/search/cms-web/faceye-cms-web*
rm -rf /tools/resin/logs/faceye-cms-web/*
cp target/faceye-cms-web.war  /tools/resin/search/cms-web/
/tools/resin/resin-search/bin/resin.sh start
