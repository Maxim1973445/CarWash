package org.example.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.LogStatus;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "logs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    private long id;
    private LocalDateTime eventTime;
    @Enumerated(EnumType.STRING)
    private LogStatus status;
    private String operationName;
    private String message;
}