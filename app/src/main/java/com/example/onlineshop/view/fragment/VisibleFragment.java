package com.example.onlineshop.view.fragment;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.onlineshop.event.NotificationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class VisibleFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 2)
    public void onNotificationEventListener(NotificationEvent notificationEvent){
        Log.d("EventBus", "The Fragment received the notification event ");

        EventBus.getDefault().cancelEventDelivery(notificationEvent);

    }

}
