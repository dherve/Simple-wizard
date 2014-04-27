package com.simple.wizard.utils;

import org.apache.commons.lang3.StringUtils;

public enum CommandKey {
    CANCEL_ACTION {
        @Override
        public String toString() {
            return "cancel";
        }
    },

    NEXT_VIEW_ACTION {
        @Override
        public String toString() {
            return "next";
        }
    },

    PREVIOUS_VIEW_ACTION {
        @Override
        public String toString() {
            return "previous";
        }
    },

    FINISH_ACTION {
        @Override
        public String toString() {
            return "finish";
        }
    },
    BROWSE_ACTION {
        @Override
        public String toString() {
            return "browse";
        }
    };

    public abstract String toString();

    public static CommandKey getCommand(final String value) {

        if (StringUtils.equals(value, CANCEL_ACTION.toString())) {
            return CANCEL_ACTION;
        }
        if (StringUtils.equals(value, NEXT_VIEW_ACTION.toString())) {
            return NEXT_VIEW_ACTION;
        }
        if (StringUtils.equals(value, PREVIOUS_VIEW_ACTION.toString())) {
            return PREVIOUS_VIEW_ACTION;
        }
        if (StringUtils.equals(value, FINISH_ACTION.toString())) {
            return FINISH_ACTION;
        }
        if (StringUtils.equals(value, BROWSE_ACTION.toString())) {
            return BROWSE_ACTION;
        }
        throw new IllegalArgumentException("NO COMMAND FOUND FOR THE VALUE "
                + value + "PROVIDED");
    }
}
