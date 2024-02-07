package ru.daedal.exception;

public class MessageTooLongException extends RuntimeException {
    public MessageTooLongException() {
        super("Message content too long. Max length - 2000 characters (Discord API restriction)");
    }
}
