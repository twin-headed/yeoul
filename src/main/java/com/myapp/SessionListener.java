package com.myapp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionListener implements HttpSessionListener {

    private static int activeSessions = 0;
    private static ArrayList<String> activeNames = new ArrayList<>();

    private static Map<HttpSession, String> sessionPairs = new HashMap<HttpSession, String>();

    public void sessionCreated(HttpSessionEvent se) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        sessionPairs.put(se.getSession(),authentication.getName());
        activeNames.add(authentication.getName());
        activeSessions++;
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        activeNames.remove(sessionPairs.get(se.getSession()));
        sessionPairs.remove(se.getSession());
        if(activeSessions > 0) {
            activeSessions--;
        }
        if(activeSessions == 0 || (activeNames.size() == 0)) {
            activeNames.clear();
            activeSessions = 0;
        }
    }

    public static int getActiveSessions() {
        return activeSessions;
    }

    public static ArrayList<String> getActiveNames() {
        return activeNames;
    }

}
