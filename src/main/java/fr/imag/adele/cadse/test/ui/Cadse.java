/**
 * 
 */
package fr.imag.adele.cadse.test.ui;

import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Cadse {
	String name;
	String packageName;
	Cadse refCadse;
	Type[] typesRef;
	int i;

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

	GTTreePath cadse_model;
	GTTreePath build_model;
	GTTreePath data_model;
	GTTreePath mapping_model;
	public String projectName;
}