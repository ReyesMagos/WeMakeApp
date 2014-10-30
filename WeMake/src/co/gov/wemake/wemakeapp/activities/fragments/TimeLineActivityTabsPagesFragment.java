package co.gov.wemake.wemakeapp.activities.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TimeLineActivityTabsPagesFragment extends FragmentPagerAdapter {

	public TimeLineActivityTabsPagesFragment(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			return new ColectivosFragment();
		case 1:
			return new InstitucionesFragment();

		}
		return null;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
