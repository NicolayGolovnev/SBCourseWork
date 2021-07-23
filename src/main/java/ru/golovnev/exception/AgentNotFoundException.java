package ru.golovnev.exception;

/**
 * Класс исключения AgentNotFoundException с перенаследуемыми методами
 */
public class AgentNotFoundException extends RuntimeException{
    public AgentNotFoundException() {super();}

    public AgentNotFoundException(String message) {
        super(message);
    }

    public AgentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
