package org.raquel.pokedex.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import org.raquel.pokedex.R;

public class Utils {
    public static Drawable getDrawable(Context context, String drawableName) {
        Resources res = context.getResources();
        int resID = res.getIdentifier(drawableName , "drawable", context.getPackageName());
        return res.getDrawable(resID );
    }

    public static int getTypeImageResource(String type) {
        switch (type) {
            case "rock":
                return R.drawable.rock;
            case "bug":
                return R.drawable.bug;
            case "dragon":
                return R.drawable.dragon;
            case "fairy":
                return R.drawable.fairy;
            case "fighting":
                return R.drawable.fighting;
            case "fire":
                return R.drawable.fire;
            case "flying":
                return R.drawable.flying;
            case "ghost":
                return R.drawable.ghost;
            case "grass":
                return  R.drawable.grass;
            case "ground":
                return R.drawable.ground;
            case "ice":
                return R.drawable.ice;
            case "normal":
                return R.drawable.normal;
            case "poison":
                return R.drawable.poison;
            case "psychic":
                return R.drawable.psychic;
            case "steel":
                return R.drawable.steel;
            case "water":
                return R.drawable.water;
            case "dark":
                return R.drawable.dark;



            default:
                return 0;
        }
    }
}
