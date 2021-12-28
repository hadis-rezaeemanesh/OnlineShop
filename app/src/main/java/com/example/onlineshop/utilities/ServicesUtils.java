package com.example.onlineshop.utilities;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.onlineshop.R;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.view.activity.HomePageActivity;

import java.util.List;

public class ServicesUtils {

    private static final int NOTIFICATION_ID = 1;

    public static void pollAndShowNotification(Context context, String tag){

        ProductRepository productRepository = ProductRepository.getInstance();

        productRepository.fetchNewestProductsList(productRepository.getPerPage().getValue());
        List<Product> items =  productRepository.getNewestProductsLiveData().getValue();

        if(items.size() == 0 || items == null)
            return;

        int lastItemId = items.get(0).getId();
        int lastSavedId = QueryPreferences.getProductId(context);

        if(lastSavedId != lastItemId) {
            Log.d(tag, "show notification");
            sendNotificationEvent(context);
        }

        QueryPreferences.setProductId(context, lastItemId);

    }

    private static void sendNotificationEvent(Context context) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                HomePageActivity.newIntent(context),
                0);

        String channelId = context.getResources().getString(R.string.channel_id);
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(context.getResources().getString(R.string.new_product_title))
                .setContentText(context.getResources().getString(R.string.new_product_text))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
}
