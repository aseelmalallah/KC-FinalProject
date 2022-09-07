package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private JC tictactoeboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button playagainbtn = findViewById(R.id.button3);
        Button homebtn = findViewById(R.id.button4);
        TextView playerturn = findViewById(R.id.textView3);

        playagainbtn.setVisibility(View.GONE);
        homebtn.setVisibility(View.GONE);

        String[] playernames = getIntent().getStringArrayExtra("player_names");

        tictactoeboard = findViewById(R.id.JC);

       tictactoeboard.setUpGame(playagainbtn, homebtn, playerturn, playernames);

        if (playernames != null){
            playerturn.setText((playernames[0] = "player 1"));
            //playerturn.setText((playernames[0] = "'s Turn"));
        }
    }

    public void playagainbuttonclick(View view){
        tictactoeboard.resetGame();
        tictactoeboard.invalidate();
    }

  public void homebuttonclick(View view){

        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);
  }

}