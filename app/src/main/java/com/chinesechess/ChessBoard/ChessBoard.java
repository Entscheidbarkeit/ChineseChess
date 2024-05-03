package com.chinesechess.ChessBoard;

import static android.graphics.Color.BLACK;

import static java.lang.Math.abs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.chinesechess.pieces.Jiang;
import com.chinesechess.pieces.PieceView;

public class ChessBoard extends View {
    private PointF[][] Knoten; // 全局坐标
    private PointF[][] LocalKnoten;// 本地坐标
    private PieceView[][] situation ;
    private float PaoZuCenter = 20.0F;
    private float PaoZuVariante = 40.0F;
    private float newWidth;
    private float newStart;
    public float widthOfOneRect;

    private Jiang whiteJiang;
    private Jiang blackJiang;
    public ChessBoard(Context con, AttributeSet attr){
        super(con,attr);
        Knoten = new PointF[9][10];
        LocalKnoten = new PointF[9][10];
        for(int i = 0; i< 9; i++){
            for(int j = 0; j < 10; j++){
                LocalKnoten[i][j] = new PointF();
                Knoten[i][j] = new PointF();
            }
        }
        situation = new PieceView[9][10];
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int width = MeasureSpec.getSize(widthMeasureSpec);


        newWidth = width*9/12;
        newStart = width*3/24;
        widthOfOneRect = newWidth/8;
        setMeasuredDimension(width, (int) (11*widthOfOneRect));
    }
    public void onDraw(Canvas canvas){
        float width = widthOfOneRect;
        PaoZuCenter = width/8;
        PaoZuVariante = width/(float)4;
        Paint paint= new Paint();
        paint.setColor(BLACK);
        paint.setStrokeWidth(5);

        //线条
        for(int i = 0; i < 9; i++){
            paint.setStrokeWidth(5);
            canvas.drawLine(i * width+newStart, newStart, i * width+newStart, width * 4+newStart, paint);
            canvas.drawLine(newStart, i * width+newStart, newWidth+newStart, i * width+newStart, paint);
            canvas.drawLine(i * width+newStart, width * 5+newStart, i * width+newStart, newWidth+width+newStart, paint);

        }
        paint.setStrokeWidth(10);
        // top
        canvas.drawLine(newStart, newStart, newWidth+newStart, newStart, paint);
        // bottom
        canvas.drawLine(newStart,9*width+newStart,newWidth+newStart,9*width+newStart,paint);
        //left
        canvas.drawLine(newStart,newStart,newStart,newStart+9*width,paint);
        //right
        canvas.drawLine(newStart+newWidth,newStart,newStart+newWidth,9*width+newStart,paint);
        paint.setStrokeWidth(5);
        // 点
        for(int i = 0; i< 9; i++){
            for(int j = 0; j < 10; j++){
                Knoten[i][j].x = i*width+newStart;
                Knoten[i][j].y = j*width+newStart;
            }
        }
        paint.setTextSize(width-width/8);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("楚河",Knoten[1][5].x,Knoten[1][5].y-width/8,paint);
        canvas.drawText("汉界",Knoten[5][5].x,Knoten[5][5].y-width/8,paint); // 这个Knoten中储存的当前是全局的坐标，而我们在这里的canvas进行绘制用的是这个View中的坐标
        //将士格子
        canvas.drawLine(Knoten[3][0].x,Knoten[3][0].y,Knoten[5][2].x,Knoten[5][2].y,paint);
        canvas.drawLine(Knoten[3][2].x,Knoten[3][2].y,Knoten[5][0].x,Knoten[5][0].y,paint);
        canvas.drawLine(Knoten[3][7].x,Knoten[3][7].y,Knoten[5][9].x,Knoten[5][9].y,paint);
        canvas.drawLine(Knoten[3][9].x,Knoten[3][9].y,Knoten[5][7].x,Knoten[5][7].y,paint);
        //炮足位置
        drawPaoZuPosition(Knoten[0][3],canvas,paint);
        drawPaoZuPosition(Knoten[1][2],canvas,paint);
        drawPaoZuPosition(Knoten[2][3],canvas,paint);
        drawPaoZuPosition(Knoten[4][3],canvas,paint);
        drawPaoZuPosition(Knoten[6][3],canvas,paint);
        drawPaoZuPosition(Knoten[7][2],canvas,paint);
        drawPaoZuPosition(Knoten[8][3],canvas,paint);
        drawPaoZuPosition(Knoten[0][6],canvas,paint);
        drawPaoZuPosition(Knoten[1][7],canvas,paint);
        drawPaoZuPosition(Knoten[2][6],canvas,paint);
        drawPaoZuPosition(Knoten[4][6],canvas,paint);
        drawPaoZuPosition(Knoten[6][6],canvas,paint);
        drawPaoZuPosition(Knoten[7][7],canvas,paint);
        drawPaoZuPosition(Knoten[8][6],canvas,paint);

        for(int i = 0; i< 9; i++){
            for(int j = 0; j < 10; j++){
                LocalKnoten[i][j].x = Knoten[i][j].x;
                LocalKnoten[i][j].y = Knoten[i][j].y;
                Knoten[i][j].x = i*width+newStart;
                Knoten[i][j].y = j*width+newStart+getTop();
            }
        } // 最后更新一下全局的坐标给外面用
    }
    private void drawPaoZuPosition(PointF p, Canvas c,Paint pa){
        if(p.x == newStart){
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y - PaoZuVariante),(float)(p.x+PaoZuCenter),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y - PaoZuCenter),(float)(p.x+PaoZuVariante),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y + PaoZuVariante),(float)(p.x+PaoZuCenter),(float)(p.y+PaoZuCenter),pa);
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y + PaoZuCenter),(float)(p.x+PaoZuVariante),(float)(p.y+PaoZuCenter),pa);
        }
        else if(p.x == newStart+newWidth){
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y - PaoZuVariante),(float)(p.x-PaoZuCenter),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y - PaoZuCenter),(float)(p.x-PaoZuVariante),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y + PaoZuVariante),(float)(p.x-PaoZuCenter),(float)(p.y+PaoZuCenter),pa);
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y + PaoZuCenter),(float)(p.x-PaoZuVariante),(float)(p.y+PaoZuCenter),pa);
        }
        else{
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y - PaoZuVariante),(float)(p.x+PaoZuCenter),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y - PaoZuCenter),(float)(p.x+PaoZuVariante),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y + PaoZuVariante),(float)(p.x+PaoZuCenter),(float)(p.y+PaoZuCenter),pa);
            c.drawLine((float)(p.x+PaoZuCenter),(float)(p.y + PaoZuCenter),(float)(p.x+PaoZuVariante),(float)(p.y+PaoZuCenter),pa);
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y - PaoZuVariante),(float)(p.x-PaoZuCenter),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y - PaoZuCenter),(float)(p.x-PaoZuVariante),(float)(p.y-PaoZuCenter),pa);
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y + PaoZuVariante),(float)(p.x-PaoZuCenter),(float)(p.y+PaoZuCenter),pa);
            c.drawLine((float)(p.x-PaoZuCenter),(float)(p.y + PaoZuCenter),(float)(p.x-PaoZuVariante),(float)(p.y+PaoZuCenter),pa);
        }
    }
    public boolean onTouchEvent(MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            if (PieceView.getSelecting() != null) {
                float x = e.getX(), y = e.getY();
                //确定四点
                PointF[] vierPunkt = new PointF[4];  //四点是触摸周围的四点， 四个虚拟点是抽象棋盘坐标
                vierPunkt[0] = LocalKnoten[0][0];
                vierPunkt[1] = LocalKnoten[8][0];
                vierPunkt[2] = LocalKnoten[0][9];
                vierPunkt[3] = LocalKnoten[8][9];
                Point[] vierVirtual = new Point[4];

                vierVirtual[0] = new Point(0,0);
                vierVirtual[1] = new Point(8,0);
                vierVirtual[2] = new Point(0,9);
                vierVirtual[3] = new Point(8,9);

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 10; j++) {
                        if ((LocalKnoten[i][j].x <= x && LocalKnoten[i][j].x >= vierPunkt[0].x) && (LocalKnoten[i][j].y <= y && LocalKnoten[i][j].y >= vierPunkt[0].y)) {
                            vierPunkt[0] = LocalKnoten[i][j];
                            vierVirtual[0].x = i;
                            vierVirtual[0].y = j;
                        }
                        if ((LocalKnoten[i][j].x >= x && LocalKnoten[i][j].x <= vierPunkt[1].x) && (LocalKnoten[i][j].y <= y && LocalKnoten[i][j].y >= vierPunkt[1].y)) {
                            vierPunkt[1] = LocalKnoten[i][j];
                            vierVirtual[1].x = i;
                            vierVirtual[1].y = j;
                        }
                        if ((LocalKnoten[i][j].x <= x && LocalKnoten[i][j].x >= vierPunkt[2].x) && (LocalKnoten[i][j].y >= y && LocalKnoten[i][j].y <= vierPunkt[2].y)) {
                            vierPunkt[2] = LocalKnoten[i][j];
                            vierVirtual[2].x = i;
                            vierVirtual[2].y = j;
                        }
                        if ((LocalKnoten[i][j].x >= x && LocalKnoten[i][j].x <= vierPunkt[3].x) && (LocalKnoten[i][j].y >= y && LocalKnoten[i][j].y <= vierPunkt[3].y)) {
                            vierPunkt[3] = LocalKnoten[i][j];
                            vierVirtual[3].x = i;
                            vierVirtual[3].y = j;
                        }
                    }
                }
                PointF closePoint = vierPunkt[0];
                Point closeVirtual = vierVirtual[0];
                for (int i = 0; i < 4; i++) {
                    if ((abs(x - vierPunkt[i].x) + abs(y - vierPunkt[i].y)) < (abs(x - closePoint.x) + abs(y - closePoint.y))) {
                        closePoint = vierPunkt[i];
                        closeVirtual = vierVirtual[i];
                    }
                }
                PieceView.getSelecting().move(closeVirtual);
                PieceView.getSelecting().setSelected(false);
                PieceView.setSelecting(null);
            }
        }
        return true;
    }
    public PointF[][] getKnoten() {
        return Knoten;
    }

    public PieceView[][] getSituation() {
        return situation;
    }

    public void setSituation(PieceView[][] situation) {
        this.situation = situation;
    }

    public void loadJiang(Jiang whiteJiang,Jiang blackJiang){
        this.whiteJiang = whiteJiang;
        this.blackJiang = blackJiang;
    }
    public int checkWinLose(){
        if(whiteJiang.getEaten()){
            return 0; // 黑方胜利
        }
        if(blackJiang.getEaten()){
            return 1; // 白方胜利
        }
        return -1;
    }
}
