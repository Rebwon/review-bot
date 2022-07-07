package com.rebwon.bot.infrastructure.impl;

import com.rebwon.bot.infrastructure.GitHubAppProperties;
import com.rebwon.bot.infrastructure.GitHubConnector;
import com.rebwon.bot.infrastructure.utils.JwtFactory;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public final class GitHubConnectorImpl implements GitHubConnector {

    private final JwtFactory jwtFactory;
    private final GitHubAppProperties properties;

    public GitHubConnectorImpl(JwtFactory jwtFactory, GitHubAppProperties properties) {
        this.jwtFactory = jwtFactory;
        this.properties = properties;
    }

    @Override
    public GitHub connect() throws Exception {
        String jwt = jwtFactory.createJWT(properties.getGitHubAppId(),
            properties.getTokenExpTime());
        GitHub gitHub = new GitHubBuilder().withJwtToken(jwt).build();
        GHAppInstallation appInstallation = gitHub.getApp()
            .getInstallationByUser(properties.getGitHubAppInstallUser());
        return new GitHubBuilder()
            .withAppInstallationToken(appInstallation.createToken().create().getToken())
            .build();
    }
}
