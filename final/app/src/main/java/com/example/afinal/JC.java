package com.example.afinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class JC extends View {

    private final int boardcolor;
    private final int xcolor;
    private final int ocolor;
    private final int winninglinecolor;

    private boolean winningLine = false;

    private final Paint paint = new Paint();

    private final JC2 game;

    private int cellsize = getWidth()/3;

    public JC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new JC2();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.JC,0,0);

         try{
             boardcolor =a.getInteger(R.styleable.JC_boardcolor,0);
             xcolor =a.getInteger(R.styleable.JC_xcolor,0);
             ocolor =a.getInteger(R.styleable.JC_ocolor,0);
             winninglinecolor =a.getInteger(R.styleable.JC_winninglinecolor,0);
         }finally {
             a.recycle();
         }


    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellsize = dimension/3;

        setMeasuredDimension(dimension, dimension);

    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);

        if (winningLine){
            paint.setColor(winninglinecolor);
            drawwinningline(canvas);
        }

        //drawx(canvas,1,1);
        //drawo(canvas,2,2);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
     float x = event.getX();
     float y = event.getY();

     int action = event.getAction();

     if (action == MotionEvent.ACTION_DOWN){
        int row =(int) Math.ceil(y/cellsize);
        int col =(int) Math.ceil(x/cellsize);

        if (!winningLine) {
            if (game.updateGameBoard(row, col)) {
                invalidate();

                if (game.winnerCheck()){
                    winningLine = true;
                    invalidate();
                }

                if (game.getPlayer() % 2 == 0) {
                    game.setPlayer(game.getPlayer() - 1);
                } else {
                    game.setPlayer(game.getPlayer() + 1);
                }
            }
        }

        invalidate();

        return true;
     }

     return false;

    }


    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardcolor);
        paint.setStrokeWidth(16);

        for (int c=1; c<3; c++){
            canvas.drawLine(cellsize*c,0,cellsize*c,canvas.getWidth(),paint);
        }
        for (int r=1; r<3; r++){
            canvas.drawLine(0,cellsize*r,canvas.getWidth(),cellsize*r,paint);
        }
    }

    private void drawMarkers(Canvas canvas){
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                if (game.getGameBoard()[r][c] != 0){
                    if (game.getGameBoard()[r][c] == 1){
                        drawx(canvas, r, c);
                    }
                    else {
                        drawo(canvas, r, c);
                    }
                }
            }
        }
    }

    private void drawx(Canvas canvas, int row , int col){
        paint.setColor(xcolor);

        canvas.drawLine((float) ((col+1)*cellsize - cellsize*0.2),
                        (float) (row*cellsize + cellsize*0.2),
                        (float) (col*cellsize + cellsize*0.2),
                        (float) ((row+1)*cellsize - cellsize*0.2),
                         paint);

        canvas.drawLine((float) (col*cellsize + cellsize*0.2),
                        (float) (row*cellsize + cellsize*0.2),
                        (float) ((col+1)*cellsize - cellsize*0.2),
                        (float) ((row+1)*cellsize - cellsize*0.2),
                        paint);
    }

    private void drawo(Canvas canvas, int row , int col){
        paint.setColor(ocolor);

        canvas.drawOval((float) (col*cellsize + cellsize*0.2),
                        (float) (row*cellsize + cellsize*0.2),
                        (float) ((col*cellsize + cellsize) - cellsize*0.2),
                        (float) ((row*cellsize + cellsize) - cellsize*0.2) ,
                         paint);
    }

    private void drawhorizontalline(Canvas canvas, int row, int col){
        canvas.drawLine(col, row*cellsize + (float) cellsize/2,
                cellsize*3, row*cellsize + (float) cellsize/2,
                paint);
    }

    private void drawverticalline(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellsize + (float) cellsize/2, row,
                col*cellsize + (float) cellsize/2, cellsize*3,
                paint);
    }
    private void drawdiagonallinepos(Canvas canvas){
        canvas.drawLine(0, cellsize*3,
                cellsize*3, 0,
                paint);
    }
    private void drawdiagonallineneg(Canvas canvas){
        canvas.drawLine(0, 0,
                cellsize*3, cellsize*3,
                paint);
    }

    private void drawwinningline(Canvas canvas){
        int row = game.getWintype()[0];
        int col = game.getWintype()[1];

        switch (game.getWintype()[2]){
            case 1:
                drawhorizontalline(canvas, row, col);
                break;
            case 2:
                drawverticalline(canvas, row, col);
                break;
            case 3:
                drawdiagonallineneg(canvas);
                break;
            case 4:
                drawdiagonallinepos(canvas);
                break;
        }
    }

    public void setUpGame(Button playagain, Button home, TextView playerdisplay, String[] names){
        game.setPlayagainbtn(playagain);
        game.setHomebtn(home);
        game.setPlayerturn(playerdisplay);
       // game.setPlayernames(names);

    }

    public void resetGame(){
        game.resetGame();
        winningLine = false;
    }
}
