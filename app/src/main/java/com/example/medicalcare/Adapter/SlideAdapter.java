package com.example.medicalcare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.medicalcare.R;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;

    }
    // Arrays
    public int[] slide_images = {
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3
    };

    public String[] slide_headings = {
            "STAY SAFE",
            "KNOW ABOUT YOUR HEALTH",
            "GET MORE HELP"
    };
    public String[] slide_description = {
            "Get uptodate information on different panademic health traits",
            "Search for the symptoms you feeling, and know what possible disease you might have",
            "We couldn't give a full diagnosis, but we can suggest you to the best hoaspital in tour town"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImage = view.findViewById(R.id.icon);
        TextView slideheader= view.findViewById(R.id.header);
        TextView slidedescription = view.findViewById(R.id.description);

        slideImage.setImageResource(slide_images[position]);
        slideheader.setText(slide_headings[position]);
        slidedescription.setText(slide_description[position]);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
