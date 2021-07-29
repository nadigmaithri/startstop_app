package com.example.startstop;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tcounter;
    Button start,stop;
    int c=0;
    boolean running=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.button2);
        stop=(Button)findViewById(R.id.button);
        tcounter=(TextView)findViewById(R.id.textView);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.equals(start)){
            c =0;
            running=true;
            new MyCounter().start();
        }else if(v.equals(stop)){
            running=false;
        }
    }
    Handler handler=new Handler(){
        public void handleMessage(Message m){
            tcounter.setText(String.valueOf(m.what));
        }
    };
    class MyCounter extends Thread{
        public void run(){
            while(running){
                c++ ;
                handler.sendEmptyMessage(c);
                try{
                    Thread.sleep(1000);
                }
                catch (Exception e){
                }
            }
        }
    }
}