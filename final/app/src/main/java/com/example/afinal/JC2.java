package com.example.afinal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 class JC2 {
    private int[] [] gameBoard;

    // private String[] playernames = {"Player 1", "Player 2"}

     private int[] wintype = {-1, -1, -1};

    private Button playagainbtn;
    private Button homebtn;
    private TextView playerturn;

    private int player = 1;

    JC2(){
        gameBoard = new int[3][3];
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row-1][col-1] = player;

            if (player == 1){
                playerturn.setText("player 2");
               // playerturn.setText((playernames[1] +"'s Turn"));
            }
            else {
                playerturn.setText("player 1 ");
                //playerturn.setText((playernames[0] +" "));
            }

            return true;
        }
        else {
            return false;
        }
    }

    public boolean winnerCheck(){

        boolean iswinner = false;

        for (int r=0; r<3; r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] != 0){
                wintype = new int[]{r, 0, 1};
                iswinner = true;
            }
        }
        for (int c=0; c<3; c++){
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[c][0] == gameBoard[2][c] && gameBoard[0][c] != 0) {
                wintype = new int[]{0, c, 2};

                iswinner = true;

            }
        }
       // for (int c=0; c<3; c++){
        //    if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[c][0] == gameBoard[2][c] && gameBoard[0][c] != 0) {

          //      iswinner = true;

         //   }
       // }
            if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0) {
                wintype = new int[]{0, 2, 3};

                iswinner = true;

            }
            if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] && gameBoard[2][0] != 0) {
                wintype = new int[]{2, 2, 4};

                iswinner = true;

            }
            int boardfailled = 0;

            for (int r=0; r<3; r++){
                for (int c=0; c<3; c++){
                    if (gameBoard[r][c] !=0 ){
                        boardfailled += 1;
                    }
                }
            }

            if (iswinner){
                playagainbtn.setVisibility(View.VISIBLE);
                homebtn.setVisibility(View.VISIBLE);
                playerturn.setText(" you won!!!");
                //playerturn.setText(playernames[player-1] + "won!!!");

                return true;
            }
            else if (boardfailled == 9){
                playagainbtn.setVisibility(View.VISIBLE);
                homebtn.setVisibility(View.VISIBLE);
                playerturn.setText( "tie!");

                return true;
            }
            else {
                return false;
            }

    }


    public void resetGame(){
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }

        player = 1;
         playagainbtn.setVisibility(View.GONE);
         homebtn.setVisibility(View.GONE);

       /////////////////////////////////////////////  playerturn.setText("player 1");
        // playerturn.setText(playernames[0] + "'s Turn");

    }

    public void setPlayagainbtn(Button playagainbtn) {
        this.playagainbtn = playagainbtn;
    }

    public void setHomebtn(Button homebtn) {
        this.homebtn = homebtn;
    }

    public void setPlayerturn(TextView playerturn) {
        this.playerturn = playerturn;
    }

   // public void setPlayernames(String[] playernames) {
    //    this.playernames = playernames;
   // }

   // public void setGameBoard(int[][] gameBoard) {
    //    this.gameBoard = gameBoard;
    //}

    public int[][] getGameBoard() { return gameBoard; }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

     public int[] getWintype() {
         return wintype;
     }
 }
