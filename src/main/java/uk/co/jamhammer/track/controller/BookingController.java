package uk.co.jamhammer.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.jamhammer.track.model.Booking;
import uk.co.jamhammer.track.service.BookingService;
import uk.co.jamhammer.track.service.TaskService;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/bookings")
    public String bookingList(Model model) {
        model.addAttribute("bookings", bookingService.findAll());
        model.addAttribute("booking", new Booking());
        model.addAttribute("tasks", taskService.findAll());
        return "booking/list";
    }

    @PostMapping("/booking")
    public String bookingPost(Booking booking,
                              @RequestParam long taskId,
                              @RequestHeader(HttpHeaders.REFERER) final String referer) {
        if (taskService.findById(taskId).isPresent()) {
            booking.setTask(taskService.findById(taskId).get());
        }
        bookingService.save(booking);
        return "redirect:" + referer;
    }
}
