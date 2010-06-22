/**
 * 
 */
package fr.imag.adele.cadse.test.ui;


public class Type {
	Cadse cadse;
	String name;
	Attribute[] attributes;
	Field[] field;
	int extendsType = -1;
	Type supertype = null;
	int superCountAttr = 0;

	public Type(int extendsType, Attribute[] attributes, Field[] field) {
		super();
		this.attributes = attributes;
		this.field = field;
		this.extendsType = extendsType;
	}

	public String getCst() {
		return cadse.getCst()+"."+name.toUpperCase();
	}
}