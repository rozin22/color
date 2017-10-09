package hr.knezzz.colors;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;

import hr.knezzz.colors.res.I;

/**
 * Created by knezzz on 16/10/14 in hr.knezzz.colors.res.
 */
public class HelpActivity extends Activity{
    ColorAdapter ca;
    CheckBox show_again;
    Button close_help;
    boolean showHelp;
    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        userPreferences = getSharedPreferences(I.SETTINGS, 0);
        showHelp = userPreferences.getBoolean(I.SHOW_HELP, true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //Getting px from dp (padding from both sides).
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round((40*2) * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        //Subtracting from total width of the screen.
        final int width = size.x - px;

        final GridView gridview = (GridView) findViewById(R.id.grid_view);
        ca = new ColorAdapter(this, width, -1);
        gridview.setAdapter(ca);

        gridview.setVerticalSpacing(I.PADDING);
        gridview.setHorizontalSpacing(I.PADDING*4);

        if(!showHelp){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setFinishOnTouchOutside(false);

        show_again = (CheckBox)findViewById(R.id.show_help);
        close_help = (Button)findViewById(R.id.close_help);

        close_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpActivity.this, MainActivity.class));
                save();

                finish();
            }
        });
    }
    public void save(){
        SharedPreferences.Editor userPreferencesEditor = userPreferences.edit();
        userPreferencesEditor.putBoolean(I.SHOW_HELP, !show_again.isChecked());
        userPreferencesEditor.apply();
    }

    @Override
    public void onResume(){
        super.onResume();

        Log.e("OnResume", "called");
    }

    @Override
    public void onBackPressed() {
    }
}
