package com.grabsy.GrabsyBackend.Model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.HandlerMethod;

import java.time.Instant;
import java.util.Map;

public class ErrorMessage {

    private String trace, timestamp, requestPath, exceptionMessage, shortLogMessage;

    public ErrorMessage() {
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
        this.timestamp = Instant.now().toString();
        this.requestPath = servletRequest.getMethod() + " " + servletRequest.getRequestURI();
        this.shortLogMessage = handlerMethod.getShortLogMessage();
    }

    public String getFormattedErrorMessage() {
        return formattedDefaultMessage();
    }

    public String getFormattedErrorMessage(Map<String, String> additionalInfo) {
        return formatMessageWithAdditionalInfo(additionalInfo);
    }

    public String formattedDefaultMessage() {
        return String.format(
                "Error Message:%n" +
                        "------------------------%n" +
                        "Trace:             %s%n" +
                        "Timestamp:         %s%n" +
                        "Request Path:      %s%n" +
                        "Short Log Message: %s%n" +
                        "------------------------%n",
                trace, timestamp, requestPath, shortLogMessage);
    }

    public String formatMessageWithAdditionalInfo(Map<String, String> additionalInfo) {
        StringBuilder sb = new StringBuilder(getFormattedErrorMessage());

        if (additionalInfo != null && !additionalInfo.isEmpty()) {
            sb.append("Additional Information:%n");
            for (Map.Entry<String, String> entry : additionalInfo.entrySet()) {
                sb.append(String.format("   %s: %s%n", entry.getKey(), entry.getValue()));
            }
        }
        sb.append("------------------------");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "trace='" + trace + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", shortLogMessage='" + shortLogMessage + '\'' +
                '}';
    }
}
