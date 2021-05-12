package com.example.onlineshop.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.repository.ProductRepository;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mRepository;
    private MutableLiveData<Integer> mClickedItem;

    public MutableLiveData<Integer> getClickedItem() {
        mClickedItem = new MutableLiveData<>();
        return mClickedItem;
    }

    public ProductViewModel(@NonNull Application application) {
        super(application);

        mRepository = ProductRepository.getInstance();
    }

    public void onItemClicked(int id){
        Log.e("productItemClicked", "this id clicked in pvm" + id);
        mRepository.fetchProductWithId(id);
        mClickedItem.setValue(id);
    }

}
