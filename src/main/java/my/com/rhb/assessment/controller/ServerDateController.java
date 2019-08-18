package my.com.rhb.assessment.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import my.com.rhb.assessment.model.ServerDate;

@RestController
public class ServerDateController {

    /**
     * GET server date.
     *
     * @return the server date in json format
     */
    @GetMapping("/serverDate")
    public ServerDate getServerDate() {
        ServerDate serverDate = new ServerDate();

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localTime = LocalDate.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTime = localTime.format(formatter);

        serverDate.setServerDate(formattedTime);
        return serverDate;
    }

}