package hr.knezzz.colors;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import hr.knezzz.colors.res.I;

public class ColorAdapter extends BaseAdapter implements I{
    private Context mContext;

    private int COLUMNS_NO = 4;
    private int ROWS_NO = 5;

    private int score;
    private static int differentOne;
    int red = (int)(Math.random()*MAX_COLOR_LVL);
    int blue = (int)(Math.random()*MAX_COLOR_LVL);
    int green = (int)(Math.random()*MAX_COLOR_LVL);

    int newRed = red;
    int newGreen = green;
    int newBlue = blue;

    int width;

    public ColorAdapter(Context c, int width, int score) {
        mContext = c;
        this.score = score;
        this.width = width;

        ROWS_NO = ROWS_EASY;
        COLUMNS_NO = COLUMNS_EASY;

        if(CHANGE_SIZE) {
            if (score >= CHANGE_TO_HARD) {
                ROWS_NO = ROWS_HARD;
                COLUMNS_NO = COLUMNS_HARD;
            } else if (score >= CHANGE_TO_MEDIUM) {
                ROWS_NO = ROWS_MEDIUM;
                COLUMNS_NO = COLUMNS_MEDIUM;
            }
        }

        if(score == -1){
            ROWS_NO = 3;
            COLUMNS_NO = 3;
        }

        differentOne = (int)(Math.random()*(COLUMNS_NO*ROWS_NO));
    }

    public int setColor(int b){
        if(b == differentOne){
            int changeColor;

            if(score < CHANGE_TO_MEDIUM) {
                changeColor = (int)(Math.random()*4)+4;
            }else if(score < CHANGE_TO_HARD){
                changeColor = (int)(Math.random()*6)+1;
            }else{
                changeColor = 7;
            }

            if(RED_RANGE.contains(changeColor)){
                int changeFor = (int)(Math.random()*MAX_COLOR_RANGE)+MIN_COLOR_RANGE;

                if(red+changeFor < MAX_COLOR_LVL && red-changeFor > MIN_COLOR_LVL){
                    if(Math.random() > 0.5){
                        newRed = red-changeFor;
                    }else{
                        newRed = red+changeFor;
                    }
                }else if(red+changeFor > MAX_COLOR_LVL){
                    newRed = red-changeFor;
                }else {
                    newRed = red+changeFor;
                }
            }

            if(GREEN_RANGE.contains(changeColor)){
                int changeFor = (int)(Math.random()*MAX_COLOR_RANGE)+MIN_COLOR_RANGE;

                if(green+changeFor < MAX_COLOR_LVL && green-changeFor > MIN_COLOR_LVL){
                    if(Math.random() > 0.5){
                        newGreen = green-changeFor;
                    }else{
                        newGreen = green+changeFor;
                    }
                }else if(green+changeFor > MAX_COLOR_LVL){
                    newGreen = green-changeFor;
                }else {
                    newGreen = green+changeFor;
                }
            }

            if(BLUE_RANGE.contains(changeColor)){
                int changeFor = (int)(Math.random()*MAX_COLOR_RANGE)+MIN_COLOR_RANGE;

                if(blue+changeFor < MAX_COLOR_LVL && blue-changeFor > MIN_COLOR_LVL){
                    if(Math.random() > 0.5){
                        newBlue = blue-changeFor;
                    }else{
                        newBlue = blue+changeFor;
                    }
                }else if(blue+changeFor > MAX_COLOR_LVL){
                    newBlue = blue-changeFor;
                }else {
                    newBlue = blue+changeFor;
                }
            }

            Log.e("Changed color", red+"/"+green+"/"+blue+ " -- " +newRed+"/"+newGreen+"/"+newBlue);
            return Color.rgb(newRed, newGreen, newBlue);
        }else{
            return Color.rgb(red, green, blue);
        }
    }

    public int getColor(){
        return Color.rgb(red, green, blue);
    }

    public int getChangedColor(){
        return Color.rgb(newRed, newGreen, newBlue);
    }

    public int getPosition(){
        return differentOne;
    }

    public int getCount() {
        return ROWS_NO*COLUMNS_NO;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            int size = (width/COLUMNS_NO)-(PADDING);
            imageView.setLayoutParams(new GridView.LayoutParams(size, size));
        } else {
            imageView = (ImageView) convertView;
        }

        if(position == differentOne && score == -1){
            imageView.setImageResource(R.drawable.help_one);
        }
        imageView.setBackgroundColor(setColor(position));
        return imageView;
    }
}
