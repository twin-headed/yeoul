package com.myapp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

@Component
public class SessionListener implements HttpSessionListener {

    private static int activeSessions = 0;
    private static ArrayList<String> activeNames = new ArrayList<>();

    public void sessionCreated(HttpSessionEvent se) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        activeNames.add(authentication.getName());
        activeSessions++;
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        activeNames.remove(authentication.getName());
        activeSessions--;
    }

    public static int getActiveSessions() {
        return activeSessions;
    }

    public static ArrayList<String> getActiveNames() {
        return activeNames;
    }

}
