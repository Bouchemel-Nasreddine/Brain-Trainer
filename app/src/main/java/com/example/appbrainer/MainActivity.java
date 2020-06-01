package com.example.appbrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] remarks = {"Need to get more", "Need to get more", "you're quite there", "you're quite there",
                            "Not bad, keep it up", "almost there, keep it up", "GOOD!, keep it up", "Very Good!",
                            "excellent, you're great", "YOU ARE A HERO", "YOU SMASHED IT"};

        final TextView score = findViewById(R.id.score);
        final TextView task = findViewById(R.id.task);
        final TextView timer = findViewById(R.id.timer);
        final TextView sug1 = findViewById(R.id.sug1);
        final TextView sug2 = findViewById(R.id.sug2);
        final TextView sug3 = findViewById(R.id.sug3);
        final TextView sug4 = findViewById(R.id.sug4);
        final TextView score_description = findViewById(R.id.score_description);
        final Button playBtn = findViewById(R.id.playBtn);
        final Button startBtn = findViewById(R.id.startBtn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sug1.setEnabled(false);
                sug2.setEnabled(false);
                sug3.setEnabled(false);
                sug4.setEnabled(false);
                startBtn.setVisibility(View.INVISIBLE);
            }
        });

        final CountDownTimer Timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                startBtn.setText(remarks[Integer.parseInt(score.getText().toString().split("/")[0])-1]+
                                "\n" +"\n"+ "wanna play again");
                ResetUI(score, task, sug1, sug2, sug3, sug4, score_description);
                startBtn.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "time's up", Toast.LENGTH_SHORT).show();
            }
        };

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sug1.setEnabled(true);
                sug2.setEnabled(true);
                sug3.setEnabled(true);
                sug4.setEnabled(true);
                Toast.makeText(getApplicationContext(), "GO!", Toast.LENGTH_SHORT).show();
                UpdateUI(task,sug1,sug2,sug3,sug4);
                Timer.start();
            }
        });

        sug1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = task.getText().toString();
                int answer = Integer.parseInt(m.split(" +")[0])+Integer.parseInt(m.substring(m.indexOf(" + ")+3));
                if (Integer.parseInt((String) sug1.getText())==answer) {
                    increaseScore(score, score_description, remarks);
                    Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                }
                UpdateUI(task, sug1, sug2, sug3, sug4);
            }
        });

        sug2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = task.getText().toString();
                int answer = Integer.parseInt(m.split(" +")[0])+Integer.parseInt(m.substring(m.indexOf(" + ")+3));
                if (Integer.parseInt((String) sug2.getText())==answer) {
                    increaseScore(score, score_description, remarks);
                    Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                }
                UpdateUI(task, sug1, sug2, sug3, sug4);
            }
        });

        sug3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = task.getText().toString();
                int answer = Integer.parseInt(m.split(" +")[0])+Integer.parseInt(m.substring(m.indexOf(" + ")+3));
                if (Integer.parseInt((String) sug3.getText())==answer) {
                    increaseScore(score, score_description, remarks);
                    Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                }
                UpdateUI(task, sug1, sug2, sug3, sug4);
            }
        });

        sug4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = task.getText().toString();
                int answer = Integer.parseInt(m.split(" +")[0])+Integer.parseInt(m.substring(m.indexOf(" + ")+3));
                if (Integer.parseInt((String) sug4.getText())==answer) {
                    increaseScore(score, score_description, remarks);
                    Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                }
                UpdateUI(task, sug1, sug2, sug3, sug4);
            }
        });

    }

    public void UpdateUI (TextView taskView, TextView sug1, TextView sug2,
                          TextView sug3, TextView sug4) {
        Task task = new Task();
        task.setSug();
        taskView.setText(task.getOp1() +" + "+ task.getOp2());

        sug1.setText(Integer.toString(task.getSug1()));
        sug2.setText(Integer.toString(task.getSug2()));
        sug3.setText(Integer.toString(task.getSug3()));
        sug4.setText(Integer.toString(task.getSug4()));







    }

    public void ResetUI (TextView score, TextView taskView, TextView sug1, TextView sug2,
                         TextView sug3, TextView sug4, TextView score_description ) {
        score.setText("0/00");
        taskView.setText("15 + 15");
        sug1.setText("45");
        sug2.setText("16");
        sug3.setText("07");
        sug4.setText("30");
        sug1.setEnabled(false);
        sug2.setEnabled(false);
        sug3.setEnabled(false);
        sug4.setEnabled(false);
        score_description.setText("Need to get some points");
    }

    public class Task {
        private Random rand = new Random();
        private int op1 = rand.nextInt(90);
        private int op2 = rand.nextInt(100-op1);
        private int[] sug = new int[4];

        public void setSug() {
            this.sug = makeSugs(op1+op2);
        }

        public int getOp1() {
            return op1;
        }

        public int getOp2() {
            return op2;
        }

        public int getSug1() {
            return sug[0];
        }

        public int getSug2() {
            return sug[1];
        }

        public int getSug3() {
            return sug[2];
        }

        public int getSug4() {
            return sug[3];
        }
    }

    public int[] makeSugs(int answer) {
        Random rand = new Random();
        int[] mSug = new int [4];
        mSug[rand.nextInt(4)] = answer;
        for (int i=0; i<mSug.length; i++) {
            if (mSug[i]!=answer && answer<80) {
                mSug[i] = rand.nextInt(40) + Math.abs(answer - 20);
            } else if (mSug[i]!=answer) {
                mSug[i] = rand.nextInt(40) + Math.abs(answer - 40);
            }
        }
        return mSug;
    }

    public void increaseScore(TextView score, TextView score_description, String[] remarks) {
        score.setText(Integer.toString(Integer.parseInt(score.getText().toString().split("/")[0])+1)+"/"+"10");
        int i = Integer.parseInt(score.getText().toString().split("/")[0])-1;
        if (i<10) {
            score_description.setText(remarks[i]);
        } else {
            score_description.setText(remarks[remarks.length-1]);
        }

    }
}
