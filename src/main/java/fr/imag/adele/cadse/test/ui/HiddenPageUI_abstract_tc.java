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

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.test.ui.model.Attribute;
import fr.imag.adele.cadse.test.ui.model.Cadse;
import fr.imag.adele.cadse.test.ui.model.ExtendedType;
import fr.imag.adele.cadse.test.ui.model.GroupUI;
import fr.imag.adele.cadse.test.ui.model.Page;
import fr.imag.adele.cadse.test.ui.model.Type;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

abstract public class HiddenPageUI_abstract_tc extends GTCadseTestCase {

	protected Type[] types;
	protected Cadse[] cadses;
	protected Type finalType;
	protected GroupUI[] groupUI;

	protected HiddenPageUI_abstract_tc(Type[] types, Cadse[] cadses, GroupUI[] groupUi, Type refType) {
		super();
		this.cadses = cadses;
		this.types = types;
		this.groupUI = groupUi;
		this.finalType = refType;
		initParam();
	}
	
	public void initParam() {
		for (int i = 0; i < cadses.length; i++) {
			Cadse c = cadses[i];
			c.name = "CADSE_UI_" +c.i +"_"+ i;
			c.projectName = "Model.Workspace." + c.name;
			
			c.packageName = "model." + c.name;
			c.cadse_model = new GTTreePath(c.name);
			c.build_model = c.cadse_model
					.concat(CadseDefinitionManager.BUILD_MODEL);
			c.data_model = c.cadse_model
					.concat(CadseDefinitionManager.DATA_MODEL);
			c.mapping_model = c.cadse_model
					.concat(CadseDefinitionManager.MAPPING);

			for (int j = 0; j < c.typesRef.length; j++) {
				c.typesRef[j].cadse = c;
			}
		}

		for (int j = 0; j < types.length; j++) {
			Type t = types[j];
			t.name = "Type_" +t.cadse.i+ "_"+ j;
			t.superCountAttr = t.supertype == null ? 0 : t.supertype.superCountAttr
					+ t.supertype.attributes.length;
			for (int i = 0; i < t.attributes.length; i++) {
				Attribute attr = t.attributes[i];
				attr.owner = t;
				attr.name = "attr" + t.superCountAttr+i;
			}
			if (t instanceof ExtendedType) {
				ExtendedType et = (ExtendedType) t;
				for (Type t_by_et : et.typesExtended) {
					t_by_et.addExtendedType(et);
				}
			}
			Page[] pages = t.pages;
			for (Page p : pages) {
				for (Attribute a : p.attributes) {
					a.addInPage(p);
				}
				for (Attribute a : p.hidden) {
					a.addHiddenInPage(p);
				}
				for (Attribute a : p.attributes) {
					a.addReadOnlyInPage(p);
				}
			}
		}
		
		for (int i = 0; i < groupUI.length; i++) {
			
		}
		
		
	}
}
