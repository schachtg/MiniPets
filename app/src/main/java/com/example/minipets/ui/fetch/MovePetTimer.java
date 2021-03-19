package com.example.minipets.ui.fetch;

public class MovePetTimer implements Runnable {

    int timer_ms;
    TimerLogic logic;

    public MovePetTimer(TimerLogic logic, int timer){
        if(timer >= 1){
            this.timer_ms = timer;
        }
        else{
            this.timer_ms = 1;
        }
        this.logic = logic;
    }

    public void run(){
        try {
            Thread.sleep(timer_ms);
            if(this.logic != null){
                this.logic.timeIsUp();
            }
        } catch (InterruptedException e) {
            //This means the thread has been interrupted
            //this thread gets interrupted if the logic would like the timer to stop
            //this is not a problem
        }
    }

    public void setTimer_ms(int time){
        if(time > 0){
            this.timer_ms = time;
        }
    }
}
