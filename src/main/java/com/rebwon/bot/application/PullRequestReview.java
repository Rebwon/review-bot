package com.rebwon.bot.application;

import org.kohsuke.github.GHPullRequest;

public interface PullRequestReview {

    void review(GHPullRequest pullRequest) throws Exception;
}
