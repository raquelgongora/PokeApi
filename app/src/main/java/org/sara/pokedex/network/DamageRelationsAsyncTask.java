package org.sara.pokedex.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sara.pokedex.entities.DamageRelations;


import java.util.ArrayList;
import java.util.List;


public class DamageRelationsAsyncTask  {

    public static List<DamageRelations> parseDamageRelationsFromJSONObject(String jsonString) {
        List<DamageRelations> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int position = 0; position < results.length(); position++) {
                list.add(parseDamageRelationsFromJSONObject(results.getJSONObject(position)));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static DamageRelations parseDamageRelationsFromJSONObject(JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString("name");
        String url = jsonObject.getString("url");
        DamageRelations damageRelation = new DamageRelations(name, url);
        damageRelation.setDoubleDamageFrom(getTypesFromJSONArray(jsonObject,"double_damage_from"));
        return damageRelation;
    }

    public static List<String> getTypesFromJSONArray(JSONObject object, String key) throws JSONException {
        List<String> types = new ArrayList<>();
        JSONArray results = object.getJSONArray(key);

        for(int position = 0; position < results.length(); position++){
            types.add(results.getJSONObject(position).getString("name"));
        }
        return types;
    }
}