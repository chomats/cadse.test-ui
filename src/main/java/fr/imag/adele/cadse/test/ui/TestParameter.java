/**
 * 
 */
package fr.imag.adele.cadse.test.ui;

import java.util.AbstractList;
import java.util.Iterator;

import fr.imag.adele.cadse.core.CadseGCST;

public class TestParameter extends AbstractList<Object[]> {
	int l = 3;
	
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

	@Override
	public Object[] get(int index) {
		switch (index) {
	case 0:
		return new Object[] { 	new Type[] {
				new Type(-1, new Attribute[] { 
						new Attribute(CadseGCST.STRING, true, true, true, true) }, null),
				new Type(0, new Attribute[] {}, null
				)},
			new Cadse[] { 
				new Cadse(index, -1, 0, 1)
			},
			1
		};
		case 1:
			return new Object[] {  new Type[] {
				new Type(-1, new Attribute[] { 
						new Attribute(CadseGCST.STRING, true, true, true, true) }, null),
				new Type(0, new Attribute[] {}, null
				)},
			new Cadse[] { 
				new Cadse(index,-1, 0), 
				new Cadse(index, 0, 1)
			},
			1
		};
		case 2:
			return new Object[] 
    			{ new Type[] {
    					new Type(-1, new Attribute[] { 
    							new Attribute(CadseGCST.STRING, true, true, true, true),
								new Attribute(CadseGCST.STRING, false, false, true, true) }, null),
						new Type(0, new Attribute[] {}, null
						)},
    				new Cadse[] { 
    					new Cadse(index, -1, 0), 
    					new Cadse(index, 0,  1)
    				},
    				1
    					
   			};

		default:
			break;
		}
		return null;
	}
	
}