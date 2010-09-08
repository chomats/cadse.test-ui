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
package fr.imag.adele.cadse.test.ui.model;

import java.util.HashMap;
import java.util.Map;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.util.ArraysUtil;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Attribute {

	public KeyValue[] keyValues;
	public Type owner;
	public String name;
	public ItemType typeAttr;
	public boolean hidden;
	public boolean override;
	public boolean sicpKv;
	public boolean simpKv;
	public Page[]	inPage;
	public Page[]	inHiddenPage;
	public Page[]	inReadOnlyPage;
	
	public Attribute(ItemType typeAttr, boolean hidden, boolean override,
			boolean sicpKv, boolean simpKv, KeyValue ...keyValues) {
		super();
		this.typeAttr = typeAttr;
		this.hidden = hidden;
		this.override = override;
		this.sicpKv = sicpKv;
		this.simpKv = simpKv;
		this.keyValues = keyValues;
	}

	
	
	public String getCst() {
		return owner.getCst()+"_at_"+name.toUpperCase()+"_";
	}

	public String getQCst() {
		return owner.cadse.getQCst();
	}

	public void addInPage(Page p) {
		inPage = ArraysUtil.add(Page.class, inPage, p);
	}
	
	public boolean isReadOnly(Type type) {
		boolean result = false;
		if (inReadOnlyPage != null) {
			
		}
		return result;
	}
	
	public boolean isInComputedPage(Type type) {
		return true;
	}

	public void addHiddenInPage(Page p) {
		inHiddenPage = ArraysUtil.add(Page.class, inPage, p);
	}



	public void addReadOnlyInPage(Page p) {
		inReadOnlyPage = ArraysUtil.add(Page.class, inPage, p);
	}
}
