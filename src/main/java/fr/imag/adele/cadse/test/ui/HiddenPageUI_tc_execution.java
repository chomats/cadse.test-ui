/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.test.ui;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.*;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.ui.model.Attribute;
import fr.imag.adele.cadse.test.ui.model.Cadse;
import fr.imag.adele.cadse.test.ui.model.GroupUI;
import fr.imag.adele.cadse.test.ui.model.Type;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

/**
 */
@RunWith(Parameterized.class)
public class HiddenPageUI_tc_execution extends HiddenPageUI_abstract_tc {
	
	public HiddenPageUI_tc_execution(Type[] types, Cadse[] cadses, GroupUI[] groupUi, Type refType) {
		super(types, cadses, groupUi, refType);
	}

	@Parameters
    public static Collection<Object[]> data() {
    	return new TestParameter();
    }
	
    /**
	 * Makes a few things before the test starts.
	 * <ul>
	 * <li>Starts CADSEg</li>
	 * <li>Closes unless views</li>
	 * <li>Creates a new CADSE</li>
	 * </ul>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	static public void init() {
		// Starts CADSEg
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);

		// Closes unless views
		welcomeView.close();
		workspaceView.show();
	}


	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void main() throws Exception {
		String[] selCadse = new String[cadses.length];
		for (int i = 0; i < cadses.length; i++) {
			Cadse c = cadses[i];
			selCadse[i] = "Cadse Model.Workspace." + c.name;
		}
		selectCadsesFromWorkspaceView(selCadse);
		
		for (int i = 0; i < types.length ; i++) {
			Type t = types[i];
			if (t.isAbstract) continue;
			Attribute[] attributes = t.allAttributes();
			
			workspaceView.contextMenuNew(t.name).click();
			GTCadseShell shell = new GTCadseShell(t.name);
			final String instanceName = getInstanceName(t);
			shell.findCadseFieldName().typeText(instanceName);
			for (Attribute att : attributes) {
				try {
					shell.findCadseField(att.name);
					if (!att.sicpKv || ((finalType == t) && att.hidden))
							fail("Attribute "+att.name+" not hidden.");
				} catch (Exception e) {
					if (att.sicpKv && ((finalType != t) || !att.hidden))
						fail("Attribute "+att.name+" hidden.");
				}
			}
			shell.close();
			
			workspaceView.selectNode(instanceName);
			propertiesView.showTab(t.name);
			for (Attribute att : attributes) {
				try {
					propertiesView.findCadseField(att.name);
					if (!att.simpKv || ((finalType == t) && att.hidden))
							fail("Attribute "+att.name+" not hidden.");
				} catch (Exception e) {
					if (att.simpKv && ((finalType != t) || !att.hidden))
						fail("Attribute "+att.name+" hidden.");
				}
			}
		}
	}

	private String getInstanceName(Type t) {
		return "instance_"+t.name;
	}

}
