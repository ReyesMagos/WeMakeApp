package co.gov.wemake.wemakeapp.security.test;

import android.test.AndroidTestCase;
import co.gov.wemake.wemakeapp.security.EncryptUtils;


public class EncryptUtilsTest extends AndroidTestCase {
	public void stringToSha1Test() {
		String password = "oscardx";
		String result = EncryptUtils.stringToSha1(password);
		if (result != null) {
			System.out.println(result);
		}
	}
}
