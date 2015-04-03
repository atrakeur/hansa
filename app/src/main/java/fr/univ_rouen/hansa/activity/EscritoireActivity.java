package fr.univ_rouen.hansa.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.view.EscritoirePagerAdapter;


public class EscritoireActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener,
        TabHost.TabContentFactory {

    private EscritoirePagerAdapter mEscritoirePagerAdapter;
    private ViewPager mViewPager;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_escritoire);
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        List<IHTPlayer> players = TurnManager.getInstance().getPlayers();

        for (int i = 0; i < players.size(); i++) {
            TextView view = new TextView(this);
            view.setText("");
            view.setMinimumHeight(50);
            view.setBackgroundColor(players.get((i + TurnManager.getInstance().getPosition()) % players.size()).getPlayerColor().getColor());
            mTabHost.addTab(mTabHost.newTabSpec("" + i).setContent(this).setIndicator(view));
        }
        mTabHost.setOnTabChangedListener(this);
        mTabHost.setCurrentTab(0);

        mEscritoirePagerAdapter = new EscritoirePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mEscritoirePagerAdapter);
        mViewPager.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        mTabHost.setCurrentTab(position);
    }

    @Override
    public void onTabChanged(String tabId) {
        mViewPager.setCurrentItem(Integer.parseInt(tabId));
    }

    @Override
    public View createTabContent(String tag) {
        View dummyContent = new View(this);
        dummyContent.setMinimumHeight(0);
        return dummyContent;
    }
}
