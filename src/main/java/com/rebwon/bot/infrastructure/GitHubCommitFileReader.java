package com.rebwon.bot.infrastructure;

import org.kohsuke.github.GHPullRequest;

public interface GitHubCommitFileReader {

    GithubCommitFile[] read(GHPullRequest pullRequest);
}
