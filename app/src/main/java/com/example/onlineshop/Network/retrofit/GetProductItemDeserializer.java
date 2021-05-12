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

public class GetProductItemDeserializer implements JsonDeserializer<Product> {

    public static final String TAG = "GetProductItemDeserializer";

    @Override
    public Product deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        int id = jsonObject.get("id").getAsInt();
        String productName = jsonObject.get("name").getAsString();
        String productPrice = jsonObject.get("price").getAsString();
        String url = jsonObject.get("permalink").getAsString();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        try {
            date = format.parse(jsonObject.get("date_created").getAsString());
        } catch (
                ParseException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        int rate = jsonObject.get("rating_count").getAsInt();

        String description = jsonObject.get("description").getAsString();

        JsonArray photoArray = jsonObject.get("images").getAsJsonArray();
        List<String> photoUrl = new ArrayList<>();
        for (int j = 0; j < photoArray.size(); j++) {
            JsonObject photoJsonObject = photoArray.get(j).getAsJsonObject();
            photoUrl.add(photoJsonObject.get("src").getAsString());
        }

        JsonArray categoryIdArray = jsonObject.get("categories").getAsJsonArray();
        List<Integer> categoriesId = new ArrayList<>();
        for (int j = 0; j < categoryIdArray.size(); j++) {
            JsonObject idJsonObject = categoryIdArray.get(j).getAsJsonObject();
            categoriesId.add(idJsonObject.get("id").getAsInt());
        }

        JsonArray relatedIdArray = jsonObject.get("related_ids").getAsJsonArray();
        List<Integer> relatedIds = new ArrayList<>();
        for (int j = 0; j < relatedIdArray.size(); j++) {
            relatedIds.add(relatedIdArray.get(j).getAsInt());
        }

        return new Product(id, productName, productPrice, url, rate, date,
                photoUrl, categoriesId, description, relatedIds);
    }


}
