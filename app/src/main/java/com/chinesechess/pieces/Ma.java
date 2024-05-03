package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.chinesechess.ChessBoard.ChessBoard;

public class Ma extends PieceView{
    public Ma(Context con, String name, PointF centralPos, ChessBoard board, int x, int y){
        super(con, name,centralPos,board);
        super.x = x;
        super.y = y;
    }

    @Override
    public int movementCheck(Point future) {
        PieceView[][] sit = board.getSituation();
        if(future.x == x+2&& future.y == y+1){
            if(sit[x+1][y]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }else if(future.x == x-2&&future.y == y+1){
            if(sit[x-1][y]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }
        else if(future.x ==x+2&&future.y == y-1){
            if(sit[x+1][y]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }
        else if(future.x == x-2 && future.y == y-1){
            if(sit[x-1][y]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }
        else if(future.x == x+1 && future.y == y+2){
            if(sit[x][y+1]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }
        else if(future.x == x-1 && future.y == y+2){
            if(sit[x][y+1]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }
        else if(future.x == x+1 && future.y == y-2){
            if(sit[x][y-1]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }
        else if(future.x == x-1 && future.y == y-2){
            if(sit[x][y-1]!= null)
                return 3; // 蹩马腿
            else {
                if(sit[future.x][future.y]!= null)
                    return 1; // attack
                else return 0;
            }
        }
        else return 2;
    }
}
