package fr.imag.adele.cadse.test.ui;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { HiddenPageUI_tc_execution.class })
public class HiddenPageUI_ts_execution {
	public static Test suite() {
		return new JUnit4TestAdapter(HiddenPageUI_ts_execution.class);
	}
}
