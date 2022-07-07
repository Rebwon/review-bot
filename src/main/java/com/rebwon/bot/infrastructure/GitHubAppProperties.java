package com.rebwon.bot.infrastructure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public final class GitHubAppProperties {

    @Value("${github.app.id}")
    private String gitHubAppId;
    @Value("${github.app.tokenExpTime}")
    private Long tokenExpTime;
    @Value("${github.app.user}")
    private String gitHubAppInstallUser;
}
