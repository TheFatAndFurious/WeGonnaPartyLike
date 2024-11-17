package base;


import org.h2.util.Task;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTaskManager {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /**
     * This method will be used to run scheduled tasks (example: looking up the DB for birthdays every 12 hours)
     * @param task is a Runnable that is needed to pass a method to our scheduler as you can not directly use methods as parameters: they are not first class citizens. Also it would be possible to pass a method if it matched the Runnable interface ie: void return, no params)
     */

    //TODO: Make the time values parameters
    public void runService(Runnable task){
        executorService.scheduleAtFixedRate(task, 0, 2000L, TimeUnit.MILLISECONDS);
    }
}
