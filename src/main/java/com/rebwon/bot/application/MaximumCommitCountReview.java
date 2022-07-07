package com.rebwon.bot.application;

import java.io.IOException;
import org.kohsuke.github.GHPullRequest;

public final class MaximumCommitCountReview implements PullRequestReview {

    private final int maximumCommitCount;

    public MaximumCommitCountReview(int maximumCommitCount) {
        this.maximumCommitCount = maximumCommitCount;
    }

    @Override
    public void review(GHPullRequest pr) throws Exception {
        if(pr.getCommits() > maximumCommitCount) pr.comment(reviewMessage(pr));
    }

    private String reviewMessage(GHPullRequest pr) throws IOException {
        return pr.getUser().getLogin() + "님, 커밋 "
            + pr.getCommits() + "개가 하나의 pull request에 포함되셨군요! 꼭 "
            + maximumCommitCount + "개를 넘겨야 하나요?";
    }
}
