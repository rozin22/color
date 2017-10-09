package hr.knezzz.colors;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import hr.knezzz.colors.res.I;

public class MainActivity extends Activity implements I{
    ColorAdapter ca;
    private int currentScore, bestScore;
    TextView score, highScore;
    SharedPreferences userPreferences;
    SharedPreferences.Editor userPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferences = getSharedPreferences(SETTINGS, 0);
        bestScore = userPreferences.getInt(SCORE_SETTINGS, 0);

        setUpView();
    }

    public void setUpView(){
        currentScore = 0;

        score = (TextView)findViewById(R.id.score);
        highScore = (TextView)findViewById(R.id.highScore);

        highScore.setText(bestScore+"");
        score.setText(currentScore+"");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //Getting px from dp (padding from both sides).
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round((20*2) * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        //Subtracting from total width of the screen.
        final int width = size.x - px;

        final GridView gridview = (GridView) findViewById(R.id.grid_view);
        ca = new ColorAdapter(this, width, currentScore);
        gridview.setAdapter(ca);

        gridview.setVerticalSpacing(PADDING);
        gridview.setHorizontalSpacing(PADDING);

        if(CHANGE_SIZE)
            gridview.setNumColumns(COLUMNS_EASY);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                boolean isCorrect = ca.getPosition() == position;
                if(isCorrect){
                    currentScore++;
                    score.setText(currentScore+"");

                    if(CHANGE_SIZE) {
                        if (currentScore >= CHANGE_TO_HARD)
                            gridview.setNumColumns(COLUMNS_HARD);
                        else if (currentScore >= CHANGE_TO_MEDIUM)
                            gridview.setNumColumns(COLUMNS_MEDIUM);
                    }

                    if(currentScore > bestScore){
                        highScore.setText(currentScore+"");
                    }

                    gridview.setAdapter(ca = new ColorAdapter(MainActivity.this, width, currentScore));
                }else{
                    boolean isBest = false;
                    if(currentScore > bestScore){
                        saveBestScore(currentScore);
                        isBest = true;
                    }

                    Intent sentIntent = new Intent(MainActivity.this, GameOver.class);
                    sentIntent.putExtra(ORIGINAL_COLOR_INTENT, ca.getColor());
                    sentIntent.putExtra(CHANGED_COLOR_INTENT, ca.getChangedColor());
                    sentIntent.putExtra(SCORE_INTENT, isBest ? "NEW BEST SCORE: "+currentScore : "Score: "+currentScore);

                    startActivity(sentIntent);

                    if(CHANGE_SIZE) {
                        gridview.setNumColumns(COLUMNS_EASY);
                    }

                    currentScore = 0;

                    gridview.setAdapter(ca = new ColorAdapter(MainActivity.this, width, currentScore));

                    score.setText(currentScore+"");
                }
            }
        });
    }

    private void saveBestScore(int score) {
        userPreferencesEditor = userPreferences.edit();
        userPreferencesEditor.putInt(SCORE_SETTINGS, score);
        userPreferencesEditor.apply();
    }
}
