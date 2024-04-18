package com.rockpaperscissor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnplay, btnhistory;
    ImageView DispAI, DispPaper, DispScissor, DispRock;
    TextView lblWin, lblLose;

    DBHandler DB;

    Intent main;
    private static String selectedHandSign;

    Drawable picRock, picScissor, picPaper;

    String pChoice, bChoice, gWin;

    int playerPick, botPick, gameWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Variables info = Variables.getInstance();

        Random rand = new Random();
        DB = new DBHandler(this);

        btnplay = findViewById(R.id.btnPlay);
        btnhistory = findViewById(R.id.btnHistory);

        DispAI = (ImageView) findViewById(R.id.dispAI);
        DispPaper = (ImageButton) findViewById(R.id.DispPaper);
        DispScissor = (ImageButton) findViewById(R.id.DispScissor);
        DispRock = (ImageButton) findViewById(R.id.DispRock);

        lblWin = findViewById(R.id.lblWin);
        lblLose = findViewById(R.id.lblLose);

        picRock = ContextCompat.getDrawable(this, R.drawable.rock);
        picScissor = ContextCompat.getDrawable(this, R.drawable.scissor);
        picPaper = ContextCompat.getDrawable(this, R.drawable.paper);

        lblWin.setText(String.valueOf(info.winCount));
        lblLose.setText(String.valueOf(info.loseCount));



        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedHandSign == null){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Select a Hand Sign First", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 500);
                    return;
                }

                botPick = rand.nextInt(3) + 1;

                switch (botPick){
                    case 1:
                        DispAI.setImageDrawable(picRock);
                        bChoice = "Rock";
                        break;
                    case 2:
                        DispAI.setImageDrawable(picScissor);
                        bChoice = "Paper";
                        break;
                    case 3:
                        DispAI.setImageDrawable(picPaper);
                        bChoice = "Scissor";
                        break;
                }

                // 1 = rock | 2 = scissor | 3 = picPaper

                if (playerPick == botPick)
                {
                    gameWinner = 0;
                    Toast toast = Toast.makeText(getApplicationContext(), "DRAW!", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                }

                else if (playerPick == 1 && botPick == 2)
                {
                    info.winCount += 1;
                    gameWinner = 1;
                    lblWin.setText(String.valueOf(info.winCount));
                    Toast toast = Toast.makeText(getApplicationContext(), "Player: Rock VS AI: Scissor = Winner: PLAYER", Toast.LENGTH_LONG);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                }

                else if  (playerPick == 2 && botPick == 3)
                {
                    info.winCount += 1;
                    gameWinner = 1;
                    lblWin.setText(String.valueOf(info.winCount));
                    Toast toast = Toast.makeText(getApplicationContext(), "Player: Scissor VS AI: Paper = Winner: PLAYER", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                }

                else if (playerPick == 3 && botPick == 1)
                {
                    info.winCount += 1;
                    gameWinner = 1;
                    lblWin.setText(String.valueOf(info.winCount));
                    Toast toast = Toast.makeText(getApplicationContext(), "Player: Paper VS AI: Rock = Winner: PLAYER", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                }

                // 1 = rock | 2 = scissor | 3 = picPaper

                else if (botPick == 1 && playerPick == 2)
                {
                    info.loseCount += 1;
                    gameWinner = 2;
                    lblLose.setText(String.valueOf(info.loseCount));
                    Toast toast = Toast.makeText(getApplicationContext(), "Player: Scissor VS AI: Rock = Winner: AI", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                }

                else if  (botPick == 2 && playerPick == 3)
                {
                    info.loseCount += 1;
                    gameWinner = 2;
                    lblLose.setText(String.valueOf(info.loseCount));
                    Toast toast = Toast.makeText(getApplicationContext(), "Player: Paper VS AI: Scissor = Winner: AI", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                }

                else if (botPick == 3 && playerPick == 1)
                {
                    info.loseCount += 1;
                    gameWinner = 2;
                    lblLose.setText(String.valueOf(info.loseCount));
                    Toast toast = Toast.makeText(getApplicationContext(), "Player: Rock VS AI: Paper = Winner: AI", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                }

                switch (playerPick){
                    case 1:
                        pChoice = "Rock";
                        break;
                    case 2:
                        pChoice = "Paper";
                        break;
                    case 3:
                        pChoice = "Scissor";
                        break;
                }

                switch (gameWinner){
                    case 0:
                        gWin = "Draw";
                        break;
                    case 1:
                        gWin = "Player";
                        break;
                    case 2:
                        gWin = "Bot";
                        break;
                }

                gameHistory();

                selectedHandSign = null;
            }
        });

        DispRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerPick = 1;
                selectedHandSign ="Rock";
                Toast toast = Toast.makeText(getApplicationContext(), "Player pick: Rock", Toast.LENGTH_SHORT);
                toast.show();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        toast.cancel();
                    }
                }, 1000);
            }
        });

        DispScissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerPick = 2;
                selectedHandSign ="Scissor";
                Toast toast= Toast.makeText(getApplicationContext(), "Player pick: Scissor", Toast.LENGTH_SHORT);
                toast.show();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        toast.cancel();
                    }
                }, 1000);
            }
        });

        DispPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerPick = 3;
                selectedHandSign ="Paper";
                Toast toast= Toast.makeText(getApplicationContext(), "Player pick: Paper", Toast.LENGTH_SHORT);
                toast.show();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        toast.cancel();
                    }
                }, 1000);
            }
        });

        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = new Intent(MainActivity.this, History.class);
                startActivity(main);
            }
        });
    }

    public void gameHistory(){
        try {
            final String pChoice1 = pChoice;
            final String bChoice1 = bChoice;
            final String gWin1 = gWin;

            boolean checkingInsert = DB.insertGameHistory(pChoice1, bChoice1, gWin1);
            if(checkingInsert){
                Toast toast = Toast.makeText(this, "Added!", Toast.LENGTH_SHORT);
                toast.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
            } else {
                Toast toast = Toast.makeText(this, "Error!", Toast.LENGTH_SHORT);
                toast.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
            }
        } catch (Exception e){
            Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("Error DB", "Error occurred: " + e.getMessage());
        }
    }
}