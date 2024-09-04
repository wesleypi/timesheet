package com.wesleypi.timesheet.controllers;

import com.wesleypi.timesheet.models.Entry;
import com.wesleypi.timesheet.models.Period;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet")
public interface DayController {


    @GetMapping("/{year}/{month}")
    List<Entry> getMonthDays(@PathVariable Integer year, @PathVariable Integer month);

    @PutMapping("/{year}/{month}/{day}")
    Period putDay(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day, @RequestBody Entry entry);
}

