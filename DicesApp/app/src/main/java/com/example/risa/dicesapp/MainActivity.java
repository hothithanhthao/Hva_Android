package com.example.risa.dicesapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentImageIndex = 0;
    private TextView scoreText;
    private TextView highScoreText;
    private int score =0;
    private int hightscore = 0;
    private  ListView listview;
    private int[] mImageNames;
    private ImageView mImageView;
    private FloatingActionButton upButton;
    private FloatingActionButton downButton;
    private List<Throw>mThrow;
    private ArrayAdapter mAdapter;

    private int n;
    Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create variables
        scoreText = (TextView)findViewById(R.id.textView5) ;
        highScoreText = (TextView)findViewById(R.id.textView7);
        listview = (ListView) findViewById(R.id.listView);
        upButton = findViewById(R.id.floatingActionButton2);
        downButton = findViewById(R.id.floatingActionButton);
        mImageView = findViewById(R.id.imageView2);
        mThrow = new ArrayList<>();

       updateUI();
       rand = new Random();
       mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        // Define what happens when the user clicks the "Up" button
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = rand.nextInt(6)+1;
                String mshowText = "Throw is "+ Integer.toString(n);
                currentImageIndex++;
                if (currentImageIndex >= mImageNames.length) {
                    currentImageIndex = 0;
                    //currentImageIndex++;
                }
                mImageView.setImageResource(mImageNames[currentImageIndex]);
                //Create the new throw
                Throw newThrow = new Throw(mshowText);
                mThrow.add(0,newThrow);
                //Update the UI
                updateUI();
                Snackbar.make(v, checkAnswer(n), Snackbar.LENGTH_LONG).show();
                //Check the condition
                if(checkAnswer(n)=="Great"){
                    score = score +1;
                    String mscore = Integer.toString(score);
                    scoreText.setText(mscore);
                }
                else{
                   // hightscore = score;
                    hightscore = checkHighScore(hightscore,score);
                    score = 0;
                    String mHighscore = Integer.toString(hightscore);
                    highScoreText.setText(mHighscore);

                }
            }
        });

        // Define what happens when the user clicks the "Down" button
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = rand.nextInt(6)+1;
                String mshowText = "Throw is "+ Integer.toString(n);
                currentImageIndex--;

                if (currentImageIndex < 1) {
                    currentImageIndex = 5;
                }
                mImageView.setImageResource(mImageNames[currentImageIndex]);
                //Create the new throw
                Throw newThrow = new Throw(mshowText);
                mThrow.add(0,newThrow);
                updateUI();
                Snackbar.make(v, checkAnswer(n), Snackbar.LENGTH_LONG).show();
                //Check the condition
                if(checkAnswer(n)=="Great"){
                    score = score +1;
                    String mscore = Integer.toString(score);
                    scoreText.setText(mscore);
                }
                else{
                    //hightscore = score;
                   hightscore = checkHighScore(hightscore,score);
                    score = 0;
                    String mHighscore = Integer.toString(hightscore);
                    highScoreText.setText(mHighscore);

                }
                }

        });

    }
        //check function
        private String checkAnswer(int answer) {
            String message;
         if (answer == (currentImageIndex+1)) {
                message = "Wrong";
         } else {
                message = "Great";
            }

            return  message;
         }
         //Update function
         private void updateUI() {
             if (mAdapter == null) {
                 mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mThrow);
                 listview.setAdapter(mAdapter);
             } else {
                 mAdapter.notifyDataSetChanged();
             }
         }

         //Update highest score
         private int checkHighScore(int a, int b){
            int temp;
             if(a>b){
                 temp=a;
             }
             else if(b>a){
                 temp =b;
             }
             else
             {temp=a;}
             return temp;
         }




}
