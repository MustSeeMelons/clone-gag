package com.strautins.CloneGag.exceptions;

/**
 * Helper for throwing REST exceptions.
 */
public class ExceptionManager {

    public enum ProcessCodes {
        OK(200),
        POST_NOT_FOUND(600),
        NO_USER(601),
        DB_ERROR(700),
        INTERNAL_ERROR(701);

        private int code;

        ProcessCodes(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
