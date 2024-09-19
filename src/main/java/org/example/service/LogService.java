package org.example.service;

import org.example.enums.LogStatus;

public interface LogService {
    Boolean writeLog(String log, LogStatus status, String operationName);
    String readLog();
}
