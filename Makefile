SHELL := /bin/bash

default: run

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

test-coverage:
	mvn clean org.jacoco:jacoco-maven-plugin:0.8.12:prepare-agent verify org.jacoco:jacoco-maven-plugin:0.8.12:report

check-coverage:
	open -a Google\ Chrome target/jacoco-report/index.html

coverage-badge-gen:
	python3 -m jacoco_badge_generator -j target/jacoco-report/jacoco.csv

.SLIENT:
.PHONY: default build run docker-build docker-run test test-coverage check-coverage coverage-badge-gen