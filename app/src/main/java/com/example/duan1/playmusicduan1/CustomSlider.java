package com.example.duan1.playmusicduan1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by trongnhan on 11/1/16.
 */

public class CustomSlider extends PagerAdapter {
    private int[] imageResource={R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};
    private Context context;
    private LayoutInflater inflater;

    public CustomSlider(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return imageResource.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.image_slider,container,false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imgSlider);
        imageView.setImageResource(imageResource[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}
