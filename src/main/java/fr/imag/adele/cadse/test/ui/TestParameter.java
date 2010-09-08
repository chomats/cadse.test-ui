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

import java.util.AbstractList;
import java.util.Iterator;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.ui.model.Attribute;
import fr.imag.adele.cadse.test.ui.model.AttributeRef;
import fr.imag.adele.cadse.test.ui.model.Cadse;
import fr.imag.adele.cadse.test.ui.model.ExtendedType;
import fr.imag.adele.cadse.test.ui.model.Field;
import fr.imag.adele.cadse.test.ui.model.GroupNameUI;
import fr.imag.adele.cadse.test.ui.model.GroupUI;
import fr.imag.adele.cadse.test.ui.model.Page;
import fr.imag.adele.cadse.test.ui.model.Type;

public class TestParameter extends AbstractList<Object[]> {
	int l = 11;
	
	@Override
	public Iterator<Object[]> iterator() {
		
		return new Iterator<Object[]>() {
			int current = 0;
			
			@Override
			public boolean hasNext() {
				return current < l;
			}

			@Override
			public Object[] next() {
				return get(current++);
			}

			@Override
			public void remove() {
			}
		};
	}

	@Override
	public int size() {
		return l;
	}
	
	public static final boolean HIDDEN = true;
	public static final boolean OVERRIDE = true;
	public static final boolean SICP = true;
	public static final boolean SIMP = true;
	

	@Override
	public Object[] get(int index) {
		Cadse c0;
		Cadse c1;
		Type t0;
		Type t1;
		Type t2;
		Type t3;
		Type t4;
		GroupNameUI g0;
		Attribute a0;
		Attribute a1;
		Attribute a2;
		Attribute a3;
		
		
		switch (index) {
	case 0:
		/**
		 * Creer deux type t0 et t1 -> t0 dans un meme cadse.
		 * t0 a un attribut qui est cacher dans une page de t1
		 */
		return e( 	t(
				t0 = new Type(null, a( 
						a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
				t1 = new Type(t0, a(), f(), p(
						new Page("hidden", p(), a(), a(a0), a())
				))
			),
			c( 
				new Cadse(index, null, t0, t1)
			),
			g( ),
			t1
		);
		case 1:
			return e(  t(
				t0 = new Type(null, a( 
						a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
				t1 = new Type(t0, a(), f(), p(
						new Page("hidden", p(), a(), a(a0), a())))),
			c( 
				c0 = new Cadse(index,null, t0), 
				new Cadse(index, c0, t1)
			), g(),
			t1
		);
		case 2:
			return e( 
    			 	t(
    					t0 = new Type(null, a( 
    							a0=new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP),
								new Attribute(CadseGCST.STRING, !HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
						t1 = new Type(t0, a(), f(), p(
								new Page("hidden", p(), a(), a(a0), a())))
						),
    				c( 
    					c0 = new Cadse(index, null, t0), 
    					new Cadse(index, c0,  t1)
    				), 
    				g(),
    				t1
    					
   			);
		case 3:
			return e(
					t(
    					t0 = new Type(null, a( 
    							a0= new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP),
    							a1= new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP),
    							a2= new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP),
								new Attribute(CadseGCST.STRING, !HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
						t1 = new Type(t0, a(), f(), p(
								new Page("hidden", p(), a(), a(a0,a1,a2), a())))
						),
    				c( 
    					c0 = new Cadse(index, null, t0), 
    					c1 = new Cadse(index, c0,  t1)
    				),
    				g(),
    				t1
    					
   			);
		case 4:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a(), f(), p(new Page("hidden", p(), a(), a(a0), a())))),
				c( 
					new Cadse(index, null, t0, t1)
				),
				g (
					g0 = new GroupNameUI(),
					new GroupUI(g0, t0, "name2", 1,  true, 
							a0, new AttributeRef("CadseGCST.ITEM_at_NAME_", 
									"fr.imag.adele.cadse.core.CadseGCST"))
				),
				t1
			);
		case 5:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
					t2= new Type(t1, a(), f(), p())),
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2)
				),
				g (
					g0 = new GroupNameUI(),
					new GroupUI(g0, t0, "name2", 1,  true, 
							a0, a1, new AttributeRef("CadseGCST.ITEM_at_NAME_", 
									"fr.imag.adele.cadse.core.CadseGCST"))
				),
				t2
			);
		case 6:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
					t2= new Type(t1, a(), f(), p())),
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2)
				),
				g (
					g0 = new GroupNameUI(),
					new GroupUI(g0, t0, "name2", 1,  true, 
							a0, a1, new AttributeRef("CadseGCST.ITEM_at_NAME_", 
									"fr.imag.adele.cadse.core.CadseGCST"))
				),
				t2
			);
		case 7:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, !HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t2= new Type(t1, a(), f(), p())),
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2)
				),
				g (
					g0 = new GroupNameUI(),
					new GroupUI(g0, t0, "name2", 1,  true, 
							a0, a1, new AttributeRef("CadseGCST.ITEM_at_NAME_", 
									"fr.imag.adele.cadse.core.CadseGCST"))
				),
				t2
			);
		case 8:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
					t2= new Type(t1, a(), f(), p())),
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2)
				),
				g (),
				t2
			);
		case 9:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP) ), f(), p()),
					t2= new Type(t1, a(), f(), p())),
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2)
				),
				g (),
				t2
			);
		case 10:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, !HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t2= new Type(t1, a(), f(), p())),
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2)
				),
				g (),
				t2
			);
		case 11:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, !HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t2= new Type(t1, a(), f(), p()),
					t4= new ExtendedType(a( 
							a3 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP)), f(), p(), t(t0))),
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2, t4)
				),
				g (),
				t2
			);
		case 12:
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t1= new Type(t0, a( 
							a1 = new Attribute(CadseGCST.STRING, !HIDDEN, OVERRIDE, SICP, SIMP) ), f(), p()),
					t4= new ExtendedType(a( 
							a3 = new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP)), f(), p(), t(t0)),
					t2= new Type(t1, a(), f(), p(
							new Page("p1",p(), a(a3), a(),a())))),
							
				c( 
					c0 = new Cadse(index, null, t0, t1),
					new Cadse(index, c0, t2, t4)
				),
				g (),
				t2
			);
		default:
			break;
		}
		return null;
	}
	
	Attribute[] a(Attribute ...a) {
		return a;
	}
	
	Field[] f(Field ...f) {
		return f;
	}
	
	Cadse[] c(Cadse ...c) {
		return c;
	}
	
	Type[] t(Type ...t) {
		return t;
	}
	
	Page[] p(Page ...p) {
		return p;
	}
	
	GroupUI[] g(GroupUI ...g) {
		return g;
	}
	
	Object[] e(Type[] t, Cadse[] c, GroupUI[] g, Type finalT) {
		return new Object[] { t, c, g, finalT  };
	}
	
}
