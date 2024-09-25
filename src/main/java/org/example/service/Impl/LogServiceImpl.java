package org.example.service.Impl;

import org.example.dao.Log;
import org.example.enums.LogStatus;
import org.example.repository.LogRepository;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Override
    public Boolean writeLog(String log, LogStatus status, String operationName) {

        List<Long> idList = new ArrayList<>();
        logRepository.findAll().forEach(e->idList.add(e.getId()));
        long count = idList.stream().max(Long::compareTo).orElse((long)0);

        Log logItem = new Log(
                ++count,
                LocalDateTime.now(),
                status,
                operationName,
                log
        );

//        logItem.setStatus(status);
//        logItem.setOperationName(operationName);
//        logItem.setEventTime(LocalDateTime.now());
//        logItem.setMessage(log);

        logRepository.save(logItem);
        return true;
    }

    @Override
    public String readLog() {
        return "";
    }

    private LogRepository logRepository;
    @Autowired
    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
}
