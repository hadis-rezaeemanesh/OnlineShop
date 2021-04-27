package com.example.onlineshop.Network.retrofit;

import android.util.Log;

import com.example.onlineshop.model.Product;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetProductDeserializer implements JsonDeserializer<List<Product>> {
    public static final String TAG = "GetProductDeserializer";
    @Override
    public List<Product> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        List<Product> items = new ArrayList<>();

        JsonArray jsonArray = json.getAsJsonArray();
        for (int i = 0; i <jsonArray.size() ; i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            if (!jsonObject.has("url"))
                continue;

            int id = jsonObject.get("id").getAsInt();
            String name = jsonObject.get("name").getAsString();
            String price = jsonObject.get("price").getAsString();
            String url = jsonObject.get("permalink").getAsString();
            int rate = jsonObject.get("rating_count").getAsInt();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = new Date();
            try {
                date = format.parse(jsonObject.get("date_created").getAsString());
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            JsonArray photoArray = jsonObject.get("images").getAsJsonArray();
            List<String> photoUrls = new ArrayList<>();
            for (int j = 0; j < photoArray.size(); j++) {
                JsonObject photoObject = photoArray.get(j).getAsJsonObject();
                String photoUrl = photoObject.get("src").getAsString();
                photoUrls.add(photoUrl);
            }

            JsonArray categoryArray = jsonObject.get("categories").getAsJsonArray();
            List<Integer> categoriesId = new ArrayList<>();
            for (int j = 0; j < categoryArray.size(); j++) {
                JsonObject categoryObject = categoryArray.get(j).getAsJsonObject();
                int idCategory = categoryObject.get("id").getAsInt();
                categoriesId.add(idCategory);
            }

            Product item = new Product(id, name, price, url, rate, date, photoUrls, categoriesId);
            items.add(item);
        }
        return items;
    }
}
