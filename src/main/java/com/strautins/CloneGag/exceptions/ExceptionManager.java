package com.strautins.CloneGag.exceptions;

/**
 * Helper for throwing REST exceptions.
 */
public class ExceptionManager {

    public enum ErrorCodes {
        POST_NOT_FOUND(600),
        NO_USER(601),
        DB_ERROR(700),
        INTERNAL_ERROR(701);

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

    public static void DBError(Exception e) throws RestException {
        throw new RestException(ErrorCodes.DB_ERROR.getCode(), "Database error.", e);
    }

    public static RestException InternalError(Exception e) {
        return new RestException(ErrorCodes.INTERNAL_ERROR.getCode(), "Internal error.", e);
    }

}
