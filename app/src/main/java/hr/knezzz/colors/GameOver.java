package hr.knezzz.colors;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hr.knezzz.colors.res.I;

public class GameOver extends Activity implements I{
    int original, changed;
    String scoreString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        Intent colors = getIntent();

        if(colors.hasExtra(ORIGINAL_COLOR_INTENT) && colors.hasExtra(CHANGED_COLOR_INTENT) && colors.hasExtra(SCORE_INTENT)){
            original = colors.getIntExtra(ORIGINAL_COLOR_INTENT, 0);
            changed = colors.getIntExtra(CHANGED_COLOR_INTENT, 0);
            scoreString = colors.getStringExtra(SCORE_INTENT);
        }

        setUpText();

        LinearLayout back = (LinearLayout)findViewById(R.id.gameOver_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setUpText(){
        int textColor = getComplimentColor(original);

        TextView originalView, changedView, scoreView, gameOverView, originalHint, changedHint, tapAnywhere;
        ImageView originalColor, changedColor;

        originalView = (TextView)findViewById(R.id.original_color_text);
        changedView = (TextView)findViewById(R.id.changed_color_text);
        gameOverView = (TextView)findViewById(R.id.gameOver_text);
        originalHint = (TextView)findViewById(R.id.original_color_hint);
        changedHint = (TextView)findViewById(R.id.changed_color_hint);
        tapAnywhere = (TextView)findViewById(R.id.tap_anywhere);

        originalView.setText(String.format("#%06X", (0xFFFFFF & original)));
        changedView.setText(String.format("#%06X", (0xFFFFFF & changed)));
        originalView.setTextColor(textColor);
        changedView.setTextColor(getComplimentColor(changed));

        originalColor = (ImageView)findViewById(R.id.original_color);
        changedColor = (ImageView)findViewById(R.id.changed_color);
        originalColor.setBackgroundColor(original);
        changedColor.setBackgroundColor(changed);

        gameOverView.setTextColor(textColor);
        originalHint.setTextColor(textColor);
        changedHint.setTextColor(textColor);
        tapAnywhere.setTextColor(textColor);

        scoreView = (TextView)findViewById(R.id.gameOver_score);
        scoreView.setTextColor(textColor);
        scoreView.setText(scoreString);
    }

    public int getComplimentColor(int color){
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);

        red = (~red) & 0xff;
        blue = (~blue) & 0xff;
        green = (~green) & 0xff;

        return Color.argb(alpha, red, green, blue);
    }
}
