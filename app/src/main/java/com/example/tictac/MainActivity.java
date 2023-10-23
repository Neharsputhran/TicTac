package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean playerOneActive=true;
    private TextView playeronescore,playertwoscore,playerstatus;
    private Button[] buttons=new Button[9];
    private  Button reset,playagain;
    private int player1scorecount,player2scorecount;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPos={ {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                      {0,3,6}, {1,4,7}, {2,5,8},
                     {0,4,8}, {2,4,6}};
    int rounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playeronescore=(TextView)findViewById(R.id.score_player1);
        playertwoscore=(TextView)findViewById(R.id.score_player2);
        reset=(Button) findViewById(R.id.reset);
        playagain=(Button) findViewById(R.id.playagain);
        playerstatus=(TextView) findViewById(R.id.status);
        buttons[0]=findViewById(R.id.btn0);
        buttons[1]=findViewById(R.id.btn1);
        buttons[2]=findViewById(R.id.btn2);
        buttons[3]=findViewById(R.id.btn3);
        buttons[4]=findViewById(R.id.btn4);
        buttons[5]=findViewById(R.id.btn5);
        buttons[6]=findViewById(R.id.btn6);
        buttons[7]=findViewById(R.id.btn7);
        buttons[8]=findViewById(R.id.btn8);
        for(int i=0;i<buttons.length;i++)
        {
            buttons[i].setOnClickListener(this);
        }
        player1scorecount=0;
        player2scorecount=0;
        rounds=0;


    }

    @Override
    public void onClick(View v)
    {
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        else if(checkWinner())
        {
            return;
        }
        String buttonId=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer= Integer.parseInt(buttonId.substring(buttonId.length()-1,buttonId.length()));

        if (playerOneActive) {
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFFFFF"));
            gameState[gameStatePointer]=0;

        }
        else
        {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#000000"));
            gameState[gameStatePointer]=1;
        }
        rounds++;

        if(checkWinner())
        {
            if (playerOneActive) {
                player1scorecount++;
                updatePlayerScore();
                playerstatus.setText("Player-1 has won");
            } else {
                player2scorecount++;
                updatePlayerScore();
                playerstatus.setText("Player-2 has won");
            }
        }
        else if(rounds==9)
            {
                playerstatus.setText("No winner");
            }
        else
        {
            playerOneActive=!playerOneActive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                player1scorecount=0;
                player2scorecount=0;
                updatePlayerScore();
            }
        });
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });


    }

    private void playAgain()
    {
        rounds=0;
        playerOneActive=true;
        for(int i=0;i<buttons.length;i++)
        {
            gameState[i]=2;
            buttons[i].setText("");
        }
        playerstatus.setText("Status");
    }

    private void updatePlayerScore()
    {
        playeronescore.setText(Integer.toString(player1scorecount));
        playertwoscore.setText(Integer.toString(player2scorecount));
    }

    private boolean checkWinner()
    {
        boolean winResults=false;
        for(int[] winPos:winPos)
        {
            if(gameState[winPos[0]]==gameState[winPos[1]]&&gameState[winPos[1]]==gameState[winPos[2]]&&gameState[winPos[0]]!=2)
            {
                winResults=true;
            }
        }
        return winResults;
    }
}