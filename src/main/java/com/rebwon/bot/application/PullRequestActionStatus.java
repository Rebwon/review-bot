package com.rebwon.bot.application;

public enum PullRequestActionStatus {
    CLOSED("closed"), REOPENED("reopened"),
    REVIEW_REQUESTED("review_requested"), REVIEW_REQUEST_REMOVED("review_request_removed"),
    ASSIGNED("assigned"), LABELED("labeled"), UNLABELED("unlabeled"),
    EDITED("edited"), SYNCHRONIZE("synchronize"),
    CONVERTED_TO_DRAFT("converted_to_draft"), READY_FOR_REVIEW("ready_for_review");

    private final String value;

    PullRequestActionStatus(String value) {
        this.value = value;
    }

    public static boolean isReviewWanted(String action) {
        return CLOSED.value.equals(action) || REVIEW_REQUESTED.value.equals(action)
            || ASSIGNED.value.equals(action) || LABELED.value.equals(action)
            || EDITED.value.equals(action) || UNLABELED.value.equals(action)
            || REVIEW_REQUEST_REMOVED.value.equals(action) || CONVERTED_TO_DRAFT.value.equals(action)
            || READY_FOR_REVIEW.value.equals(action);
    }
}
