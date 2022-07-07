package com.rebwon.bot.infrastructure;

import com.rebwon.bot.application.AdditionalFileLengthReview;
import com.rebwon.bot.application.MaximumCommitCountReview;
import com.rebwon.bot.application.PullRequestReviewProcessor;
import com.rebwon.bot.infrastructure.impl.GitHubConnectorImpl;
import com.rebwon.bot.infrastructure.impl.GithubCommitFileReaderImpl;
import com.rebwon.bot.infrastructure.utils.JwtFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PullRequestReviewProcessor pullRequestService(
        @Value("${maximum.commit.count}") int maximumCommitCount,
        @Value("${additional.file.length}") int additionalFileLength,
        GitHubCommitFileReader commitFileReader) {
        return new PullRequestReviewProcessor(
            new AdditionalFileLengthReview(new MaximumCommitCountReview(maximumCommitCount),
                additionalFileLength, commitFileReader));
    }

    @Bean
    public GitHubCommitFileReader gitHubCommitFileReader(
        @Value("${github.authorization.token}") String authorizationToken,
        @Value("${github.accept.header}") String acceptHeaderValue
    ) {
        return new GithubCommitFileReaderImpl(restTemplate(), authorizationToken,
            acceptHeaderValue);
    }

    @Bean
    public GitHubAppProperties gitHubAppProperties() {
        return new GitHubAppProperties();
    }

    @Bean
    public GitHubConnector gitHubConnector(GitHubAppProperties properties) {
        return new GitHubConnectorImpl(new JwtFactory(), properties);
    }
}
