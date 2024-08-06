VERSION=$(shell git describe --tags --abbrev=0)-$(shell git rev-parse --short HEAD)
IMAGE_TAG=${REGISTRY}/${REPOSITORY}:${VERSION}-${TARGETOS}-${TARGETARCH}

format:
	gofmt -s -w ./

get:
	go get

lint:
	golint

test:
	go test -v

build: format get
	CGO_ENABLED=0 GOOS=${TARGETOS} GOARCH=${TARGETARCH} go build -v -o kbot -ldflags "-X="github.com/suhovius/kbot/cmd.appVersion=${VERSION}

image:
	docker build . -t ${IMAGE_TAG}

push:
	docker push ${IMAGE_TAG}

clean:
	rm -rf kbot
	@IMG_TAG=${IMAGE_TAG}; \
	test ! -z "$$(docker images -q $${IMG_TAG})" && docker rmi -f $${IMG_TAG} || echo "Image does not exist. Nothing to delete"