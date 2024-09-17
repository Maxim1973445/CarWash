package org.example.dao;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="logs")
public class Log {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private Date eventTime;
    private String operationName;
    private String message;
}
