package com.rebwon.bot.application;

import java.io.IOException;
import org.kohsuke.github.GHEventPayload.PullRequest;
import org.kohsuke.github.GHPullRequest;

public final class PullRequestReviewProcessor {

    private final PullRequestReview reviewer;

    public PullRequestReviewProcessor(PullRequestReview review) {
        this.reviewer = review;
    }

    public void process(PullRequest pullRequest) throws Exception {
        if(verifyAction(pullRequest)) return;

        GHPullRequest pr = pullRequest.getPullRequest();

        if(isBlocked(pr)) return;

        reviewer.review(pr);
    }

    private boolean isBlocked(GHPullRequest pr) throws IOException {
        return pr.isMerged() || pr.isLocked();
    }

    private boolean verifyAction(PullRequest pullRequest) {
        return PullRequestActionStatus.isReviewWanted(pullRequest.getAction());
    }
}
