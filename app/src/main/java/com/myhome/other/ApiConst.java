package com.myhome.other;

public enum ApiConst {

    // REPLACE BY SERVER IP
    URL_BASE("http://192.168.8.92:8080/"),

    URL_ACCOUNT("/account"),
    URL_LOGIN("/login"),
    URL_REGISTER("/register"),
    URL_FORGOTTEN_PW("/forgotten"),

    URL_MEMBER("/member"),

    // General URLs
    URL_FETCH_ALL("get-all"),
    URL_INSERT("/insert"),
    URL_DELETE("/delete"),
    URL_UPDATE("/update"),

    FIELD_EMAIL("e-mail"),
    FIELD_PASSWORD("password"),
    FIELD_TOKEN("token"),
    FIELD_NEW_EMAIL("new-email"),
    FIELD_NEW_PASSWORD("new-password"),
    FIELD_ACCOUNT_ID("account-id"),

    LOG_ERROR("ERROR"),
    LOG_DEBUG("DEBUG");

    private final String s;

    ApiConst(String s) {
        this.s = s;
    }

    public String value() {
        return s;
    }
}
