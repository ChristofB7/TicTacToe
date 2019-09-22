package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

     GameLogic game;

    String player1 = "Player X";
    String player2 = "Player O";    //TODO Change this
    private Button[] buttonArray;   // a Button array to hold all of the buttons that make the board
    Button resetButton;
    TextView playerTurn;
    TextView scorecard;
    int turnTracker=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new GameLogic();
        buttonArray = new Button[9];
        int[] rIDs = {R.id.button,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};
        for(int i = 0; i<9; i++){
            buttonArray[i] = findViewById(rIDs[i]);     // Find all buttons using a for loop and add it to the buttonArray
            buttonArray[i].setBackgroundColor(0);       // Make all buttons transparent to show the back drop
        }

        resetButton = findViewById(R.id.reseter);       // Find the reset button
        playerTurn = findViewById(R.id.playerTurnTextView);       // Find the playerTurn TextView
        scorecard = findViewById(R.id.scorecardTextView);       // Find the scorecard TextView
        playerTurn.setText(player1+" to start");
        scorecard.setText(player1+": "+game.getScoreX()+"         "+ player2 + ": "+game.getScoreO());

        for(int i = 0; i<9;i++){
            final int x = i;
            buttonArray[x].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    if (game.ticTACtoe[x % 3][x / 3] == ' ') {          // This if statement checks to see if the button is empty using game Logic
                                                                        // in order to make the 1D array into 2D game logic I translated the index
                        turnTracker++;                                  // using modulo and integer division
                        if (turnTracker % 2 == 0) {
                            buttonArray[x].setText("x");
                            buttonArray[x].setTextColor(getResources().getColor(R.color.colorRed));
                            game.ticTACtoe[x % 3][x / 3] = 'x';         // set the 2D game logic array to x as well to later check if someone won
                            playerTurn.setText(player2+"'s Turn");
                        } else {
                            buttonArray[x].setText("O");
                            game.ticTACtoe[x % 3][x / 3] = 'O';
                            buttonArray[x].setTextColor(getResources().getColor(R.color.colorBlue));
                            playerTurn.setText(player1+"'s Turn");
                        }

                        if (game.checkWin()) {                          // This if statement encapsulates all win conditions seen below
                            if (turnTracker % 2 == 0) {                 // Display who won, increment the score counter
                                playerTurn.setTextColor(getResources().getColor(R.color.colorRed));
                                playerTurn.setText(player1+" Wins");
                                game.setScoreX(game.getScoreX()+1);
                                scorecard.setText(player1+": "+game.getScoreX()+"         "+ player2 + ": "+game.getScoreO());
                            }
                            else {
                                playerTurn.setTextColor(getResources().getColor(R.color.colorBlue));
                                playerTurn.setText(player2+" Wins");
                                game.setScoreO(game.getScoreO()+1);
                                scorecard.setText(player1+": "+game.getScoreX()+"         "+ player2 + ": "+game.getScoreO());
                            }

                            if(game.checkVertical()){ // set the winning column to a gold color
                                if(game.getWinCondition() == 0) for(int i = 0; i<3;i++) buttonArray[i].setTextColor(getResources().getColor(R.color.colorGold));
                                if(game.getWinCondition() == 1) for(int i = 3;i<6;i++) buttonArray[i].setTextColor(getResources().getColor(R.color.colorGold));
                                if(game.getWinCondition() == 2) for(int i = 6;i<9;i++) buttonArray[i].setTextColor(getResources().getColor(R.color.colorGold));
                            }
                            if(game.checkHorizontal()){
                                if(game.getWinCondition() == 0){ // set the winning row to a gold color
                                    buttonArray[0].setTextColor(getResources().getColor(R.color.colorGold));
                                    buttonArray[3].setTextColor(getResources().getColor(R.color.colorGold));
                                    buttonArray[6].setTextColor(getResources().getColor(R.color.colorGold));
                                }
                                if(game.getWinCondition() == 1){
                                    buttonArray[1].setTextColor(getResources().getColor(R.color.colorGold));
                                    buttonArray[4].setTextColor(getResources().getColor(R.color.colorGold));
                                    buttonArray[7].setTextColor(getResources().getColor(R.color.colorGold));
                                }
                                if(game.getWinCondition() == 2){
                                    buttonArray[2].setTextColor(getResources().getColor(R.color.colorGold));
                                    buttonArray[5].setTextColor(getResources().getColor(R.color.colorGold));
                                    buttonArray[8].setTextColor(getResources().getColor(R.color.colorGold));
                                }
                            }
                            if(game.checkDiagonalLtoR()){ // set the winning diagonal to gold
                                buttonArray[0].setTextColor(getResources().getColor(R.color.colorGold));
                                buttonArray[4].setTextColor(getResources().getColor(R.color.colorGold));
                                buttonArray[8].setTextColor(getResources().getColor(R.color.colorGold));
                            }
                            if(game.checkDiagonalRtoL()){
                                buttonArray[6].setTextColor(getResources().getColor(R.color.colorGold));
                                buttonArray[4].setTextColor(getResources().getColor(R.color.colorGold));
                                buttonArray[2].setTextColor(getResources().getColor(R.color.colorGold));
                            }


                            for(int i = 0; i<3; i++) {  // in order to not allow multiple winners we set the 2D array to values that are not
                                                        // spaces. The very first thing my code checks is if the 2D array spot is empty
                                for (int j = 0; j < 3; j++) {
                                    if (game.ticTACtoe[i][j] == ' ') {
                                        game.ticTACtoe[i][j] = 'c';
                                    }
                                }
                            }
                        }
                    }
                }
            }
            );
        }

        resetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){        // reset all values as well
                for(int i = 0; i<9;i++){
                    buttonArray[i].setText(" ");
                    buttonArray[i].setTextColor(0);
                    buttonArray[i].setBackgroundColor(0);
                }
                playerTurn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                playerTurn.setText( player1+" to start");
                game.reset();
                turnTracker = 1;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        char[] oneDimensionalBoard = new char[9];
        int count = 0;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                oneDimensionalBoard[count] = game.ticTACtoe[i][j];      //When the instance is save (e.x. screen rotation) store the current board
                count++;                                                //into a one dimensional array to allow us to use outState.putCharArray below
            }
        }
        String[] stringArray = new String[9];
        for(int i = 0;i<buttonArray.length;i++){
            stringArray[i] = (String)buttonArray[i].getText();          // Store all button texts in an array so that we can use outState.putString array
        }
        outState.putInt("turn",turnTracker);                            // Store the turn
        outState.putCharArray("board",oneDimensionalBoard);
        outState.putStringArray("button",stringArray);
        outState.putString("scorecard",(String)playerTurn.getText());       //store the text of the textView that states whose turn it is
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        char[] oneDimensionalBoard = new char[9];                       // create a temporary variable to get the 1D char array
        String[] stringArr = new String[9];                             // and the button texts
        oneDimensionalBoard = savedInstanceState.getCharArray("board");
        stringArr = savedInstanceState.getStringArray("button");

        int count = 0;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                game.ticTACtoe[i][j] = oneDimensionalBoard[count];     // Place the 1D char array into the current game board
                count++;
            }
        }
        for(int i = 0; i<buttonArray.length;i++){
            buttonArray[i].setText(stringArr[i]);                   // set each button to their previous values
            if (buttonArray[i].getText() == "x"){
                buttonArray[i].setTextColor(getResources().getColor(R.color.colorRed)); // if x make it red
            }
            else if(buttonArray[i].getText()== "O"){
                buttonArray[i].setTextColor(getResources().getColor(R.color.colorBlue));// if o make it blue
            }
        }
        turnTracker = savedInstanceState.getInt("turn");
        playerTurn.setText(savedInstanceState.getString("scorecard"));


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.stats:
                Intent intent1 = new Intent(this, SettingsActivity.class);  // start the settings activity when clicked on the preferences button
                this.startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
