package com.example.onlineshop.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.model.Review;
import com.example.onlineshop.repository.CustomerRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.utilities.QueryPreferences;

import java.util.List;

public class ProductPageViewModel extends ProductViewModel {

    private LiveData<Product> mProductLiveData;
    private LiveData<List<Product>> mRelatedProducts;
    private LiveData<List<Review>> mReviewLiveData;
    private ProductRepository mRepository;
    private CustomerRepository mCustomerRepository;

    private String mRating;
    private String mReview;

    public LiveData<Product> getProductLiveData() {
        return mProductLiveData;
    }

    public LiveData<List<Product>> getRelatedProducts() {
        return mRelatedProducts;
    }

    public LiveData<List<Review>> getReviewLiveData() {
        return mReviewLiveData;
    }

    public ProductPageViewModel(@NonNull Application application) {
        super(application);
        mRepository = ProductRepository.getInstance();
        mProductLiveData = mRepository.getProductItemLiveData();
        mRelatedProducts = mRepository.getRatedProductsLiveData();
        mCustomerRepository = CustomerRepository.getInstance();
        mReviewLiveData = mCustomerRepository.getReviewLiveData();
    }

    public void fetchRelatedProducts(List<Integer> productRelatedIds){
        mRepository.fetchRelatedProducts(productRelatedIds);
    }

    public void addToCartClicked(){
        Product item = mProductLiveData.getValue();
        item.setCountInCart(1);
        QueryPreferences.addCartProduct(getApplication(), item);
        mRepository.setCartItemLiveData(QueryPreferences.getCartProducts(getApplication()));
    }

    public void sendReview(){
        Review review = new Review(
                QueryPreferences.getUserName(getApplication()),
                QueryPreferences.getUserEmail(getApplication()),
                Integer.parseInt(mRating),
                mReview,
                getProductLiveData().getValue().getId());
        mCustomerRepository.sendReview(review);
    }

    public void onTextChangedReview(CharSequence charSequence, int i, int i1, int i2) {
        mReview = charSequence.toString();
    }

    public void onTextChangedRating(CharSequence charSequence, int i, int i1, int i2) {
        mRating = charSequence.toString();
    }
}
