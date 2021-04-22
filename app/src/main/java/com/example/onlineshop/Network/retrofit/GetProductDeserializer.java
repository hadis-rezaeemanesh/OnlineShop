package com.example.onlineshop.Network.retrofit;

import com.example.onlineshop.model.Product;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetProductDeserializer implements JsonDeserializer<List<Product>> {
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

            Product item = new Product(id, name, price, url);
            items.add(item);
        }
        return items;
    }
}
