package com.rebwon.bot.interfaces;

import com.rebwon.bot.application.PullRequestReviewProcessor;
import com.rebwon.bot.infrastructure.GitHubConnector;
import javax.servlet.http.HttpServletRequest;
import org.kohsuke.github.GHEventPayload.PullRequest;
import org.kohsuke.github.GitHub;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class WebhookHandler {

    private final PullRequestReviewProcessor processor;
    private final GitHubConnector connector;

    public WebhookHandler(PullRequestReviewProcessor processor,
        GitHubConnector connector) {
        this.processor = processor;
        this.connector = connector;
    }

    @GetMapping("${webhook.cron.url}")
    public void cronRequest() {

    }

    @PostMapping("${webhook.url}")
    public void processPullRequestEvent(HttpServletRequest request) throws Exception {
        GitHub gitHub = connector.connect();

        PullRequest pullRequest = gitHub.parseEventPayload(request.getReader(), PullRequest.class);

        processor.process(pullRequest);
    }
}
