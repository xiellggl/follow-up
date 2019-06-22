

####版本发布方式
mvn release:prepare -Darguments="-DskipTests -DuseReleaseProfile=false"
mvn release:perform -Darguments="-Dmaven.test.skip=true -Dmaven.javadoc.skip=true"

#### release分支使用
mvn versions:set -DnewVersion=1.0.4
mvn versions:commit
mvn versions:revert