package com.wapazock.doozby.Utils;

import android.util.Log;

import com.wapazock.doozby.R;

import java.util.Random;

public class BlurredImageHelper {

    public static int getRandomBlurredImage() {
        // Guess Random Number
        Random random_number_generator = new Random();
        int random_number = random_number_generator.nextInt(6);
        int a = random_number;

        switch (random_number) {
            case 0:
                return R.drawable.blurred_blue;
            case 1:
                return R.drawable.blurred_gray;
            case 2:
                return R.drawable.blurred_green;
            case 3:
                return R.drawable.blurred_orange;
            case 4:
                return R.drawable.blurred_purple;
            default:
                return R.drawable.blurred_red;
        }
    }
}
