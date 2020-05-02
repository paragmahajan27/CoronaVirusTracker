package com.tracker.coronavirus.controllers;

import com.tracker.coronavirus.model.CoronaVirusDTO;
import com.tracker.coronavirus.services.CoronaVirusCasesTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Controller
public class CoronaVirusCasesTrackingController {

    @Autowired
    CoronaVirusCasesTrackingService coronaVirusCasesTrackingService;

    @GetMapping("/")
    public String getCoronaData(Model model){
        CoronaVirusDTO coronaVirusDTO = coronaVirusCasesTrackingService.getCoronaData();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //statewise element
        coronaVirusDTO.getStatewise().stream().map(x->{
            LocalDateTime lastUpdt = LocalDateTime.parse(x.getLastupdatedtime(), formatter);
            long hrsDiff = ChronoUnit.HOURS.between(lastUpdt, now);
            long minDiff = ChronoUnit.MINUTES.between(lastUpdt, now);
            String msg = "";
            if (hrsDiff>0) {
                minDiff = minDiff - 60 * hrsDiff;
                msg = String.format("%s hr %s mins ago", Long.toString(hrsDiff), Long.toString(minDiff));
            }
            else
                msg = String.format("%s mins ago", Long.toString(minDiff));
            x.setLastupdatedtime(msg);
            return x;
        }).collect(Collectors.toList());

        // total element
        coronaVirusDTO.setTotal(coronaVirusDTO.getStatewise().stream()
                .filter(s->"Total".equals(s.getState()))
                .collect(Collectors.toList()).get(0));

        coronaVirusDTO.setStatewise(coronaVirusDTO.getStatewise().stream()
                .filter(s->!"Total".equals(s.getState()))
                .collect(Collectors.toList()));

        // Latest tested element
        coronaVirusDTO.setLatestTested(coronaVirusDTO.getTested()
                .stream().reduce((first, second) -> second).orElse(null));

        LocalDateTime lastUpdt = LocalDateTime.parse(coronaVirusDTO.getLatestTested().getUpdatetimestamp(), formatter);
        long hrsDiff = ChronoUnit.HOURS.between(lastUpdt, now);
        long minDiff = ChronoUnit.MINUTES.between(lastUpdt, now);
        String msg = "";
        if (hrsDiff>0) {
            minDiff = minDiff - 60 * hrsDiff;
            msg = String.format("%s hr %s mins ago", Long.toString(hrsDiff), Long.toString(minDiff));
        }
        else
            msg = String.format("%s mins ago", Long.toString(minDiff));
        coronaVirusDTO.getLatestTested().setUpdatetimestamp(msg);


        model.addAttribute("coronaAllData", coronaVirusDTO);
        return "CoronaDashboard";
    }
}
