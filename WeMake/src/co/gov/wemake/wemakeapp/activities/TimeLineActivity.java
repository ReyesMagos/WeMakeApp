package co.gov.wemake.wemakeapp.activities;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.R.id;
import co.gov.wemake.wemakeapp.R.layout;
import co.gov.wemake.wemakeapp.R.menu;
import co.gov.wemake.wemakeapp.activities.fragments.TimeLineActivityTabsPagesFragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class TimeLineActivity extends FragmentActivity implements TabListener {

	private ActionBar action;
	private ViewPager pager;
	private TimeLineActivityTabsPagesFragment tabsAdapter;
	private String[] tabsNames = { "Colectivos", "Instituciones" }; // it's must
																	// be load
																	// dinamycally
																	// from
																	// res/values
																	// file

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		action = getActionBar();
		pager = (ViewPager) findViewById(R.id.pager);
		tabsAdapter = new TimeLineActivityTabsPagesFragment(
				getSupportFragmentManager());

		pager.setAdapter(tabsAdapter);
		action.setHomeButtonEnabled(false);
		action.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (String tab_name : tabsNames) {
			action.addTab(action.newTab().setText(tab_name)
					.setTabListener(this));
			
			
		}
		
		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			 
		    @Override
		    public void onPageSelected(int position) {
		        // on changing the page
		        // make respected tab selected
		        action.setSelectedNavigationItem(position);
		    }
		 
		    @Override
		    public void onPageScrolled(int arg0, float arg1, int arg2) {
		    }
		 
		    @Override
		    public void onPageScrollStateChanged(int arg0) {
		    }
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		pager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
