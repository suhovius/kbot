# kbot
DevOps Course test application

### Link to telegram bot
[kbot](https://t.me/test_284709_bot)

### Setup
* Create your own telegram bot with BotFather bot. Use command `/newbot`. It will ask for bot name and bot telegram user name. Provide these values.
In final response message there will be API token, click at this message once to copy this token.
* Call command `read -s TELE_TOKEN` to read from the input, use insert from buffer hotkey aka `CMD+V`, `CTRL+V`, and hit enter
* Check saved token with `echo $TELE_TOKEN` terminal command
* Use `export TELE_TOKEN` to export this variable into the environment
* start Kbot with `./kbot start` command
* Open your telegram bot link that was obtained at the BotFather and start typing some bot commands

### Build Generation
* Each make and build command has such environment variables `TARGETOS`, `TARGETARCH` they define target operation system and processor architecture. See variables defaults at `Makefile`.
* See supported os and architecture values here [Golang Environment](https://go.dev/doc/install/source#environment)
* `TARGETOS=darwin TARGETARCH=arm64 make build` - this example command will prepare application binary file
* `make image` - create docker image
* `make push` - push docker image into registry
* `make clean` - remove binary and docker image

### Run application via Docker
* `docker run registry.digitalocean.com/suhovius/kbot:v1.0.5-07d3380-amd64`

### Supported Commands

`/start hello` - see bot version. Output example: `'Hello I'm Kbot 1.0.2!'`

`/start rand` - generate random number. Output example: `'Here is some random number 3!'`