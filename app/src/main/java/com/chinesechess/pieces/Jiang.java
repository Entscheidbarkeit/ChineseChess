package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.chinesechess.ChessBoard.ChessBoard;

public class Jiang extends PieceView {
    public Jiang(Context con, String name, PointF centralPos, ChessBoard board, int x, int y){
        super(con, name,centralPos,board);
        super.x = x;
        super.y = y;
    }

    @Override
    public int movementCheck(Point future) {
        PieceView[][] sit = board.getSituation();
        if(future.x<3||future.x>5){
            return 2; //老头出宫
        }
        else{
            if(y <3){// 上方老头
                if(future.y>2){
                    return 2;
                }
                else return validation(future,sit);
            }
            else { //下方老头
                if(future.y<7){
                    return 2;
                }
                else return validation(future,sit);
            }
        }
    }

    private int validation(Point future, PieceView[][] sit) {
        if(future.x == x&& future.y == y-1){
            if(sit[future.x][future.y]!=null){
                return 1;
            }
            else return 0;
        }
        else if(future.x == x-1&& future.y == y){
            if(sit[future.x][future.y]!=null){
                return 1;
            }
            else return 0;
        }
        else if(future.x == x+1&& future.y == y){
            if(sit[future.x][future.y]!=null){
                return 1;
            }
            else return 0;
        }
        else if(future.x == x && future.y == y+1){
            if(sit[future.x][future.y]!=null){
                return 1;
            }
            else return 0;
        }
        else return 2;
    }

}
