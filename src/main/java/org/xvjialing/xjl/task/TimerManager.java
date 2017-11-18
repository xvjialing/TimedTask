package org.xvjialing.xjl.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerManager {
    //时间间隔
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
//    private static final long PERIOD_DAY =1000;
    private final Timer timer;

    private int hour,minute;
    private String tag;
    private boolean isRepeat;

    public TimerManager(int hour, int minute, String tag, boolean isRepeat) {
        this.hour = hour;
        this.minute = minute;
        this.tag = tag;
        this.isRepeat = isRepeat;

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Date date=calendar.getTime();
//        System.out.println(date);
//        System.out.println("before 方法比较："+date.before(new Date()));
        //如果第一次执行定时任务的时间 小于 当前的时间
        //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
        if (date.before(new Date())) {
            calendar.add(Calendar.HOUR_OF_DAY,1);
            date=calendar.getTime();
//            System.out.println(date);
        }


        timer = new Timer();

        if (isRepeat){
            timer.schedule(new RegularTimerTask(tag),date,PERIOD_DAY);
            System.out.println("timerManager repeat");
        }else {
            timer.schedule(new RegularTimerTask(tag),date);
        }
    }

    public void stopTimer(){
        timer.cancel();
    }


}
