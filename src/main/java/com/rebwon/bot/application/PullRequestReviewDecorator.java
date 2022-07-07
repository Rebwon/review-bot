package com.rebwon.bot.application;

import org.kohsuke.github.GHPullRequest;

public abstract class PullRequestReviewDecorator implements PullRequestReview {

    private final PullRequestReview pullRequestReview;

    public PullRequestReviewDecorator(PullRequestReview pullRequestReview) {
        this.pullRequestReview = pullRequestReview;
    }

    @Override
    public void review(GHPullRequest pullRequest) throws Exception {
        pullRequestReview.review(pullRequest);
    }
}
