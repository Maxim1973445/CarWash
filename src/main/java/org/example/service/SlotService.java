package org.example.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SlotService {
    List<LocalDateTime> getEmptySlots(long StationId, LocalDate date);
    boolean slotIsEmpty(LocalDateTime dateTime);
}
