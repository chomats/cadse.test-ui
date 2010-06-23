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