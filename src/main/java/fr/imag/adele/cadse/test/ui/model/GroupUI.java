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

public class GroupUI {
	String cst;
	public String label;
	public boolean hasBox;
	public Attribute[] attributes;
	public GroupUI     overrideG;
	public Type attachedType;
	public int column;
	
	/**
	 * @param label the label or null
	 * @param hasBox has a box ?
	 * @param attributes (int type, int attribut)
	 */
	public GroupUI(GroupUI overrideG, Type attachedType, String label, int column, boolean hasBox, Attribute... attributes) {
		this.label = label;
		this.hasBox = hasBox;
		this.attributes = attributes;
		this.overrideG = overrideG;
		this.attachedType = attachedType;
		this.column = column;
	}
	
	public GroupUI() {
		
	}
	
	public boolean generateIt() {
		return true;
	}
	
	public String getCST() {
		return cst;
	}
	
	public String getQCst() {
		return null;
	}
}
