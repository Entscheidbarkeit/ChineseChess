package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.chinesechess.ChessBoard.ChessBoard;

public class Shi extends PieceView{
    public Shi(Context con, String name, PointF centralPos, ChessBoard board, int x, int y){
        super(con, name,centralPos,board);
        super.x = x;
        super.y = y;
    }

    @Override
    public int movementCheck(Point future) {
        PieceView[][] sit = board.getSituation();
        if(future.x<3 || future.x>5) //横向越界
            return 2;
        else {
            if (y >= 0 && y <= 2) {// 纵向越界
                if (future.y > 2) {
                    return 2;
                } else { //未越界进行下一步判断
                    return validation(future, sit);
                }
            }
            else if(y<=9&&y>=7){
                if(future.y <7)
                    return 2;
                else return validation(future,sit);
            }
            else return 2;
        }
    }

    private int validation(Point future, PieceView[][] sit){
        if(future.x == x+1&&future.y == y+1){
            if(sit[future.x][future.y]!= null){
                return 1;
            }
            else return 0;
        }
        else if(future.x == x+1&&future.y == y-1){
            if(sit[future.x][future.y]!= null){
                return 1;
            }
            else return 0;
        }
        else if(future.x == x-1&&future.y == y+1){
            if(sit[future.x][future.y]!= null){
                return 1;
            }
            else return 0;
        }
        else if(future.x == x-1&&future.y == y-1){
            if(sit[future.x][future.y]!= null){
                return 1;
            }
            else return 0;
        }
        else return 2;
    }
}
