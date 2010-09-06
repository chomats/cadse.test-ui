/**
 * 
 */
package fr.imag.adele.cadse.test.ui.model;

import java.util.HashMap;
import java.util.Map;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.util.ArraysUtil;
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
	public Page[]	inPage;
	public Page[]	inHiddenPage;
	public Page[]	inReadOnlyPage;
	
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

	public void addInPage(Page p) {
		inPage = ArraysUtil.add(Page.class, inPage, p);
	}
	
	public boolean isReadOnly(Type type) {
		boolean result = false;
		if (inReadOnlyPage != null) {
			
		}
		return result;
	}
	
	public boolean isInComputedPage(Type type) {
		return true;
	}

	public void addHiddenInPage(Page p) {
		inHiddenPage = ArraysUtil.add(Page.class, inPage, p);
	}



	public void addReadOnlyInPage(Page p) {
		inReadOnlyPage = ArraysUtil.add(Page.class, inPage, p);
	}
}