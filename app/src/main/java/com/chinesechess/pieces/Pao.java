package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.chinesechess.ChessBoard.ChessBoard;

public class Pao extends PieceView{
    public Pao(Context con, String name, PointF centralPos, ChessBoard board, int x, int y){
        super(con, name,centralPos,board);
        super.x = x;
        super.y = y;
    }

    @Override
    public int movementCheck(Point future) {
        int count = 0;
        PieceView[][] sit = super.board.getSituation();
        if(future.x != super.x&&future.y!=super.y)  //非直线行动
            return 2;
        if(sit[future.x][future.y] == null){//逻辑同车 非进攻意图
            if(future.x == super.x){ // 竖向移动
                if(future.y>=y) {
                    for (int i = super.y + 1; i <= future.y; i++) {
                        if (sit[super.x][i] != null) {  //有棋子在路上
                            return 2;
                        }
                    }
                    return 0;
                }
                else{
                    for (int i = super.y - 1; i >= future.y; i--) {
                        if (sit[super.x][i] != null) {  //有棋子在路上
                            return 2;
                        }
                    }
                    return 0;
                }
            }
            else{ //横向移动
                if(future.x>=x) {
                    for (int i = super.x + 1; i <= future.x; i++) {
                        if (sit[i][super.y] != null) {  //有棋子在路上
                            return 2;
                        }
                    }
                    return 0;
                }
                else{
                    for (int i = super.x - 1; i >= future.x; i--) {
                        if (sit[i][super.y] != null) {  //有棋子在路上
                            return 2;
                        }
                    }
                    return 0;
                }
            }
        }
        else{ //进攻意图
            if(future.x == super.x){ // 竖向移动
                if(future.y>=y) {
                    for (int i = super.y + 1; i < future.y; i++) {
                        if (sit[super.x][i] != null) {  //有棋子在路上
                            count++;
                        }
                    }
                    if(count == 1)
                    return 1;
                    else return 3;
                }
                else{
                    for (int i = super.y - 1; i > future.y; i--) {
                        if (sit[super.x][i] != null) {  //有棋子在路上
                            count++;
                        }
                    }
                    if(count == 1)
                        return 1;
                    else return 3;
                }
            }
            else{ //横向移动
                if(future.x>=x) {
                    for (int i = super.x + 1; i < future.x; i++) {
                        if (sit[i][super.y] != null) {  //有棋子在路上
                            count++;
                        }
                    }
                    if(count == 1)
                        return 1;
                    else return 3;
                }
                else{
                    for (int i = super.x - 1; i > future.x; i--) {
                        if (sit[i][super.y] != null) {  //有棋子在路上
                            count++;
                        }
                    }
                    if(count == 1)
                        return 1;
                    else return 3;
                }
            }
        }
    }
}
