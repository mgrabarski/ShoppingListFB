package com.mateusz.grabarski.myshoppinglist.views.activities.help;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.mateusz.grabarski.myshoppinglist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.activity_help_top_tv)
    TextView topTv;

    @BindView(R.id.activity_help_bottom_tv)
    TextView bottomTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);

        Animation upToDown = AnimationUtils.loadAnimation(this, R.anim.up_to_down);
        Animation downToUp = AnimationUtils.loadAnimation(this, R.anim.down_to_up);

        topTv.setAnimation(upToDown);
        bottomTv.setAnimation(downToUp);
    }
}
