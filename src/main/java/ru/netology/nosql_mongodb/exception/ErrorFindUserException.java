package ru.netology.nosql_mongodb.exception;

public class ErrorFindUserException extends RuntimeException {
    public ErrorFindUserException(String msg) {
        super(msg);
    }
}
