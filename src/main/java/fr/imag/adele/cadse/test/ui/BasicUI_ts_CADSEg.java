package fr.imag.adele.cadse.test.ui;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { BasicUI_tc_CADSEg.class })
public class BasicUI_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(BasicUI_ts_CADSEg.class);
	}
}