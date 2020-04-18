package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Soccer extends AppCompatActivity {

    LinearLayout linearLayout2;
    Button addBtn;
    int count = 0;

    LayoutInflater layoutInflater;

    TextView mtvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activuty_real);

        //버튼 클릭시 선수 레이어 추가 생성


        //drawer=(DrawerLayout)findViewById(R.id.drawer);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2); // 플레이어 레이아웃의 부모 레이아웃(축구장배경)


        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE); //xml 파일을 객체화 시키기 위한 준비
        final ArrayList<View> testArray = new ArrayList<View>();
        addBtn = (Button) findViewById(R.id.addBtn);


        addBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          count++;
                                          if (count < 12) {

                                              View myview = layoutInflater.inflate(R.layout.player, null, true); //myview로 객체

                                              //메인 레이아웃(리니어 레이아웃)에 player.xml을 뷰로 추가
                                              linearLayout2.addView(myview, 150, 150);
                                              testArray.add(myview);


                                              for (int i = 0; i < count; i++) {

                                                  testArray.get(i).setOnTouchListener(new View.OnTouchListener() {


                                                      //유니폼 드래그 후 포지션 별 색상 변경
                                                      float oldXvalue;
                                                      float oldYvalue;

                                                      @Override
                                                      public boolean onTouch(View v, MotionEvent event) {
                            /*int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
                               int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();*/


                                                          //선수명 변경 텍스트
                                                          mtvTest = (TextView) v.findViewById(R.id.tvTest);
                                                          mtvTest.setOnClickListener(new View.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  show();
                                                              }
                                                          });


                                                          int parentWidth = ((ViewGroup) v.getParent()).getWidth();    // 부모 View 의 Width
                                                          int parentHeight = ((ViewGroup) v.getParent()).getHeight();    // 부모 View 의 Height


                                                          ImageView img = (ImageView) v.findViewById(R.id.FowardImageView2);


                                                          if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                                              //뷰 눌렀을 때

                                                              oldXvalue = event.getX();
                                                              oldYvalue = event.getY();
                                                              //  Log.i("Tag1", "Action Down X" + event.getX() + "," + event.getY());


                                                              Log.d("터치한 지점의 상대 좌표값", "oldXvalue : " + oldXvalue + " oldYvalue : " + oldYvalue);    // View 내부에서 터치한 지점의 상대 좌표값.
                                                              Log.d("좌측 상단이 되는 지점의 절대 좌표값", "v.getX() : " + v.getX());    // View 의 좌측 상단이 되는 지점의 절대 좌표값.
                                                              Log.d("view를 터치한 지점의 절대 좌표", "RawX : " + event.getRawX() + " RawY : " + event.getRawY());    // View 를 터치한 지점의 절대 좌표값.
                                                              Log.d(" View 의 Width, Height", "v.getHeight : " + v.getHeight() + " v.getWidth : " + v.getWidth());    // View 의 Width, Height

                                                          } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                                                              //뷰 이동 중

                                                              v.setX(event.getRawX() - oldXvalue);
                                                              v.setY(event.getRawY() - (oldYvalue + v.getHeight()));

                                                              //끝부분에서 붙어서 이동하기
                                                              if (v.getX() < 0) {
                                                                  v.setX(0);
                                                              } else if ((v.getX() + v.getWidth()) > parentWidth) {
                                                                  v.setX(parentWidth - v.getWidth());

                                                                  Log.i("부모너비", "parentWidth" + parentWidth);
                                                                  Log.i("너비", "v.getWidth" + v.getWidth());
                                                              }

                                                              if (v.getY() < 0) {
                                                                  v.setY(0);
                                                              } else if ((v.getY() + v.getHeight()) > parentHeight) {
                                                                  v.setY(parentHeight - v.getHeight());
                                                                  Log.i("부모높이", "parentHeight" + parentHeight);
                                                                  Log.i("높이", "v.getHeight" + v.getHeight());
                                                              }


                                                              if (v.getY() < 350) {
                                                                  //공격수 유니폼 변경
                                                                  img.setImageResource(R.drawable.forwards);

                                                              } else if (v.getY() > 350 && v.getY() < 1100) {
                                                                  //미드필더 유니폼 변경
                                                                  img.setImageResource(R.drawable.midfielders);

                                                              } else if (v.getY() > 1100) {
                                                                  // 수비수 유니폼 변경
                                                                  img.setImageResource(R.drawable.defenders);

                                                                  if (v.getY() > 1300 && v.getX() > 150 && v.getX() < 750) {
                                                                      //골키퍼 유니폼 변경
                                                                      img.setImageResource(R.drawable.keepers);

                                                                      Log.i("keeper", "키퍼");
                                                                  }
                                                              }
                                                              //  Log.i("Tag2", "Action Down " + me.getRawX() + "," + me.getRawY());
                                                          } else if (event.getAction() == MotionEvent.ACTION_UP) {
                                                              //뷰에서 손을 뗐을 때

                                                              Log.d("좌측 상단이 되는 지점의 절대 좌표값", "v.getX() : " + v.getX() + "v.getY()" + v.getY());    // View 의 좌측 상단이 되는 지점의 절대 좌표값.
                                                              Log.d("view를 터치한 지점의 절대 좌표", "RawX : " + event.getRawX() + " RawY : " + event.getRawY());    // View 를 터치한 지점의 절대 좌표값.

                                                              if (v.getY() < 350) {
                                                                  //공격수 유니폼 변경
                                                                  img.setImageResource(R.drawable.forwards);
                                                              } else if (v.getY() > 350 && v.getY() < 1100) {
                                                                  //미드필더 유니폼 변경
                                                                  Log.i("y좌표", "250이상 900이하");

                                                                  img.setImageResource(R.drawable.midfielders);

                                                              } else if (v.getY() > 1100) {
                                                                  // 수비수 유니폼 변경
                                                                  img.setImageResource(R.drawable.defenders);
                                                                  if (v.getY() > 1300 && v.getX() > 150 && v.getX() < 750) {
                                                                      //골키퍼 유니폼 변경
                                                                      img.setImageResource(R.drawable.keepers);
                                                                      Log.i("keeper", "키퍼");
                                                                  }
                                                              }

                                                          }

                                                          return true;

                                                      }

                                                  });
                                              }
                                          } else {
                                              Toast.makeText(Soccer.this, "11명이상", Toast.LENGTH_SHORT).show();
                                          }

                                      }

                                  }

        );
    }
    void show(){
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선수명 변경");
        builder.setMessage("선수 이름을 입력하세요");
        builder.setView(edittext);
        builder.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mtvTest.setText(edittext.getText().toString());
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}
