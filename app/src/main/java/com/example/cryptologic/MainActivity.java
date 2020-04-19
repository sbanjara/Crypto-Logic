package com.example.cryptologic;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private String word;
    private String correctGuess;
    private String resultString;

    private int correctguessCount;
    private int incorrectGuess;

    private TextView correctresultView;
    private TextView incorrectresultView;
    private TextView congratulationDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ArrayList<String> words = new ArrayList<>(Arrays.asList("APPLE", "BANANA", "CHERRY"));
        int index = (int) (Math.random() * words.size());

        correctGuess = "";
        incorrectGuess = 0;
        correctguessCount = 0;
        word = words.get(index);

        ArrayList<String> wordlist = new ArrayList(Arrays.asList(word.split("")));
        Collections.shuffle(wordlist);

        String shuffledword = "";
        for(String s: wordlist) {
            shuffledword += s;
        }

        TextView shuffledwordView = (TextView) findViewById(R.id.scrambledWord);
        shuffledwordView.setText(shuffledword);

        correctresultView = (TextView) findViewById(R.id.correctWord);
        incorrectresultView = (TextView) findViewById(R.id.incorrectGuess);
        congratulationDisplay = (TextView) findViewById(R.id.congratulationsDisplay);

        correctresultView.setText("Correct Letter(s)\n");
        incorrectresultView.setText("Incorrect Guess(es)\n");

    }

    public void onClick(View v) {

        EditText e = (EditText) findViewById(R.id.userinput);
        String input = e.getText().toString();
        input = input.toUpperCase();

        if ( (correctguessCount + 1) <= word.length()) {

            String letter = word.substring(correctguessCount, correctguessCount+1);

            if (input.equals(letter)) {
                correctguessCount++;
                correctGuess += input;
                correctresultView.setText("Correct Letter(s)\n" + correctGuess);
            }
            else {
                incorrectGuess += 1;
                incorrectresultView.setText("Incorrect Guess(es)\n" + incorrectGuess);
            }

        }

        else {
            congratulationDisplay.setText("Congratulations, you guessed the word right!!!");
        }

        if(correctGuess.equals(word)) {
            congratulationDisplay.setText("Congratulations, you guessed the word right!!!");
        }
        e.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
