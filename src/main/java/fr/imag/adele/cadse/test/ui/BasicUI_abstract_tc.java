package fr.imag.adele.cadse.test.ui;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

abstract public class BasicUI_abstract_tc extends GTCadseTestCase {

	protected Type[] types;
	protected Cadse[] cadses;
	protected Type finalType;

	public BasicUI_abstract_tc(Type[] types, Cadse[] cadses, int refType) {
		super();
		this.cadses = cadses;
		this.types = types;
		this.finalType = types[refType];
		initParam();
	}
	
	public void initParam() {
		for (int i = 0; i < cadses.length; i++) {
			Cadse c = cadses[i];
			c.name = "CADSE_UI_" +c.i +"_"+ i;
			c.projectName = "Model.Workspace." + c.name;
			if (c.extendsCadse != -1)
				c.refCadse = cadses[c.extendsCadse];
			
			c.packageName = "model." + c.name;
			c.cadse_model = new GTTreePath(c.name);
			c.build_model = c.cadse_model
					.concat(CadseDefinitionManager.BUILD_MODEL);
			c.data_model = c.cadse_model
					.concat(CadseDefinitionManager.DATA_MODEL);
			c.mapping_model = c.cadse_model
					.concat(CadseDefinitionManager.MAPPING);

			c.typesRef = new Type[c.types.length];
			for (int j = 0; j < c.types.length; j++) {
				c.typesRef[j] = types[c.types[j]];
				c.typesRef[j].cadse = c;
			}
		}

		for (int j = 0; j < types.length; j++) {
			Type t = types[j];
			t.name = "Type_" +t.cadse.i+ "_"+ j;
			if (t.extendsType != -1)
				t.supertype = types[t.extendsType];
			t.superCountAttr = t.supertype == null ? 0 : t.supertype.superCountAttr
					+ t.supertype.attributes.length;
			for (int i = 0; i < t.attributes.length; i++) {
				Attribute attr = t.attributes[i];
				attr.owner = t;
				attr.name = "attr" + t.superCountAttr+i;
			}
		}
	}
}
