package com.t1t.keycloak.utils;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public final class UriUtils {

    private UriUtils() { }

    public static String constructURI(String uri, String contextPath) {
        return uriFinalSlashAppender(uri) + uriLeadingSlashRemover(contextPath);
    }

    public static String uriFinalSlashAppender(String uri) {
        if (!uri.endsWith("/")) return uri + "/";
        else return uri;
    }

    public static String uriFinalSlashRemover(String uri) {
        if (uri.endsWith("/")) {
            return uri.substring(0, uri.length() - 1);
        } else return uri;
    }

    public static String uriLeadingSlashRemover(String uri) {
        if (uri.startsWith("/")) {
            return uri.substring(1);
        } else return uri;
    }

    public static String uriLeadingSlashPrepender(String uri) {
        if (!uri.startsWith("/")) return "/" + uri;
        else return uri;
    }
}