/**
 * 
 */
package fr.imag.adele.cadse.test.ui;

import fr.imag.adele.cadse.util.ArraysUtil;


public class Type {
	Cadse cadse;
	String name;
	Attribute[] attributes;
	Field[] field;
	Type supertype = null;
	int superCountAttr = 0;
	boolean isAbstract = false;
	
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