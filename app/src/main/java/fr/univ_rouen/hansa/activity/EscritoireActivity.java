package fr.univ_rouen.hansa.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.view.EscritoirePagerAdapter;


public class EscritoireActivity extends FragmentActivity {

    EscritoirePagerAdapter mEscritoirePagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_escritoire);

        mEscritoirePagerAdapter = new EscritoirePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mEscritoirePagerAdapter);
    }

}
