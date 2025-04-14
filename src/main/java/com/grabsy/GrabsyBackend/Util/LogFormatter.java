package com.grabsy.GrabsyBackend.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LogFormatter {
    public static String logFormattingTemplate(LinkedHashMap<String, String> messages){
        StringBuilder sb = new StringBuilder();
        messages.forEach((k, v) -> {
            sb.append(String.format("%n" + k + ": %s", v));
        });
        sb.append("\n");
        return sb.toString();
    }

    public static String formatPreHandleMessage(HttpServletRequest request) {
        LinkedHashMap<String,String> message = new LinkedHashMap<>();
        message.put("PreHandle Log", "");
        message.put("Request Method", request.getMethod());
        message.put("Query String", request.getQueryString());
        message.put("Timestamp", Instant.now().toString());
        return logFormattingTemplate(message);
    }

    public static String formatPostHandleMessage(HttpServletRequest request, HttpServletResponse response) {
        long startTime = (Long) request.getAttribute("startTime");
        long timeTaken = System.currentTimeMillis() - startTime;
        LinkedHashMap<String, String> message = new LinkedHashMap<>();

        message.put("PostHandle Log", "");
        message.put("Request URI", request.getRequestURI());
        message.put("Response Status", String.valueOf(response.getStatus()));
        message.put("Timestamp", Instant.now().toString());
        message.put("Time Taken To Complete", timeTaken + " ms");
        return logFormattingTemplate(message);
    }
}
