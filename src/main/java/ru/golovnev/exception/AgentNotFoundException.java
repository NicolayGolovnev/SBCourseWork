package ru.golovnev.exception;

public class AgentNotFoundException extends RuntimeException{
    public AgentNotFoundException() {
    }

    public AgentNotFoundException(String message) {
        super(message);
    }

    public AgentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
