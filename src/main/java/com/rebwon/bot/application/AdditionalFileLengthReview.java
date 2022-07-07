package com.rebwon.bot.application;

import com.rebwon.bot.infrastructure.GitHubCommitFileReader;
import com.rebwon.bot.infrastructure.GithubCommitFile;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHPullRequest;

@Slf4j
public final class AdditionalFileLengthReview extends PullRequestReviewDecorator {

    private final int additionalFileLength;
    private final GitHubCommitFileReader commitFileReader;

    public AdditionalFileLengthReview(PullRequestReview review, int additionalFileLength,
        GitHubCommitFileReader commitFileReader) {
        super(review);
        this.additionalFileLength = additionalFileLength;
        this.commitFileReader = commitFileReader;
    }

    @Override
    public void review(GHPullRequest pr) throws Exception {
        GithubCommitFile[] commitFiles = commitFileReader.read(pr);
        if (commitFiles == null) return;

        int additionalSize = calculateAdditionalFileLength(commitFiles);

        log.info("Additional Size : {} ", additionalSize);

        if(additionalSize > additionalFileLength) pr.comment(reviewMessage(pr, additionalSize));
        super.review(pr);
    }

    private int calculateAdditionalFileLength(GithubCommitFile[] commitFiles) {
        int result = 0;
        for(GithubCommitFile file : commitFiles) {
            String filename = file.getFilename();
            if(filename.endsWith(".java") && !(filename.contains("test"))) {
                result += file.getAdditions();
            }
        }
        return result;
    }

    private String reviewMessage(GHPullRequest pr, int additionalSize) throws IOException {
        return pr.getUser().getLogin() + "님 정말 하나의 pull request에서 "
            + additionalSize + "줄의 코드를 추가하실 생각이신가요? "
            + additionalFileLength + "줄 이하로 추가하실 수는 없나요?";
    }
}
