package jsh.example.com.emnet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jsh.example.com.emnet.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


    }

    @Override
    protected void onResume() {
        super.onResume();
        new IntroTimer(3000).start();
    }

    private void nextActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //임시...
    class IntroTimer extends Thread{
        private long time;

        public IntroTimer(long time){
            this.time = time;
        }

        @Override
        public void run() {
            //super.run();
            try {
                sleep(time);
                nextActivity();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
