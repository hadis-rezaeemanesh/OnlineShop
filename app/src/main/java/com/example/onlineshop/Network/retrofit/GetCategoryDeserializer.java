package com.example.onlineshop.Network.retrofit;

import com.example.onlineshop.model.Category;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetCategoryDeserializer implements JsonDeserializer<List<Category>> {
    @Override
    public List<Category> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        List<Category> items = new ArrayList<>();

        JsonArray jsonArray = json.getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            int id = jsonObject.get("id").getAsInt();
            String name = jsonObject.get("name").getAsString();
            int count = jsonObject.get("count").getAsInt();

            JsonObject photoJsonObject = jsonObject.get("image").getAsJsonObject();
            String photoUrl = photoJsonObject.get("src").getAsString();

            items.add(new Category(name, id, count, photoUrl));
        }
        return items;
    }
}
