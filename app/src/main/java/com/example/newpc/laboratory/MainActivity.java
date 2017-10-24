package com.example.newpc.laboratory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.newpc.laboratory.adapters.MyFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
       // mTabLayout.addTab(mTabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        mViewPager = (ViewPager) findViewById(R.id.view_page);

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titles_tab)));
        mTabLayout.setupWithViewPager(mViewPager);
        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mTabLayout.getTabAt(0).setIcon(R.drawable.ativado);
        mTabLayout.getTabAt(1).setIcon(R.drawable.desativado);
        mTabLayout.getTabAt(2).setIcon(R.drawable.desativado);
        mTabLayout.getTabAt(3).setIcon(R.drawable.desativado);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTabLayout.getTabAt(tab.getPosition()).setIcon(R.drawable.ativado);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mTabLayout.getTabAt(tab.getPosition()).setIcon(R.drawable.desativado);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mTabLayout.getTabAt(tab.getPosition()).setIcon(R.drawable.ativado);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatem ent
        if (id == R.id.action_settings) {
            Intent sobre = new Intent(this, SobreActivity.class);
            startActivity(sobre);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
