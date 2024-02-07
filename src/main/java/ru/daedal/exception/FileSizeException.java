package ru.daedal.exception;

public class FileSizeException extends RuntimeException {
    public FileSizeException() {
        super("File size is too big! Max file upload size is 25.0 MB");
    }
}
