package com.grabsy.GrabsyBackend.dto;

import com.grabsy.GrabsyBackend.constant.common.LogConstant;
import com.grabsy.GrabsyBackend.util.LogFormatter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.web.method.HandlerMethod;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public <T extends RuntimeException> void setErrorMessage(T exception, HttpServletRequest servletRequest, HandlerMethod handlerMethod) {
        this.trace = exception.getMessage();
        this.requestPath = servletRequest.getMethod() + " " + servletRequest.getRequestURI();
        this.shortLogMessage = handlerMethod.getShortLogMessage();
    }

    public <T extends BindException> void setErrorMessageForRequestBodyValidation(T exception, HttpServletRequest
            servletRequest, HandlerMethod handlerMethod) {
        this.trace = exception.getMessage();
        this.requestPath = servletRequest.getMethod() + " " + servletRequest.getRequestURI();
        this.shortLogMessage = handlerMethod.getShortLogMessage();
    }

    public <T extends BindException> void setErrorMessageForRequestBodyValidation(T exception, Map<String, String>
            errors, HttpServletRequest servletRequest, HandlerMethod handlerMethod) {
        this.trace = exception.getMessage();
        this.requestPath = servletRequest.getMethod() + " " + servletRequest.getRequestURI();
        this.shortLogMessage = handlerMethod.getShortLogMessage();
    }

    public String getFormattedErrorMessage() {
        return formattedDefaultMessage();
    }

    public String getFormattedErrorMessage(LinkedHashMap<String, String> additionalInfo) {
        return formatMessageWithAdditionalInfoAtTheEnd(additionalInfo);
    }

    public String getFormattedErrorMessage(String title, LinkedHashMap<String, String> additionalInfo) {
        return formatMessageWithAdditionalInfoAtTheEnd(title, additionalInfo);
    }

    public String getFormattedErrorMessage(LinkedHashMap<String, String> preInfo,
                                           LinkedHashMap<String, String> additionalInfo) {
        return formatMessageWithAdditionalInfoAtTheEnd(preInfo, additionalInfo);
    }

    public String getFormattedErrorMessage(String title, LinkedHashMap<String, String> preInfo,
                                           LinkedHashMap<String, String> additionalInfo) {
        return formatMessageWithAdditionalInfoAtTheEnd(title, preInfo, additionalInfo);
    }

    private String formattedDefaultMessage() {
        LinkedHashMap<String, String> messages = new LinkedHashMap<>();
        messages.put(LogConstant.LABEL_TRACE, trace);
        messages.put(LogConstant.LABEL_TIMESTAMP, Instant.now().toString());
        messages.put(LogConstant.LABEL_REQUEST_PATH, requestPath);
        messages.put(LogConstant.LABEL_SHORT_LOG_MESSAGE, shortLogMessage);

        return LogFormatter.logFormattingTemplate("Exception Occurred!!", messages);
    }

    private String formattedDefaultMessage(String title) {
        LinkedHashMap<String, String> messages = new LinkedHashMap<>();
        messages.put(LogConstant.LABEL_TRACE, trace);
        messages.put(LogConstant.LABEL_TIMESTAMP, Instant.now().toString());
        messages.put(LogConstant.LABEL_REQUEST_PATH, requestPath);
        messages.put(LogConstant.LABEL_SHORT_LOG_MESSAGE, shortLogMessage);

        return LogFormatter.logFormattingTemplate(title, messages);
    }

    private String formattedDefaultMessage(LinkedHashMap<String, String> messages) {
        messages.put(LogConstant.LABEL_TRACE, trace);
        messages.put(LogConstant.LABEL_TIMESTAMP, Instant.now().toString());
        messages.put(LogConstant.LABEL_REQUEST_PATH, requestPath);
        messages.put(LogConstant.LABEL_SHORT_LOG_MESSAGE, shortLogMessage);

        return LogFormatter.logFormattingTemplate("Exception Occurred!!", messages);
    }

    private String formattedDefaultMessage(String title, LinkedHashMap<String, String> messages) {
        messages.put(LogConstant.LABEL_TRACE, trace);
        messages.put(LogConstant.LABEL_TIMESTAMP, Instant.now().toString());
        messages.put(LogConstant.LABEL_REQUEST_PATH, requestPath);
        messages.put(LogConstant.LABEL_SHORT_LOG_MESSAGE, shortLogMessage);

        return LogFormatter.logFormattingTemplate(title, messages);
    }

    private String formatMessageWithAdditionalInfoAtTheEnd(LinkedHashMap<String, String> additionalInfo) {
        StringBuilder sb = new StringBuilder(formattedDefaultMessage());

        if (additionalInfo != null && !additionalInfo.isEmpty())
            sb.append(LogFormatter.logFormattingTemplate(additionalInfo));

        return sb.toString();
    }

    private String formatMessageWithAdditionalInfoAtTheEnd(String additionalInfoTitle,
                                                           LinkedHashMap<String, String> additionalInfo) {
        StringBuilder sb = new StringBuilder(formattedDefaultMessage());

        if (additionalInfo != null && !additionalInfo.isEmpty())
            sb.append(LogFormatter.logFormattingTemplate(additionalInfoTitle, additionalInfo));

        return sb.toString();
    }

    private String formatMessageWithAdditionalInfoAtTheEnd(LinkedHashMap<String, String> preInfo,
                                                           LinkedHashMap<String, String> additionalInfo) {
        StringBuilder sb = new StringBuilder(formattedDefaultMessage(preInfo));

        if (additionalInfo != null && !additionalInfo.isEmpty()) sb.append(additionalInfo);

        return sb.toString();
    }

    private String formatMessageWithAdditionalInfoAtTheEnd(String title, LinkedHashMap<String, String> preInfo,
                                                           LinkedHashMap<String, String> additionalInfo) {
        StringBuilder sb = new StringBuilder(formattedDefaultMessage(title, preInfo));

        if (additionalInfo != null && !additionalInfo.isEmpty()) sb.append(additionalInfo);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "ErrorMessage{" + "trace='" + trace + '\'' + ", timestamp='" + Instant.now().toString() + '\'' + ", " +
                "requestPath='" + requestPath + '\'' + ", exceptionMessage='" + exceptionMessage + '\'' + ", " +
                "shortLogMessage='" + shortLogMessage + '\'' + '}';
    }
}
