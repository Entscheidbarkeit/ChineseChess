package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.chinesechess.ChessBoard.ChessBoard;

public class Xiang extends PieceView{
    public Xiang(Context con, String name, PointF centralPos, ChessBoard board, int x, int y){
        super(con, name,centralPos,board);
        super.x = x;
        super.y = y;
    }

    @Override
    public int movementCheck(Point future) {
        PieceView[][] sit = board.getSituation();
        if(y<= 5){
            if(future.y >5)// 不能过河
                return 3;
            return validation(future,sit); //在此前提下对落点进行判断
        }
        else{
            if(future.y<=5)
                return 3;
            return validation(future,sit);
        }
    }
    private int validation(Point future,PieceView[][]sit){
        if(future.x == x+2 && future.y == y+2){
            if(sit[x+1][y+1] != null){
                return 3;
            }
            else{
                if(sit[future.x][future.y] != null)
                    return 1;
                else return 0;
            }
        }
        else if(future.x == x-2 && future.y == y+2){
            if(sit[x-1][y+1] != null){
                return 3;
            }
            else{
                if(sit[future.x][future.y] != null)
                    return 1;
                else return 0;
            }
        }
        else if(future.x == x+2 && future.y == y-2){
            if(sit[x+1][y-1] != null){
                return 3;
            }
            else{
                if(sit[future.x][future.y] != null)
                    return 1;
                else return 0;
            }
        }
        else if(future.x == x-2 && future.y == y-2){
            if(sit[x-1][y-1] != null){
                return 3;
            }
            else{
                if(sit[future.x][future.y] != null)
                    return 1;
                else return 0;
            }
        }
        else return 2;
    }
}
