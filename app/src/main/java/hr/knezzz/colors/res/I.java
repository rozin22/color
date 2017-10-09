package hr.knezzz.colors.res;

import java.util.ArrayList;
import java.util.Arrays;

public interface I {
    public static final boolean CHANGE_SIZE = true;

    public static final int MAX_COLOR_RANGE = 20;
    public static final int MIN_COLOR_RANGE = 4;
    public static final int PADDING = 7;

    public static final int MAX_COLOR_LVL = 255;
    public static final int MIN_COLOR_LVL = 0;

    public static final int CHANGE_TO_MEDIUM = 5;
    public static final int CHANGE_TO_HARD = 15;

    public static final int COLUMNS_EASY = 3;
    public static final int ROWS_EASY = 4;

    public static final int ROWS_HARD = 6;
    public static final int COLUMNS_HARD = 5;

    public static final int ROWS_MEDIUM = 5;
    public static final int COLUMNS_MEDIUM = 4;

    public static final ArrayList<Integer> RED_RANGE = new ArrayList<Integer>(Arrays.asList(1, 4, 5, 7));
    public static final ArrayList<Integer> GREEN_RANGE = new ArrayList<Integer>(Arrays.asList(2, 4, 6, 7));
    public static final ArrayList<Integer> BLUE_RANGE = new ArrayList<Integer>(Arrays.asList(3, 5, 6, 7));

    public static final String SCORE_INTENT = "score";
    public static final String ORIGINAL_COLOR_INTENT = "original";
    public static final String CHANGED_COLOR_INTENT = "changed";

    public static final String SETTINGS = "settings";
    public static final String SCORE_SETTINGS = "best";
    public static final String SHOW_HELP = "help";
    public static int GET_HELP_INTENT = 1;

}
