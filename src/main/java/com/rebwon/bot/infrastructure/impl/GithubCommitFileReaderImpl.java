package com.rebwon.bot.infrastructure.impl;

import com.rebwon.bot.infrastructure.GitHubCommitFileReader;
import com.rebwon.bot.infrastructure.GithubCommitFile;
import org.kohsuke.github.GHPullRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public final class GithubCommitFileReaderImpl implements GitHubCommitFileReader {

    private static final String AUTHORIZATION = "Authorization";
    private static final String ACCEPT = "Accept";

    private final RestTemplate restTemplate;
    private final String authorizationToken;
    private final String acceptHeaderValue;

    public GithubCommitFileReaderImpl(RestTemplate restTemplate,
        String authorizationToken,
        String acceptHeaderValue) {
        this.restTemplate = restTemplate;
        this.authorizationToken = authorizationToken;
        this.acceptHeaderValue = acceptHeaderValue;
    }

    @Override
    public GithubCommitFile[] read(GHPullRequest pullRequest) {
        String url = pullRequest.getUrl().toString() + "/files";
        HttpEntity<Object> header = new HttpEntity<>(makeHeaders());
        ResponseEntity<GithubCommitFile[]> entity = restTemplate.exchange(url, HttpMethod.GET, header,
            new ParameterizedTypeReference<GithubCommitFile[]>() {});
        return entity.getBody();
    }

    private HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, authorizationToken);
        headers.add(ACCEPT, acceptHeaderValue);
        return headers;
    }
}
