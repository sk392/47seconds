package latte.kr.com.project47seconds;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import latte.kr.com.project47seconds.Library.NonSwipeViewPager;

public class HomeActivity extends AppCompatActivity {

    NonSwipeViewPager viewPager;
    TabLayout tabLayout;
    LatestFragment latestFragment;
    ProfileFragment profileFragment;
    TodayNewsFragment todayNewsFragment;
    MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager = (NonSwipeViewPager) findViewById(R.id.viewpager);
        latestFragment = new LatestFragment();
        profileFragment = new ProfileFragment();
        todayNewsFragment = new TodayNewsFragment();
        adapter.addFragment(latestFragment);
        adapter.addFragment(todayNewsFragment);
        adapter.addFragment(profileFragment);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.btn_latest);
        tabLayout.getTabAt(1).setIcon(R.drawable.btn_today_clicked);
        tabLayout.getTabAt(2).setIcon(R.drawable.btn_profile);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //탭을 눌렀을 때 화면도 바꿔준다.
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.getTabAt(0).setIcon(R.drawable.btn_latest);
                tabLayout.getTabAt(1).setIcon(R.drawable.btn_today);
                tabLayout.getTabAt(2).setIcon(R.drawable.btn_profile);

                getSupportFragmentManager().popBackStack();
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.btn_latest_clicked);

                } else if (tab.getPosition() == 1) {

                    tab.setIcon(R.drawable.btn_today_clicked);
                } else if (tab.getPosition() == 2) {

                    tab.setIcon(R.drawable.btn_profile_clicked);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setCurrentItem(1);

    }

    public class MainPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        Context mContext;
        List<Fragment> mFragments = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


            return mFragments.get(position);
        }

        public Fragment requestNews() {
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mFragments.size();
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }
    }

}
