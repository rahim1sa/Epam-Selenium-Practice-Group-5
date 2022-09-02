package org.example.properties.converters;

import org.example.enumeration.SupportedBrowsers;

import static org.example.enumeration.SupportedBrowsers.LOCAL_CHROME;
import static org.example.enumeration.SupportedBrowsers.LOCAL_FIREFOX;

public class SupportedBrowserConverter {
    public static SupportedBrowsers valueOfWebBrowser(String webBrowserName) {
        return switch (webBrowserName) {
            case "local_chrome" -> LOCAL_CHROME;
            case "local_firefox" -> LOCAL_FIREFOX;
            default -> throw new IllegalArgumentException("Incorrect browser name");
        };
    }

}
