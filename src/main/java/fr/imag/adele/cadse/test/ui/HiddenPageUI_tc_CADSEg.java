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
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.*;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.Collection;
import java.util.Iterator;

import org.codehaus.groovy.runtime.ArrayUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.generatorManager.GenerateFieldPart;
import fr.imag.adele.cadse.test.generatorManager.GenerateGroupPart;
import fr.imag.adele.cadse.test.generatorManager.GeneratePage;
import fr.imag.adele.cadse.test.generatorManager.GenerateInnerClass;
import fr.imag.adele.cadse.test.generatorManager.GenerateManager;
import fr.imag.adele.cadse.test.ui.model.Attribute;
import fr.imag.adele.cadse.test.ui.model.Cadse;
import fr.imag.adele.cadse.test.ui.model.ExtendedType;
import fr.imag.adele.cadse.test.ui.model.GroupUI;
import fr.imag.adele.cadse.test.ui.model.Page;
import fr.imag.adele.cadse.test.ui.model.Type;
import fr.imag.adele.cadse.util.ArraysUtil;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;

/**
 * Tests basic properties for boolean attributes.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
@RunWith(Parameterized.class)
public class HiddenPageUI_tc_CADSEg extends HiddenPageUI_abstract_tc {
	
	public HiddenPageUI_tc_CADSEg(Type[] types, Cadse[] cadses, GroupUI[] groupUi, Type refType) {
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
	
	@Test
	public void main() throws Exception {
		for (int i = 0; i < cadses.length; i++) {
			Cadse c = cadses[i];
			if (c.refCadse == null)
				createCadseDefinition(c.name, c.packageName);
			else
				createCadseDefinition(c.name, c.packageName, 
						new KeyValue(CadseGCST.CADSE_lt_EXTENDS, c.refCadse.name));
		}

		for (int j = 0; j < types.length; j++) {
			Type t = types[j];
			createType(t);
		}
		test_item_manager();
	}

	public void test_item_manager() throws Exception {

		Cadse c = finalType.cadse;
		GTCadseHelperMethods.addImportOnManifest(c.projectName,
				"fr.imag.adele.cadse.core.impl.ui",
				"fr.imag.adele.cadse.core.ui",
				"fr.imag.adele.cadse.cadseg.pages.ic",
				"fr.imag.adele.cadse.cadseg.pages.mc",
				"fr.imag.adele.cadse.core.impl.ui.mc",
				"fr.imag.adele.cadse.core.impl.ui.ic",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui",
				"fr.imag.adele.teamwork.db",
				"fr.imag.adele.cadse.cadseg.pages");

		workspaceView.findTree().doubleClick(c.data_model.concat(finalType.name));
		GenerateManager manager = new GenerateManager();
		manager.setCadsePackageName( "model." + c.name);
		manager.setClassName(finalType.name);
		manager.setExtendsPart("extends "+finalType.supertype.name+"Manager");
		//model.CADSE_UI_1_0.managers.Type_1_0Manager
		if (finalType.cadse != finalType.supertype.cadse) {
			manager.addImports(finalType.supertype.cadse.packageName+".managers."+finalType.supertype.name+"Manager");
			GTCadseHelperMethods.addImportOnManifest(c.projectName,
					"fr.imag.adele.cadse.core.util");
		}
		manager.addImports("fr.imag.adele.cadse.core.CadseGCST",
	"fr.imag.adele.cadse.core.InitAction",
	"fr.imag.adele.cadse.core.Item",
	"fr.imag.adele.cadse.core.ItemType",
	"fr.imag.adele.cadse.core.LinkType",
	"fr.imag.adele.cadse.core.ui.EPosLabel",
	"fr.imag.adele.cadse.core.ui.IPage",
	"fr.imag.adele.cadse.core.ui.RuningInteractionController",
	"fr.imag.adele.cadse.core.ui.UIField",
	"fr.imag.adele.cadse.core.util.CreatedObjectManager",
	"fr.imag.adele.cadse.core.impl.ui.PageImpl",
	"fr.imag.adele.cadse.core.impl.ui.UIFieldImpl",
	"fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor",
	"fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor",
	"fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform",
	"fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI");
		
		
		GeneratePage ghp = new GeneratePage();
		ghp.setPageVar("hiddenPage");
		ghp.setTypeAttached(finalType.getCst());
		ghp.addImports(finalType.cadse.getQCst());
		
		for (int j = 0; j < types.length; j++) {
			Type t = types[j];
			for (int i = 0; i < t.attributes.length; i++) {
				Attribute attr = t.attributes[i];
				if (attr.hidden) {
					ghp.addHiddenAttribute(attr.getCst());
					ghp.addImports(attr.owner.cadse.getQCst());
				}
				if (attr.override) {
					String clsName = "MyClass_"+attr.name;
					
					GenerateFieldPart gfp = new GenerateFieldPart();
					gfp.setAttachedType(attr.owner.getCst());
					gfp.addImports(attr.owner.cadse.getQCst());
					gfp.setAttr(attr.getCst());
					gfp.setFieldClass(clsName);
					manager.addInitPart(gfp);
					
					GenerateInnerClass gic = new GenerateInnerClass();
					gic.setClassName(clsName );
					manager.addInnerPart(gic);
				}
			}
			for (int i = 0; i < t.pages.length; i++) {
				manager.addInitPart(
						configure(
								new GeneratePage(), t, t.pages[i]));
			}
		}
		
		for (int i = 0; i < groupUI.length; i++) {
			GroupUI g = groupUI[i];
			if (!g.generateIt()) continue;
			
			
			GenerateGroupPart ggp = new GenerateGroupPart();
			String[] attributesCst = new String[g.attributes.length];
			for (int j = 0; j < attributesCst.length; j++) {
				attributesCst[j] = g.attributes[j].getCst();
				ggp.addImports(g.attributes[j].getQCst());
			}
			
			ggp.setAttributeCST(attributesCst);
			ggp.setColumn(Integer.toString(g.column));
			ggp.setDescription(g.label);
			ggp.setHasBox(Boolean.toString(g.hasBox));
			ggp.setLabel(g.label);
			if (g.overrideG != null) {
				ggp.setOverrideGroup(g.overrideG.getCST());
				if (g.overrideG.getQCst() != null)
					ggp.addImports(g.overrideG.getQCst());
			}
			ggp.setAttachedType(g.attachedType.getCst());
			ggp.addImports(g.attachedType.getQCst());
			
			manager.addInitPart(ggp);
		}
		
		manager.addInitPart(ghp);
		
		System.out.println("Open manager of " + finalType.getCst());
		GTTextEditor editor = new GTTextEditor(finalType.name+"Manager.java");
		editor.show();
		editor.selectAll();
		editor.typeText(manager.classPart());
		editor.save();
		editor.close();
		
		checkError();
	}



	private GeneratePage configure(GeneratePage ghp_i, Type t, Page p) {
		ghp_i.setPageVar("page");
		ghp_i.setTypeAttached(t.getCst());
		ghp_i.addImports(t.cadse.getQCst());
		ghp_i.setLabel(p.name);
		
		for (Attribute a : p.attributes) {
			ghp_i.addAttribute(a.getCst());
			ghp_i.addImports(a.owner.cadse.getQCst());
		}
		
		for (Attribute a : p.hidden) {
			ghp_i.addHiddenAttribute(a.getCst());
			ghp_i.addImports(a.owner.cadse.getQCst());
		}
		
		for (Attribute a : p.readonly) {
			ghp_i.addReadOnlyAttributes(a.getCst());
			ghp_i.addImports(a.owner.cadse.getQCst());
		}
		return ghp_i;
	}

	private void checkError() throws Exception {
		for (int i = 0; i < cadses.length; i++) {
			Cadse cadse = cadses[i];
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
					cadse.projectName);
			assertNotNull(project);
			IJavaProject jp = JavaCore.create(project);
			assertNotNull(jp);
			GTCadseHelperMethods.checkError(jp, null);
		}
	}

	private void createType(Type t) throws Exception {
		
		KeyValue superItA = null;
		if (t.supertype != null)
			superItA = new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE,
					t.supertype.cadse.data_model.concat(t.supertype.name));

		if (t instanceof ExtendedType) {
			createExtendedType(t.cadse.data_model, t.name);
		} else {
			// Creates item type with def ault instance name
			if (superItA == null)
				createItemType(t.cadse.data_model, t.name, notAbstractKv(), rootKv());
			else
				createItemType(t.cadse.data_model, t.name, notAbstractKv(), rootKv(),
						superItA);
		}
		
		final GTTreePath it_A_path = t.cadse.data_model.concat(t.name);

		workspaceView.selectNode(it_A_path);
		if (t instanceof ExtendedType) {
			ExtendedType et = (ExtendedType) t;
			propertiesView.showTab(t.name);
			for (int i = 0; i < et.typesExtended.length; i++) {
				Type bindingType = et.typesExtended[i];
				GTTreePath bindingType_path = bindingType.cadse.data_model.concat(bindingType.name);
				propertiesView.setValue(new KeyValue(CadseGCST.EXT_ITEM_TYPE_lt_REF_TYPE, bindingType_path));
			}
		}
		for (int i = 0; i < t.attributes.length; i++) {
			Attribute attr = t.attributes[i];
			KeyValue[] kv = attr.keyValues.clone();
			
			if (attr.sicpKv)
				kv = ArraysUtil.add(KeyValue.class, kv, sicpKv());
			if (attr.simpKv)
				kv = ArraysUtil.add(KeyValue.class, kv, simpKv());

			createBasicAttribute(it_A_path, attr.typeAttr, attr.name, kv);
		}
	}

}
