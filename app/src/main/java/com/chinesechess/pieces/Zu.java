package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.chinesechess.ChessBoard.ChessBoard;

public class Zu extends PieceView{
    boolean overRiver = false;
    public Zu(Context con, String name, PointF centralPos, ChessBoard board, int x, int y){
        super(con, name,centralPos,board);
        super.x = x;
        super.y = y;
    }

    @Override
    public int movementCheck(Point future) {
        PieceView[][] sit = board.getSituation();
        if(overRiver){
            if(side) { // Black moving upwards
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
                else return 2;
            }
            else{ //White moving downwards
                if(future.x == x&& future.y == y+1){
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
                else return 2;
            }
        }
        else { // not crossed river
            if(side) { // Black moving upwards
                if (future.x == x && future.y == y - 1) {
                    if(future.y==4)// 过河后设为true
                        overRiver = true;
                    if (sit[future.x][future.y] != null) {
                        return 1;
                    } else return 0;
                } else return 2;
            }
            else{ // White moving downwards
                if (future.x == x && future.y == y + 1) {
                    if(future.y==5)// 过河后设为true
                        overRiver = true;
                    if (sit[future.x][future.y] != null) {
                        return 1;
                    } else return 0;
                } else return 2;
            }
        }
    }
}
