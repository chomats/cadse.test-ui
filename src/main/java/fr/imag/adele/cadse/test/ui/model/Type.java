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

import fr.imag.adele.cadse.util.ArraysUtil;


public class Type {
	public Cadse cadse;
	public String name;
	public Attribute[] attributes;
	public Field[] field;
	public Type supertype = null;
	public int superCountAttr = 0;
	public boolean isAbstract = false;
	public Page[] pages;
	public ExtendedType[] extendedTypes = null;
	
	public Type(Type supertype, Attribute[] attributes, Field[] field, Page[] pages) {
		super();
		this.attributes = attributes == null ? new Attribute[0] : attributes;
		this.field = field == null ? new Field[0] : field;
		this.supertype = supertype;
		this.pages = pages;
	}

	public String getCst() {
		return cadse.getCst()+"."+name.toUpperCase();
	}

	public Attribute[] allAttributes() {
		if (supertype == null)
			return attributes.clone();
		Attribute[] superA = supertype.allAttributes();
		return ArraysUtil.addList(Attribute.class, superA, attributes);
	}

	public String getQCst() {
		return cadse.getQCst();
	}
	
	public void addExtendedType(ExtendedType et) {
		extendedTypes = ArraysUtil.add(ExtendedType.class, extendedTypes, et);
	}
}
