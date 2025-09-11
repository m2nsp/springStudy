package org.example.exception;

public class UserServiceException extends RuntimeException {

    private int statusCode;

    // 기본 메세지로 예외 생성
    public UserServiceException(String message) {
        super(message);
    }

    // 메세지 + 원인 예외를 함께 전달
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    // 추가적인 코드
    public UserServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {}
}
