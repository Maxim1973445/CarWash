package org.example.service.Impl;

import org.example.dao.*;
import org.example.enums.OrderStatus;
import org.example.service.*;
import org.example.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SlotServiceImpl implements SlotService {
    private static final Logger log = LoggerFactory.getLogger(SlotServiceImpl.class);

    private final StationService stationService;
    private final OrderService orderService;

    @Autowired
    public SlotServiceImpl(StationServiceImpl stationService, OrderServiceImpl orderService) {
        this.stationService = stationService;
        this.orderService = orderService;
    }

    @Override
    public List<LocalDateTime> getEmptySlots(long StationId, LocalDate date) {
        Station station = stationService.getStationById(StationId);
        if (station == null) {
            return null;
        }
        LocalDateTime openTime = LocalDateTime.of(LocalDate.now(), station.getOpenTime());
        LocalDateTime closeTime = LocalDateTime.of(LocalDate.now(), station.getCloseTime());
        List<LocalDateTime> slots = new ArrayList<>();
        while (openTime.isBefore(closeTime)) {
            slots.add(openTime);
            openTime = openTime.plusHours(1);
        }
        List<LocalDateTime> deltaSlots = new ArrayList<>(slots);
        List<Order> orders = station.getOrders().stream().filter(order -> order.getOrderStatus() != OrderStatus.CANCELLED).toList();

        for (int i = 0; i < orders.size(); i++) {
            LocalDateTime orderStartTime = orders.get(i).getStartTime();
            deltaSlots.removeIf(slot -> slot.equals(orderStartTime));
        }
        List<LocalDateTime> emptySlots = new ArrayList<>();
        for (int i = 0; i < deltaSlots.size(); i++) {
            if (deltaSlots.get(i).isAfter(LocalDateTime.now())) {
                emptySlots.add(deltaSlots.get(i));
            }
        }
        return emptySlots;
    }

    public boolean slotIsEmpty(LocalDateTime dateTime) {
        if (orderService.getAllOrders().stream().anyMatch(item -> item.getStartTime() == dateTime))
            return false;
        return true;
    }
}
