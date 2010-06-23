/**
 * 
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
	
	public Type(Type supertype, Attribute[] attributes, Field[] field) {
		super();
		this.attributes = attributes == null ? new Attribute[0] : attributes;
		this.field = field == null ? new Field[0] : field;
		this.supertype = supertype;
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
}