#!/bin/csh -f

rm -rf concept-map-app concept-map-app.zip

mvn install
mvn clean compile assembly:single
mkdir concept-map-app
cp target/concept-map-1.0-SNAPSHOT-jar-with-dependencies.jar concept-map-app
chmod +x concept-map-app/concept-map-1.0-SNAPSHOT-jar-with-dependencies.jar
mv concept-map-app/concept-map-1.0-SNAPSHOT-jar-with-dependencies.jar concept-map-app/concept-map.jar
cp -r data concept-map-app
zip -r concept-map-app.zip concept-map-app
rm -rf concept-map-app

echo "unzip concept-map-app.zip ; cd concept-map-app ; java -jar concept-map.jar"

