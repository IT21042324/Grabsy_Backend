package com.grabsy.GrabsyBackend.model;

import com.grabsy.GrabsyBackend.util.LogFormatter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.HandlerMethod;

import java.time.Instant;
import java.util.LinkedHashMap;

public class ErrorMessage {

    private String trace, requestPath, exceptionMessage, shortLogMessage;

    public ErrorMessage() {
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getShortLogMessage() {
        return shortLogMessage;
    }

    public void setShortLogMessage(String shortLogMessage) {
        this.shortLogMessage = shortLogMessage;
    }

    public <T extends RuntimeException> void setErrorMessage(T exception, HttpServletRequest servletRequest,
                                                             HandlerMethod handlerMethod) {
        this.trace = exception.getMessage();
        this.requestPath = servletRequest.getMethod() + " " + servletRequest.getRequestURI();
        this.shortLogMessage = handlerMethod.getShortLogMessage();
    }

    public String getFormattedErrorMessage() {
        return formattedDefaultMessage();
    }

    public String getFormattedErrorMessage(LinkedHashMap<String, String> additionalInfo) {
        return formatMessageWithAdditionalInfo(additionalInfo);
    }

    public String formattedDefaultMessage() {
        LinkedHashMap<String, String> messages = new LinkedHashMap<>();
        messages.put("Error Message", "");
        messages.put("Trace", trace);
        messages.put("Timestamp", Instant.now().toString());
        messages.put("Request Path", requestPath);
        messages.put("Short Log Message", shortLogMessage);

        return LogFormatter.logFormattingTemplate(messages);
    }

    public String formatMessageWithAdditionalInfo(LinkedHashMap<String, String> additionalInfo) {
        StringBuilder sb = new StringBuilder(formattedDefaultMessage());

        if (additionalInfo != null && !additionalInfo.isEmpty())
           sb.append(formatMessageWithAdditionalInfo(additionalInfo));

        return sb.toString();
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "trace='" + trace + '\'' +
                ", timestamp='" + Instant.now().toString() + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", shortLogMessage='" + shortLogMessage + '\'' +
                '}';
    }
}
