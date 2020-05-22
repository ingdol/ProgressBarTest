package com.example.progressbartest;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity { //클래스를 상속받아 MainActivity 클래스를 작성한다.

    private ProgressDialog progress; //ProgressDialog를 progress로 선언한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreat 메소드의 매개변수는 앱의 이전 실행상태를 전달한다.
        super.onCreate(savedInstanceState); // 부모클래스의 onCreate를 호출한다.
        setContentView(R.layout.activity_main); //메인을 띄운다.
        progress = new ProgressDialog(this); //progress로 객체 생성한다.
    }

    public void start(View view) { //start 메소드를 호출한다.
        progress.setCancelable(false); //false : 프로그레스바가 끝나기 전까지는 cancel 안 된다.
        progress.setMessage("네트워크에서 다운로드 중입니다."); //메시지를 띄운다.
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //프로세스다이어로그 스타일을 수평적으로 증가하게 한다.
        progress.setProgress(0); //처음은 0으로 시작한다.
        progress.setMax(100); //최대치를 100으로 한다.
        progress.show(); //프로그레스 바가 보여진다.


    final Thread t = new Thread() { //스레드를 생성한다.
        @Override
        public void run() { //run함수를 실행한다.
            int time = 0; //시간을 초기화한다.
            while (time < 100) { //100까지 채우기 위해
                try {
                    sleep(200); //0.2초에 한번씩 증가한다.
                    //진행될 때 딜레이트를 주기 위해
                    time += 5; //5초씩 증가하여 총 20번 증가한다.
                    progress.setProgress(time); //진행상태에 관한 함수를 세팅한다.
                } catch (InterruptedException e) { //try~catch 문을 통해 해당 익셉션을 잡는다.
                    //자신들이 인터럽트를 받게 되면 현재 하던 일을 취소하고 즉시 리턴한다.
                    e.printStackTrace();
                }
            }
        }
    };
    t.start();
}
}
