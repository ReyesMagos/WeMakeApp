package co.gov.wemake.wemakeapp.controller;

import co.gov.wemake.wemakeapp.activities.LoginActivity;
import co.gov.wemake.wemakeapp.activities.ProfileActivity;
import co.gov.wemake.wemakeapp.domain.entities.User;
import co.gov.wemake.wemakeapp.factory.FactoryUser;
import android.app.Activity;

public class ProfileController extends AbstractController {

	public ProfileController(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	public void fillProfileInformation() {
		User user = FactoryUser.getInstance().getCurrentUserInActivity();
		if (user == null)// We must return error message
			return;
		ProfileActivity profileActivity = (ProfileActivity) getActivity();
		profileActivity.getTxtName().setText(user.getName());
		profileActivity.getTxtLastname().setText(user.getLastname());
		profileActivity.getTxtEmail().setText(user.getEmail());
		profileActivity.getTxtProfession().setText(user.getProfession());
		profileActivity.getTxtSkills().setText(user.getSkills());
		profileActivity.getTxtCity().setText(user.getCity());

	}
}
