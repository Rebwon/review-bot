package com.rebwon.bot.infrastructure;

import lombok.Data;

@Data
public final class GithubCommitFile {

    private String sha;
    private String filename;
    private String status;
    private int additions;
    private int deletions;
    private int changes;
}
