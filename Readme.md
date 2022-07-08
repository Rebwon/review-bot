## GitHub Pull Request Review Bot

### Build & Run

`$ ./gradlew package` 

`$ java -jar /build/libs/*.jar`

### Usage

1. Make your github app and input your github app-id in application.properties property `github.app.id`
2. Input your github organization name in application.properties property `github.app.user`
3. Input your github authorization token in application.properties property `gituhb.authorization.token`

### Example

Maximum Commit Size Review

![commitCount](/image/commitCount.png)

Additional Commit File Length Review

![additionalFileLength](/image/additionalFileLength.png)

### Configuration options

| Option | Required | description                                                                      |
|--------|----------|----------------------------------------------------------------------------------|
| maximum.commit.count   | required | Specifies the maximum number of commits to be restricted by Pull Request Review. |
| additional.file.length   | required | Limits the number of code lines added in a committed file.                       |
| webhook.url   | required | This is the webhook url to receive a Pull Request response.                      |
| webhook.cron.url   | optional | This is url to receive the cron request.                                         |