package com.example.tictactoe;
class GameLogic{
    public static char[][] ticTACtoe;
    private static int winCondition = 0;
    private static int scoreX = 0;
    private static int scoreO =0;
    public GameLogic(){
        ticTACtoe = new char[][]{{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    }

    public int getWinCondition(){   // The WinCondition variable is used to determine which of the columns or rows is the winner
        return winCondition;
    }

    public void setScoreX(int x){   // Scorecard for player X
        scoreX = x;
    }

    public int getScoreX(){
        return scoreX;
    }
    public void setScoreO(int x){   // Scorecard for player O
        scoreO = x;
    }

    public int getScoreO(){
        return scoreO;
    }

    static boolean checkVertical(){ // method to check if a player has won using columns
        boolean win = false;
        for(int i = 0; i<3;i++){   // if any column has three of the same value but not equal to ' '
            if(ticTACtoe[0][i]==ticTACtoe[1][i] && ticTACtoe[1][i] == ticTACtoe[2][i] && ticTACtoe[0][i]!=' ') {
                win = true;
                winCondition = i;
            }
        }
        return win;
    }
    static boolean checkHorizontal(){ // method to check if a player has won using rows
        boolean win = false;
        for(int i = 0; i<3;i++){
            if(ticTACtoe[i][0]==ticTACtoe[i][1] && ticTACtoe[i][1] == ticTACtoe[i][2]&& ticTACtoe[i][0]!=' ') {
                win = true;
                winCondition = i;
            }
        }
        return win;
    }

    static boolean checkDiagonalLtoR(){ // check to see if a player won from left to right diagonal
        boolean win = false;
        if((ticTACtoe[0][0]==ticTACtoe[1][1] && ticTACtoe[1][1] == ticTACtoe[2][2]) && (ticTACtoe[1][1]!=' '))
            win = true;
        return win;
    }

    static boolean checkDiagonalRtoL(){
        boolean win = false;
        if((ticTACtoe[2][0]==ticTACtoe[1][1] && ticTACtoe[1][1]== ticTACtoe[0][2]) && (ticTACtoe[1][1]!=' '))
            win = true;
        return win;
    }

    static boolean checkWin(){      // check win calls all other win methods
        return(checkDiagonalRtoL()||checkHorizontal()||checkVertical())||checkDiagonalLtoR();
    }

    public static void reset(){     // to reset the game board we are returning our board to empty
        ticTACtoe = new char[][]{{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    }

}