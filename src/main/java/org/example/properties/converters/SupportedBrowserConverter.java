package org.example.properties.converters;

import org.example.enumeration.SupportedBrowsers;

public class SupportedBrowserConverter {
    public static SupportedBrowsers valueOfWebBrowser(String webBrowserName) {
        SupportedBrowsers result;
        switch (webBrowserName) {
            case "local_chrome":
                result = SupportedBrowsers.LOCAL_CHROME;
                break;
            case "local_firefox":
                result = SupportedBrowsers.LOCAL_FIREFOX;
                break;
            default:
                throw new IllegalArgumentException("Incorrect browser name");
        }
        return result;
    }
//    public static SupportedBrowsers valueOfWebBrowser(String webBrowserName){
//        return switch (webBrowserName){
//            case "local_chrome" -> SupportedBrowsers.LOCAL_CHROME;
//            case "local_firefox" -> SupportedBrowsers.LOCAL_FIREFOX;
//            default -> throw new IllegalArgumentException("Incorrect browser name");
//        };
//    }
}
