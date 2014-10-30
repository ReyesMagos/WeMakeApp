package co.gov.wemake.wemakeapp.activities.fragments;

import co.gov.wemake.wemakeapp.R;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InstitucionesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.instituciones_layout,
				container, false);

		return rootView;
	}

}
