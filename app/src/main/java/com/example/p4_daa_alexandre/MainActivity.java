package com.example.p4_daa_alexandre;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;

   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(R.layout.activity_main);

       setSupportActionBar(binding.toolbar);
       //mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
       //mViewPager.setAdapter(mPagerAdapter);
       //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
       //mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
   }

}
