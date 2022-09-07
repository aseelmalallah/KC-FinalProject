package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        player1 = findViewById(R.id.P1);
        player2 = findViewById(R.id.P2);
    }

    public void submitbuttonclick(View view){
        String player1name = player1.getText().toString();
        String player2name = player2.getText().toString();

        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("player_names", new String[] {player1name,player2name});
        startActivity(intent);
    }

}