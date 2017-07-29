package latte.kr.com.project47seconds;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

public class HomeActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    ProfileFragment profileFragment = null;
    TodayNewsFragment todayNewsFragment = null;

    ImageButton btYesterday, btToday, btProfile;
    LinearLayout layoutProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container_news);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        init();
        profileFragment = new ProfileFragment();
//        todayNewsFragment = new TodayNewsFragment();
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

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).


            return PlaceholderFragment.newInstance(position + 1);

//            switch (position) {
//                case 0:
//                    return profileFragment;
//                case 1:
//                    return profileFragment;
//                case 2:
//                    return profileFragment;
//                case 3:
//                    return profileFragment;
//            }
//            return null;
        }

        public Fragment requestNews() {

            return
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "IT/과학";
                case 1:
                    return "정치";
                case 2:
                    return "경제";
                case 3:
                    return "사회";
            }
            return null;
        }
    }

    private void init() {
        btYesterday = (ImageButton) findViewById(R.id.bt_home_yesterday);
        btToday = (ImageButton) findViewById(R.id.bt_home_today);
        btProfile = (ImageButton) findViewById(R.id.bt_home_profile);
        layoutProfile = (LinearLayout) findViewById(R.id.container_profile);


        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutProfile.getVisibility() == View.VISIBLE) {
                    layoutProfile.setVisibility(View.INVISIBLE);
                    mViewPager.setVisibility(View.VISIBLE);
                } else {
                    layoutProfile.setVisibility(View.VISIBLE);
                    mViewPager.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(getBaseContext(), "profile", Toast.LENGTH_SHORT);
            }
        });
    }
}
