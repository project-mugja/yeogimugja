package com.mugja.booking.controller;

import com.mugja.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class BookingProcessController {

    private final BookingService bookingService;

    @Autowired
    public BookingProcessController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/mugja/booking/addbooking")
    public String addBooking() {
        return "view/booking.html";
    }
}