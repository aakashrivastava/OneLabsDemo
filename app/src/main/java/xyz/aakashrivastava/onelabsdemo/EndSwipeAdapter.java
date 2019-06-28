package xyz.aakashrivastava.onelabsdemo;


import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EndSwipeAdapter extends PagerAdapter {

    FragmentActivity activity;
    ArrayList<ImageModel> imageModels;
    int xxposition = 1;

    public EndSwipeAdapter(FragmentActivity fragmentActivity, ArrayList<ImageModel> arrayList, int positionxx) {
        imageModels = arrayList;
        activity = fragmentActivity;
        xxposition = positionxx;
    }


    @Override
    public int getCount() {
        return imageModels.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(activity);
        container.addView(imageView, 0);

        Picasso.get().load(imageModels.get(xxposition).getUrl()).into(imageView);

        if(xxposition >= imageModels.size() -1) xxposition = 0;
        else xxposition++;
        return imageView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
