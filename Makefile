build:
	mvn clean package

run: build
	java -jar target/mars-rover*.jar

docker-build:
	docker build --no-cache=true -f Dockerfile.exercise -t mars-rover .

docker-run: docker-build
	docker run -i --rm -t mars-rover

test:
	mvn clean test