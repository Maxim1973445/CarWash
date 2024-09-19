package org.example.dao;


import lombok.Getter;
import lombok.Setter;
import org.example.enums.LogStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="logs")
@Setter
@Getter
public class Log {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private LocalDateTime eventTime;
    @Enumerated(EnumType.STRING)
    private LogStatus status;
    private String operationName;
    private String message;
}
