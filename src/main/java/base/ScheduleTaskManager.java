package base;


import org.h2.util.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTaskManager {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public void runService(){
        executorService.scheduleAtFixedRate(()->{
            System.out.println("Coucou at: " + new java.util.Date());
        }, 0, 1000L, TimeUnit.MILLISECONDS);
    }
}
