package org.raquel.pokedex.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class Utils {
    public static Drawable getDrawable(Context context, String drawableName) {
        Resources res = context.getResources();
        int resID = res.getIdentifier(drawableName , "drawable", context.getPackageName());
        return res.getDrawable(resID );
    }

    public String damageRelationText(String originalText) {
        switch(originalText) {
            case "double_damage_from":
                return "Doble daño a";

            case "no_damage_from":
                return "Ningún daño de:";
            default:
                return "";
        }
    }
}
