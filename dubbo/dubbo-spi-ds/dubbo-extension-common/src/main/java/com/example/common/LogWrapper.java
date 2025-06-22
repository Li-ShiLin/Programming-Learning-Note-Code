package com.example.common;

public class LogWrapper {

    public LogWrapper() {
        // 无参构造函数，供Dubbo SPI使用
    }

    public void log(String message) {
        System.out.println("[Logger] " + message);
    }
}

// 简单的日志包装器，不需要作为SPI扩展点