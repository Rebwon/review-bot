package com.rebwon.bot.infrastructure;

import org.kohsuke.github.GitHub;

public interface GitHubConnector {

    GitHub connect() throws Exception;
}
