package latte.kr.com.project47seconds;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TodayNewsFragment extends Fragment {
    View mView;
    TextView tvHomeTodaynews1, tvHomeTodaynews2, tvHomeTodaynews3, tvHomeTodaynews4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_todaynews, container, false);
        xmlBinding();
        return mView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public void xmlBinding() {
        tvHomeTodaynews1 = (TextView) mView.findViewById(R.id.tv_todaynews_1);
        tvHomeTodaynews2 = (TextView) mView.findViewById(R.id.tv_todaynews_2);
        tvHomeTodaynews3 = (TextView) mView.findViewById(R.id.tv_todaynews_3);
        tvHomeTodaynews4 = (TextView) mView.findViewById(R.id.tv_todaynews_4);
        tvHomeTodaynews1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeTodaynews1.setTextColor(getResources().getColor(R.color.tealish));
                tvHomeTodaynews2.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews3.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews4.setTextColor(getResources().getColor(R.color.dimmed_color));
            }
        });
        tvHomeTodaynews2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeTodaynews1.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews2.setTextColor(getResources().getColor(R.color.tealish));
                tvHomeTodaynews3.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews4.setTextColor(getResources().getColor(R.color.dimmed_color));
            }
        });
        tvHomeTodaynews3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeTodaynews1.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews2.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews3.setTextColor(getResources().getColor(R.color.tealish));
                tvHomeTodaynews4.setTextColor(getResources().getColor(R.color.dimmed_color));
            }
        });
        tvHomeTodaynews4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomeTodaynews1.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews2.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews3.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomeTodaynews4.setTextColor(getResources().getColor(R.color.tealish));
            }
        });
    }
}
