package co.gov.wemake.wemakeapp.activities.test;

import android.app.Activity;
import android.test.AndroidTestCase;
import co.gov.wemake.wemakeapp.controller.RegisterController;
import junit.framework.TestCase;

public class RegisterControllerTest extends AndroidTestCase {

	public void testVerifyName() {
		Activity a = new Activity();
		String testString = "oscar";
		RegisterController registerController = new RegisterController(a);
		assertTrue(registerController.verifyString2(3, 0, testString));

	}

	public void testVerifyEmail() {
		Activity a = new Activity();
		String testString = "os-car_6gallon@gmail.com";
		RegisterController registerController = new RegisterController(a);
		assertTrue(registerController.verifyEmail(testString));
	}

	public void testVerifyAge() {
		Activity a = new Activity();
		String testString = "18";
		RegisterController registerController = new RegisterController(a);
		assertTrue(registerController.verifyAge(testString));
	}

}
