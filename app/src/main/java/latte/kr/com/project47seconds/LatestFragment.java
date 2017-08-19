package latte.kr.com.project47seconds;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LatestFragment extends Fragment {
    View mView;
    TextView  tvHomelatest1, tvHomelatest2, tvHomelatest3, tvHomelatest4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_latest, container, false);
        xmlBinding();


        return mView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void xmlBinding() {
        tvHomelatest1 = (TextView) mView.findViewById(R.id.tv_latest_1);
        tvHomelatest2 = (TextView) mView.findViewById(R.id.tv_latest_2);
        tvHomelatest3 = (TextView) mView.findViewById(R.id.tv_latest_3);
        tvHomelatest4 = (TextView) mView.findViewById(R.id.tv_latest_4);
        tvHomelatest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomelatest1.setTextColor(getResources().getColor(R.color.tealish));
                tvHomelatest2.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest3.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest4.setTextColor(getResources().getColor(R.color.dimmed_color));
            }
        });
        tvHomelatest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomelatest1.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest2.setTextColor(getResources().getColor(R.color.tealish));
                tvHomelatest3.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest4.setTextColor(getResources().getColor(R.color.dimmed_color));
            }
        });
        tvHomelatest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomelatest1.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest2.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest3.setTextColor(getResources().getColor(R.color.tealish));
                tvHomelatest4.setTextColor(getResources().getColor(R.color.dimmed_color));
            }
        });
        tvHomelatest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHomelatest1.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest2.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest3.setTextColor(getResources().getColor(R.color.dimmed_color));
                tvHomelatest4.setTextColor(getResources().getColor(R.color.tealish));
            }
        });
    }
}
