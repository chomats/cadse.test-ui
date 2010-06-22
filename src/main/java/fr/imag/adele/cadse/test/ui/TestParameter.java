/**
 * 
 */
package fr.imag.adele.cadse.test.ui;

import java.util.AbstractList;
import java.util.Iterator;

import fr.imag.adele.cadse.core.CadseGCST;

public class TestParameter extends AbstractList<Object[]> {
	int l = 5;
	
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
		switch (index) {
	case 0:
		return e( 	t(
				t0 = new Type(null, a( 
						new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f()),
				t1 = new Type(t0, a(), f())
			),
			c( 
				new Cadse(index, null, t0, t1)
			),
			g(),
			t1
		);
		case 1:
			return e(  t(
				t0 = new Type(null, a( 
						new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), null),
				t1 = new Type(t0, a(), null)),
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
    							new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP),
								new Attribute(CadseGCST.STRING, !HIDDEN, !OVERRIDE, SICP, SIMP) ), f()),
						t1 = new Type(t0, a(), f())
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
    							new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP),
    							new Attribute(CadseGCST.STRING, HIDDEN, !OVERRIDE, SICP, SIMP),
    							new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP),
								new Attribute(CadseGCST.STRING, !HIDDEN, !OVERRIDE, SICP, SIMP) ), f()),
						t1 = new Type(t0, a(), f())
						),
    				c( 
    					c0 = new Cadse(index, null, t0), 
    					c1 = new Cadse(index, c0,  t1)
    				),
    				g(),
    				t1
    					
   			);
		case 4:
			GroupNameUI g0;
			Attribute a0;
			return e( 	t(
					t0= new Type(null, a( 
							a0 = new Attribute(CadseGCST.STRING, HIDDEN, OVERRIDE, SICP, SIMP) ), f()),
					t1= new Type(t0, a(), f())),
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
	
	GroupUI[] g(GroupUI ...g) {
		return g;
	}
	
	Object[] e(Type[] t, Cadse[] c, GroupUI[] g, Type finalT) {
		return new Object[] { t, c, g, finalT  };
	}
	
}