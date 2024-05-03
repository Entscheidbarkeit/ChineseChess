package com.chinesechess;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;




import com.chinesechess.ChessBoard.ChessBoard;
import com.chinesechess.pieces.Jiang;
import com.chinesechess.pieces.Ju;
import com.chinesechess.pieces.Ma;
import com.chinesechess.pieces.Pao;
import com.chinesechess.pieces.PieceView;
import com.chinesechess.pieces.Shi;
import com.chinesechess.pieces.Xiang;
import com.chinesechess.pieces.Zu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Log.println(Log.ASSERT,"Info","clear");
        setContentView(R.layout.activity_main);
        ChessBoard board = findViewById(R.id.chessBoard);
        addPiece(board);

    }
    private void addPiece(ChessBoard board){
        ConstraintLayout father = findViewById(R.id.main);
        PieceView[][] sit = board.getSituation();

        Ju whiteJu1 = new Ju(this.getBaseContext(),"white_ju",null,board,0,0); // 此时的点位坐标尚未更新
        //ConstraintLayout.LayoutParams whileJu1Param = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //whiteJu1.setLayoutParams(whileJu1Param);
        Ju whiteJu2 = new Ju(this.getBaseContext(),"white_ju",null,board,8,0);
        sit[0][0] = whiteJu1;
        sit[8][0] = whiteJu2;
        board.setSituation(sit);

        father.addView(whiteJu1);
        father.addView(whiteJu2);

        Ma whiteMa1 = new Ma(this.getBaseContext(),"white_ma",null,board,1,0);
        sit[1][0] = whiteMa1;
        father.addView(whiteMa1);

        Ma whiteMa2 = new Ma(this.getBaseContext(),"white_ma",null,board,7,0);
        sit[7][0] = whiteMa2;
        father.addView(whiteMa2);

        Xiang whiteXiang1 = new Xiang(this.getBaseContext(),"white_xiang",null,board,2,0);
        sit[2][0] = whiteXiang1;
        father.addView(whiteXiang1);

        Xiang whiteXiang2 = new Xiang(this.getBaseContext(),"white_xiang",null,board,6,0);
        sit[6][0] = whiteXiang2;
        father.addView(whiteXiang2);

        Shi whiteShi1 = new Shi(this.getBaseContext(),"white_shi",null,board,3,0);
        sit[3][0] = whiteShi1;
        father.addView(whiteShi1);

        Shi whiteShi2 = new Shi(this.getBaseContext(),"white_shi",null,board,5,0);
        sit[5][0] = whiteShi2;
        father.addView(whiteShi2);

        Jiang whiteJiang = new Jiang(this.getBaseContext(),"white_jiang",null,board,4,0);
        sit[4][0] = whiteJiang;
        father.addView(whiteJiang);

        Pao whitePao1 = new Pao(this.getBaseContext(),"white_pao",null,board,1,2);
        sit[1][2] = whitePao1;
        father.addView(whitePao1);

        Pao whitePao2 = new Pao(this.getBaseContext(),"white_pao",null,board,7,2);
        sit[7][2] = whitePao2;
        father.addView(whitePao2);

        Zu whiteZu1 = new Zu(this.getBaseContext(),"white_zu",null,board,0,3);
        sit[0][3] = whiteZu1;
        father.addView(whiteZu1);

        Zu whiteZu2 = new Zu(this.getBaseContext(),"white_zu",null,board,2,3);
        sit[2][3] = whiteZu2;
        father.addView(whiteZu2);

        Zu whiteZu3 = new Zu(this.getBaseContext(),"white_zu",null,board,4,3);
        sit[4][3] = whiteZu3;
        father.addView(whiteZu3);

        Zu whiteZu4 = new Zu(this.getBaseContext(),"white_zu",null,board,6,3);
        sit[6][3] = whiteZu4;
        father.addView(whiteZu4);

        Zu whiteZu5 = new Zu(this.getBaseContext(),"white_zu",null,board,8,3);
        sit[8][3] = whiteZu5;
        father.addView(whiteZu5);
        ///////////////////////////////////////////////////
        Ju blackJu1 = new Ju(this.getBaseContext(),"black_ju",null,board,0,9);
        sit[0][9] = blackJu1;
        father.addView(blackJu1);

        Ju blackJu2 = new Ju(this.getBaseContext(),"black_ju",null,board,8,9);
        sit[8][9] = blackJu2;
        father.addView(blackJu2);

        Ma blackMa1 = new Ma(this.getBaseContext(),"black_ma",null,board,1,9);
        sit[1][9] = blackMa1;
        father.addView(blackMa1);

        Ma blackMa2 = new Ma(this.getBaseContext(),"black_ma",null,board,7,9);
        sit[7][9] = blackMa2;
        father.addView(blackMa2);

        Xiang blackXiang1 = new Xiang(this.getBaseContext(),"black_xiang",null,board,2,9);
        sit[2][9] = blackXiang1;
        father.addView(blackXiang1);

        Xiang blackXiang2 = new Xiang(this.getBaseContext(),"black_xiang",null,board,6,9);
        sit[6][9] = blackXiang2;
        father.addView(blackXiang2);

        Shi blackShi1 = new Shi(this.getBaseContext(),"black_shi",null,board,3,9);
        sit[3][9] = blackShi1;
        father.addView(blackShi1);

        Shi blackShi2 = new Shi(this.getBaseContext(),"black_shi",null,board,5,9);
        sit[5][9] = blackShi2;
        father.addView(blackShi2);

        Jiang blackJiang = new Jiang(this.getBaseContext(),"black_jiang",null,board,4,9);
        sit[4][9] = blackJiang;
        father.addView(blackJiang);

        Pao blackPao1 = new Pao(this.getBaseContext(),"black_pao",null,board, 1,7);
        sit[2][7] = blackPao1;
        father.addView(blackPao1);

        Pao blackPao2 = new Pao(this.getBaseContext(),"black_pao",null,board, 7,7);
        sit[7][7] = blackPao2;
        father.addView(blackPao2);

        Zu blackZu1 = new Zu(this.getBaseContext(),"black_zu",null,board,0,6);
        sit[0][6] = blackZu1;
        father.addView(blackZu1);

        Zu blackZu2 = new Zu(this.getBaseContext(),"black_zu",null,board,2,6);
        sit[2][6] = blackZu2;
        father.addView(blackZu2);

        Zu blackZu3 = new Zu(this.getBaseContext(),"black_zu",null,board,4,6);
        sit[4][6] = blackZu3;
        father.addView(blackZu3);

        Zu blackZu4 = new Zu(this.getBaseContext(),"black_zu",null,board,6,6);
        sit[6][6] = blackZu4;
        father.addView(blackZu4);

        Zu blackZu5 = new Zu(this.getBaseContext(),"black_zu",null,board,8,6);
        sit[8][6] = blackZu5;
        father.addView(blackZu5);
    }
}