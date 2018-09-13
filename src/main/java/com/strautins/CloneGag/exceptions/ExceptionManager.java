package com.strautins.CloneGag.exceptions;

/**
 * Helper for throwing exceptions.
 */
public class ExceptionManager {

    public enum ErrorCodes {
        POST_NOT_FOUND(600),
        NO_USER(601);

        private int code;

        ErrorCodes(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public static void PostNotFoundException() throws RestException {
        throw new RestException(ErrorCodes.POST_NOT_FOUND.getCode(), "Post not found.");
    }

    public static void NoUserException() throws RestException {
        throw new RestException(ErrorCodes.NO_USER.getCode(), "User is not logged in.");
    }
}
