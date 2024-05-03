package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

import com.chinesechess.ChessBoard.ChessBoard;

public class Ju extends PieceView{
    public Ju(Context con, String name, PointF centralPos, ChessBoard board, int x, int y){
        super(con, name,centralPos,board);
        super.x = x;
        super.y = y;
    }

    @Override
    public int movementCheck(Point future) {
        PieceView[][] sit = super.board.getSituation();
        if(future.x != super.x&&future.y!=super.y)  //非直线行动
            return 2;
        else if(future.x == super.x){ // 竖向移动
            if(future.y>=y) { //这里处理同一位置的逻辑在外部解决，先对棋子位图置空，后将选中棋子移动到位图上
                for (int i = super.y + 1; i < future.y; i++) {
                    if (sit[super.x][i] != null) {  //有棋子在路上
                        return 2;
                    }
                }
                if (sit[x][(int) future.y] != null) return 1; //进攻
                else return 0;
            }
            else{
                for (int i = super.y - 1; i > future.y; i--) {
                    if (sit[super.x][i] != null) {  //有棋子在路上
                        return 2;
                    }
                }
                if (sit[x][(int) future.y] != null) return 1; //进攻
                else return 0;
            }
        }
        else{ //横向移动
            if(future.x>=x) {
                for (int i = super.x + 1; i < future.x; i++) {
                    if (sit[i][super.y] != null) {  //有棋子在路上
                        return 2;
                    }
                }
                if (sit[(int) future.x][y] != null) return 1; //进攻
                else return 0;
            }
            else{
                for (int i = super.x - 1; i > future.x; i--) {
                    if (sit[i][super.y] != null) {  //有棋子在路上
                        return 2;
                    }
                }
                if (sit[(int) future.x][y] != null) return 1; //进攻
                else return 0;
            }
        }
    }
}
