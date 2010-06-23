/**
 * 
 */
package fr.imag.adele.cadse.test.ui.model;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Attribute {

	public KeyValue[] keyValues;
	public Type owner;
	public String name;
	public ItemType typeAttr;
	public boolean hidden;
	public boolean override;
	public boolean sicpKv;
	public boolean simpKv;
	
	public Attribute(ItemType typeAttr, boolean hidden, boolean override,
			boolean sicpKv, boolean simpKv, KeyValue ...keyValues) {
		super();
		this.typeAttr = typeAttr;
		this.hidden = hidden;
		this.override = override;
		this.sicpKv = sicpKv;
		this.simpKv = simpKv;
		this.keyValues = keyValues;
	}

	
	
	public String getCst() {
		return owner.getCst()+"_at_"+name.toUpperCase()+"_";
	}

	public String getQCst() {
		return owner.cadse.getQCst();
	}
}