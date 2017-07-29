package latte.kr.com.project47seconds;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private LineChart lineChart;
    private NewsAdapter newsAdapter;

    ProfileFragment profileFragment = null;
    TodayNewsFragment todayNewsFragment = null;
    LatestFragment latestFragment = null;

    ImageButton btYesterday, btToday, btProfile;
    LinearLayout layoutProfile, layoutNews;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setBackgroundResource(R.drawable.img_toolbar);
//        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

//        initRecyclerViewpager();

        initViewPager();


        init();

        profileFragment = new ProfileFragment();
//        todayNewsFragment = new TodayNewsFragment();
        //chartInit();
    }
    private void initViewPager() {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(latestFragment);
        mSectionsPagerAdapter.addFragment(todayNewsFragment);
        mSectionsPagerAdapter.addFragment(profileFragment);
//         Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager_home);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.lightGrey));
        tabLayout.setTabTextColors(getResources().getColor(R.color.darkGrey), getResources().getColor(R.color.tealish));

        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //탭을 눌렀을 때 화면도 바꿔준다.
                mViewPager.setCurrentItem(tab.getPosition());
                getSupportFragmentManager().popBackStack();
                tabLayout.getTabAt(0).setIcon(R.drawable.btn_latest);
                tabLayout.getTabAt(1).setIcon(R.drawable.btn_today_clicked);
                tabLayout.getTabAt(2).setIcon(R.drawable.btn_profile);
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.btn_latest_clicked);
                    //some
                } else if (tab.getPosition() == 1) {
                    tab.setIcon(R.drawable.btn_today_clicked);

                } else if (tab.getPosition() == 2) {
                    tab.setIcon(R.drawable.btn_profile);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
        public void addFragment(Fragment fragment){
            mFragments.add(fragment);
        }
    }

    private void init() {


//        // 지난뉴스
//        btYesterday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (layoutNews.getVisibility() == View.INVISIBLE) {
//                    layoutProfile.setVisibility(View.INVISIBLE);
//                    layoutNews.setVisibility(View.VISIBLE);
//                    btYesterday.setImageResource(R.drawable.btn_latest_clicked);
//                    btToday.setImageResource(R.drawable.btn_today);
//                    btProfile.setImageResource(R.drawable.btn_profile);
//                }
//            }
//        });
//
//        // 오늘뉴스
//        btToday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (layoutNews.getVisibility() == View.INVISIBLE) {
//                    layoutProfile.setVisibility(View.INVISIBLE);
//                    layoutNews.setVisibility(View.VISIBLE);
//                    btYesterday.setImageResource(R.drawable.btn_latest);
//                    btToday.setImageResource(R.drawable.btn_today_clicked);
//                    btProfile.setImageResource(R.drawable.btn_profile);
//                }
//            }
//        });
//
//        //프로필
//        btProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (layoutProfile.getVisibility() == View.INVISIBLE) {
//                    layoutProfile.setVisibility(View.VISIBLE);
//                    layoutNews.setVisibility(View.INVISIBLE);
//                    btYesterday.setImageResource(R.drawable.btn_latest);
//                    btToday.setImageResource(R.drawable.btn_today);
//                    btProfile.setImageResource(R.drawable.btn_profile_clicked);
//                }
//            }
//        });
    }

    public void chartInit() {
        lineChart = (LineChart) findViewById(R.id.graph_profile);
        lineChart.animateY(2000);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        // z : 라인 수
//        for (int z = 0; z < 3; z++) {

            ArrayList<Entry> values = new ArrayList<Entry>();

        // i : 데이터 10개
            for (int i = 0; i < 10; i++) {
                int val = (int)(Math.random() * 5 + 30);
                values.add(new Entry(i, val));
            }

            LineDataSet d = new LineDataSet(values, "DataSet");
            d.setLineWidth(1f);
            d.setCircleRadius(4f);

//            int color = mColors[z % mColors.length];
//            d.setColor(color);
//            d.setCircleColor(color);
            dataSets.add(d);
//        }

        // make the first DataSet dashed
//        ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
//        ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
//        ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);


        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
    }
}
