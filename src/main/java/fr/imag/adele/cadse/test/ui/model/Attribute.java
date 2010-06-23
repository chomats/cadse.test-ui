/**
 * 
 */
package fr.imag.adele.cadse.test.ui.model;

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

	public Type owner;
	public String name;
	public ItemType typeAttr;
	public boolean hidden;
	public boolean override;
	public boolean sicpKv;
	public boolean simpKv;
	
	public String getCst() {
		return owner.getCst()+"_at_"+name.toUpperCase()+"_";
	}

	public String getQCst() {
		return owner.cadse.getQCst();
	}
}