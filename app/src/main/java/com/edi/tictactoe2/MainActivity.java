package com.edi.tictactoe2;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    int player = 0; //0-x,1-y
    boolean gameactive = true;
    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningpos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {3, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void shfaq(View view) {
        ImageView counter = (ImageView) view;
        int tapcount = Integer.parseInt(counter.getTag().toString());
        if (state[tapcount] == 2 && gameactive) {
            state[tapcount] = player;
            counter.setTranslationY(-1000f);
            if (player == 0) {
                counter.setImageResource(R.drawable.cross);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.cirlce3);
                player = 0;
            }
            counter.animate().translationYBy(1000f).rotation(100f).setDuration(300);
            for (int[] winningpos : winningpos) {
                if (state[winningpos[0]] == state[winningpos[1]] &&
                        state[winningpos[1]] == state[winningpos[2]] &&
                        state[winningpos[0]] != 2) {
                    gameactive = false;
                    TextView msg = (TextView) findViewById(R.id.tekst1);
                    msg.setText("Fituesi është Lojtari -> " + " " + (state[winningpos[0]] + 1));

                    LinearLayout layout = (LinearLayout) findViewById(R.id.vertilay);
                    layout.setVisibility(View.VISIBLE);

                } else {
                    boolean gameover = true;
                    for (int i : state) {
                        if (i == 2) {
                            gameover = false;
                        }
                    }
                    if (gameover) {
                        TextView msg = (TextView) findViewById(R.id.tekst1);
                        msg.setText("Barazim!!");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.vertilay);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

        public void luaj (View view){
            gameactive = true;
            LinearLayout layout = (LinearLayout) findViewById(R.id.vertilay);
            layout.setVisibility(View.INVISIBLE);
            player = 0; //0-x,1-y
            for (int a = 0; a < state.length; a++) {
                state[a] = 2;
            }
            GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);

            for (int i = 0; i< gridLayout.getChildCount(); i++) {

                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

            }
        }

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
}