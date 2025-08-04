package ru.netology.nosql_mongodb.exception;

public class ErrorInputDataException extends RuntimeException {
    public ErrorInputDataException(String msg) {
        super(msg);
    }
}
