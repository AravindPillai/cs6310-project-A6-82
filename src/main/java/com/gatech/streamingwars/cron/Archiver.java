package com.gatech.streamingwars.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Archiver {
    @Scheduled(cron = "0 * * ? * *")
    public void archive()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("We need to update this to move the data into archive DB " + strDate);
    }
}
