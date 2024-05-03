package com.chinesechess.pieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chinesechess.ChessBoard.ChessBoard;

abstract public class PieceView extends View {
    static private PieceView selecting;

    static private boolean lastmove = false; //记录上次哪方走的，实现轮番走

    private Paint paint;
    protected PointF centralPos;
    private float halfWidth;
    protected int x;
    protected int y;
    private String name;
    // White = 0    Black = 1
    protected boolean side; // White upwards false, Black downwards true;

    private RectF position;
    private Bitmap pic;
    // Ju 1   Ma 2   Pao 3  Shi 4  Xiang 5 Jiang 6 Zu 7
    protected int number;

    protected ChessBoard board;

    private boolean selected = false;

    private boolean eaten = false;

    public PieceView(Context con,String name,PointF centralPos,ChessBoard board){
        super(con);
        this.paint = new Paint();
        this.name = name;
        this.centralPos = centralPos;
        pic = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(name,"drawable",getContext().getPackageName()));
        position = new RectF();
        if(name.contains("white")){
            this.side = false;
        }
        else this.side = true;
        String subname = name.substring(6);
        switch(subname){
            case "Ju": this.number = 1;break;
            case "Ma": this.number = 2;break;
            case "Pao": this.number = 3;break;
            case "Shi": this.number = 4;break;
            case "Xiang": this.number = 5;break;
            case "Jiang": this.number = 6;break;
            case "Zu": this.number = 7;break;
            default: this.number = -1;break;
        }
        this.board = board;

    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        this.halfWidth = 2*board.widthOfOneRect/5;
        setMeasuredDimension((int)(2*halfWidth), (int) (2*halfWidth));
    }

    public void onDraw(Canvas canvas){
        if(!eaten) {
            PointF[][] knoten = board.getKnoten();
            centralPos = knoten[x][y];
            setX(centralPos.x-halfWidth);
            setY(centralPos.y-halfWidth);
            Log.println(Log.DEBUG,"Position of piece By Ondraw",getX()+","+getY());
            position.set(0, 0, 2*halfWidth, 2*halfWidth);
            canvas.drawBitmap(pic, null, position, paint); // 这里不再使用onDraw来改变位置，而在move中直接设定坐标

        }
    }

    // 这里不再增加处理移动判断的方法，因为需要判定路上存不存在棋子，不能让每一个棋子都保存一份棋盘副本，所以在外层逻辑进行判断。
    // 0 valid, 1 attack, 2 invalid_type_1, 3 invalid_type_2
    abstract public int movementCheck(Point future);

    public boolean onTouchEvent(MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_CANCEL){
            Log.println(Log.DEBUG,"Canceled","now!!!");
        }
        if(e.getAction()==MotionEvent.ACTION_DOWN) {
                if (selecting != null) {
                    if (selecting.side != this.side) { // 对方吃我方棋子
                        selecting.move(new Point(x, y));
                        selecting.setSelected(false);
                        selecting = null;
                    } else {
                        if(lastmove!=this.side) { // 上次不是我方走才可选中
                            if (selecting == this) {
                                selecting.selected = false;
                                selecting.unselect();
                                selecting = null;
                            } else {
                                selecting.selected = false;
                                selecting.unselect();

                                selecting = this;
                                this.selected = true;
                                select();
                            }
                        }
                    }
                }
                else { // 没有选中的棋子
                    if(lastmove!=this.side) { // 上次不是我方走才可选中
                        selecting = this;
                        this.selected = true;
                        select();
                    }
                }

        }
        return true;
    }
    public void move(Point future) // 吃子逻辑也在这里实现，记得判断是否是我方棋子
    {
        int shouldMove = movementCheck(future);
        if(shouldMove == 0||shouldMove ==1){
            Log.println(Log.DEBUG,"ShouldMove","is "+shouldMove);
            PointF[][] pos = board.getKnoten();
            { // Moving!!!! Hier !!!!!
                setX(pos[future.x][future.y].x-halfWidth);
                setY(pos[future.x][future.y].y-halfWidth);
                Log.println(Log.DEBUG,"Position of piece after move",getX()+","+getY());
            }
            PieceView[][] sit = board.getSituation();
            if(shouldMove==0) {
                sit[x][y] = null;
                x = future.x;
                y = future.y;
                sit[future.x][future.y] = this;
                board.setSituation(sit);
            }
            if(shouldMove ==1){
                if(sit[future.x][future.y].side != this.side) {
                    sit[future.x][future.y].eaten = true;
                    sit[future.x][future.y].setVisibility(View.GONE);

                    sit[x][y] = null;
                    x = future.x;
                    y = future.y;
                    sit[future.x][future.y] = this;
                    board.setSituation(sit);
                }
            }
            lastmove = !lastmove; //走过之后变换
        }
        else{
            this.unselect();
        }
    }
    public void select(){
        setY(getY()-20);
    }
    public void unselect(){
        setY(getY()+20);
    }

    public static PieceView getSelecting() {
        return selecting;
    }

    public static void setSelecting(PieceView selecting) {
        PieceView.selecting = selecting;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getEaten(){
        return eaten;
    }

}
