/**
 * 
 */
package fr.imag.adele.cadse.test.ui;

import fr.imag.adele.cadse.core.ItemType;

public class Attribute {

	public Attribute(ItemType typeAttr, boolean hidden, boolean override,
			boolean sicpKv, boolean simpKv) {
		super();
		this.typeAttr = typeAttr;
		this.hidden = hidden;
		this.override = override;
		this.sicpKv = sicpKv;
		this.simpKv = simpKv;
	}

	Type owner;
	String name;
	ItemType typeAttr;
	boolean hidden;
	boolean override;
	boolean sicpKv;
	boolean simpKv;
	
	public String getCst() {
		return owner.getCst()+"_at_"+name.toUpperCase()+"_";
	}

	public String getQCst() {
		return owner.cadse.getQCst();
	}
}