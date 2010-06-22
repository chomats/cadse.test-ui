/**
 * 
 */
package fr.imag.adele.cadse.test.ui;

import org.codehaus.groovy.runtime.ArrayUtil;

import fr.imag.adele.cadse.util.ArraysUtil;


public class Type {
	Cadse cadse;
	String name;
	Attribute[] attributes;
	Field[] field;
	int extendsType = -1;
	Type supertype = null;
	int superCountAttr = 0;
	boolean isAbstract = false;
	
	public Type(int extendsType, Attribute[] attributes, Field[] field) {
		super();
		this.attributes = attributes;
		this.field = field;
		this.extendsType = extendsType;
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
}