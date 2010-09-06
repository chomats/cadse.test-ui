/**
 * 
 */
package fr.imag.adele.cadse.test.ui.model;

import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Cadse {
	public String name;
	public String packageName;
	public Cadse refCadse;
	public Type[] typesRef;
	public int i;
	public GTTreePath cadse_model;
	public GTTreePath build_model;
	public GTTreePath data_model;
	public GTTreePath mapping_model;
	public String projectName;
	
	public Cadse(int i, Cadse extendsCadse, Type... types) {
		super();
		this.i = i;
		this.refCadse = extendsCadse;
		this.typesRef = types;
	}

	public String getQCst() {
		return packageName+"."+getCst();
	}
	public String getCst() {
		return name+"CST";
	}

	
}