package org.example.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class Slot {

    private long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long stationId;
    private Long orderId;
}
