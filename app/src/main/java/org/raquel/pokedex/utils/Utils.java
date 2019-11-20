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
                return R.drawable.rock_tag;
            case "bug":
                return R.drawable.bug_tag;
            case "dragon":
                return R.drawable.dragon_tag;
            case "fairy":
                return R.drawable.fairy_tag;
            case "fighting":
                return R.drawable.fighting_tag;
            case "fire":
                return R.drawable.fire_tag;
            case "flying":
                return R.drawable.flying_tag;
            case "ghost":
                return R.drawable.ghost_tag;
            case "grass":
                return  R.drawable.grass_tag;
            case "ground":
                return R.drawable.ground_tag;
            case "ice":
                return R.drawable.ice_tag;
            case "normal":
                return R.drawable.normal_tag;
            case "poison":
                return R.drawable.poison_tag;
            case "psychic":
                return R.drawable.psychic_tag;
            case "steel":
                return R.drawable.steel_tag;
            case "water":
                return R.drawable.water_tag;
            case "dark":
                return R.drawable.dark_tag;
            case "electric":
                return R.drawable.electric_tag;



            default:
                return 0;
        }
    }




}
