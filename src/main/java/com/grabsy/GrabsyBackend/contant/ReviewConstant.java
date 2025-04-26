package com.grabsy.GrabsyBackend.contant;

public final class ReviewConstant {
    public static final String DEFAULT_SORT_FIELD = "createdAt";
    public static final String REVIEW_DESCRIPTION_SORT_FIELD = "reviewDescription";
    public static final String RATING_SORT_FIELD = "ratings";
    public static final String USER_ID_FIELD = "userId";
    public static final String CREATED_AT_FIELD = "createdAt";
    public static final String UPDATED_AT_FIELD = "updatedAt";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";
    public static final String ASC_SORT_DIRECTION = "ASC";
    public static final String DESC_SORT_DIRECTION = "DESC";


    // Review Limits
    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;

    public static final String REVIEW_DESCRIPTION_REQUIRED = "Review description must not be blank.";
    public static final String RATING_REQUIRED = "Rating must not be null.";
    public static final String RATING_VALIDATION = "Ratings Must be Greater Than 0 And Less Than or Equal to 5";
    public static final String USER_ID_REQUIRED = "User Id must not be null.";
    public static final String NO_REVIEWS_FOR_USER = "No reviews found for the given user.";
    public static final String REVIEW_NOT_FOUND_WITH_ID = "Review not found with id: ";
    public static final String REVIEW_NOT_FOUND = "Review not found.";

}
