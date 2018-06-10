package org.davy.easyandroid;

import android.os.Bundle;
import android.os.SystemClock;

import org.davy.easyandroid.base.BaseActivity;
import org.davy.easyandroid.utils.ThreadUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThreadUtils.getInstance().addScheduledTask(new Task(0) {
            @Override
            public void run() {
                System.out.println("addScheduledTask");
            }
        });

        for (int i = 0; i < 100; i++) {
            ThreadUtils.getInstance().addScheduledTaskAtFixedRate(new Task(i));
        }
    }

    class Task implements Runnable {

        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("R:" + num + " " + SystemClock.elapsedRealtime());
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
