package kr.ac.kpu2014180044.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.kpu2014180044.termproject.framework.GameObject;
import kr.ac.kpu2014180044.termproject.game.Brick;
import kr.ac.kpu2014180044.termproject.game.MainGame;
import kr.ac.kpu2014180044.termproject.game.Player;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageButton dirChangeButton;
    private ImageButton upStairButton;
    private ProgressBar progressBar;
    private TextView scoreTextView;
    private Button retryButton;
    MainGame mainGame;
    Player player;
    private int startIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGame = MainGame.get();
        player = mainGame.getPlayer();

        dirChangeButton = findViewById(R.id.DirChange);
        upStairButton = findViewById(R.id.UpStairs);
        progressBar = findViewById(R.id.ProgressBar);

        scoreTextView = findViewById(R.id.ScoreText);
        scoreTextView.setBackgroundColor(Color.argb(0,255,255,255));
        scoreTextView.setTextColor(Color.argb(0,255,255,255));

        retryButton = findViewById(R.id.RetryButton);
        retryButton.setEnabled(false);
        retryButton.setBackgroundColor(Color.argb(0,255,255,255));
        retryButton.setTextColor(Color.argb(0,255,255,255));

        dirChangeButton.setImageResource(R.mipmap.dir_change);
        dirChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = mainGame.getPlayer();
                player.setupDir();

                player.setProgressBar(progressBar);
                player.setScoreTextView(scoreTextView);
                player.setRetryButton(retryButton);
            }
        });

        upStairButton.setImageResource(R.mipmap.up_stairs);
        upStairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = mainGame.getPlayer();
                player.upStairs();

                player.setProgressBar(progressBar);
                player.setScoreTextView(scoreTextView);
                player.setRetryButton(retryButton);
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = mainGame.getPlayer();
                player.reset();

                ArrayList<GameObject> brickList = mainGame.getLayers().get(3);
                for (GameObject brick : brickList)
                    brick.reset();

                scoreTextView.setBackgroundColor(Color.argb(0,255,255,255));
                scoreTextView.setTextColor(Color.argb(0,255,255,255));

                retryButton.setEnabled(false);
                retryButton.setBackgroundColor(Color.argb(0,255,255,255));
                retryButton.setTextColor(Color.argb(0,0,0,0));
            }
        });
    }


}