package fr.imag.adele.cadse.test.ui;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.generatorManager.GenerateManager;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;

/**
 * Tests basic properties for boolean attributes.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
@RunWith(Parameterized.class)
public class BasicUI_tc_CADSEg extends GTCadseTestCase {
	static public class Field {
		String label;
		ItemType type;
		String className;
	}

	static public class Attribute {

		public Attribute(ItemType typeAttr, boolean hidden, boolean override,
				boolean sicpKv, boolean simpKv) {
			super();
			this.typeAttr = typeAttr;
			this.hidden = hidden;
			this.override = override;
			this.sicpKv = sicpKv;
			this.simpKv = simpKv;
		}

		String name;
		ItemType typeAttr;
		boolean hidden;
		boolean override;
		boolean sicpKv;
		boolean simpKv;
	}

	static public class Type {
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
	}

	static public class Cadse {
		String name;
		int extendsCadse;
		Cadse refCadse;
		Type[] typesRef;
		int[] types;
		private int i;

		public Cadse(int i, int extendsCadse, int... types) {
			super();
			this.i = i;
			this.extendsCadse = extendsCadse;
			this.types = types;
		}

		GTTreePath cadse_model;
		GTTreePath build_model;
		GTTreePath data_model;
		GTTreePath mapping_model;
		public String projectName;
	}

	
	static public class TestParameter extends AbstractList<Object[]> {
		int l = 3;
		
		@Override
		public Iterator<Object[]> iterator() {
			
			return new Iterator<Object[]>() {
				int current = 0;
				
				@Override
				public boolean hasNext() {
					return current < l;
				}

				@Override
				public Object[] next() {
					return get(current++);
				}

				@Override
				public void remove() {
				}
			};
		}

		@Override
		public int size() {
			return l;
		}

		@Override
		public Object[] get(int index) {
			switch (index) {
		case 0:
			return new Object[] { 	new Type[] {
					new Type(-1, new Attribute[] { 
							new Attribute(CadseGCST.STRING, true, true, true, true) }, null),
					new Type(0, new Attribute[] {}, null
					)},
				new Cadse[] { 
					new Cadse(index, -1, 0, 1)
				},
				1
			};
			case 1:
				return new Object[] {  new Type[] {
					new Type(-1, new Attribute[] { 
							new Attribute(CadseGCST.STRING, true, true, true, true) }, null),
					new Type(0, new Attribute[] {}, null
					)},
				new Cadse[] { 
					new Cadse(index,-1, 0), 
					new Cadse(index, 0, 1)
				},
				1
			};
			case 2:
				return new Object[] 
	    			{ new Type[] {
	    					new Type(-1, new Attribute[] { 
	    							new Attribute(CadseGCST.STRING, true, true, true, true),
									new Attribute(CadseGCST.STRING, false, false, true, true) }, null),
							new Type(0, new Attribute[] {}, null
							)},
	    				new Cadse[] { 
	    					new Cadse(index, -1, 0), 
	    					new Cadse(index, 0,  1)
	    				},
	    				1
	    					
	   			};

			default:
				break;
			}
			return null;
		}
		
	}
	@Parameters
    public static Collection<Object[]> data() {
    	return new TestParameter();
    }

	private Type[] types;
	private Cadse[] cadses;
	private Type finalType;

	public BasicUI_tc_CADSEg(Type[] types, Cadse[] cadses, int refType) {
		super();
		this.cadses = cadses;
		this.types = types;
		this.finalType = types[refType];
	}

	/**
	 * Makes a few things before the test starts.
	 * <ul>
	 * <li>Starts CADSEg</li>
	 * <li>Closes unless views</li>
	 * <li>Creates a new CADSE</li>
	 * </ul>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	static public void init() {
		// Starts CADSEg
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);

		// Closes unless views
		welcomeView.close();
		workspaceView.show();
	}
	
	@Test
	public void main() throws Exception {
		for (int i = 0; i < cadses.length; i++) {
			Cadse c = cadses[i];
			c.name = "CADSE_UI_" +c.i +"_"+ i;
			c.projectName = "Model.Workspace." + c.name;
			if (c.extendsCadse != -1)
				c.refCadse = cadses[c.extendsCadse];
			
			if (c.refCadse == null)
				createCadseDefinition(c.name, "model." + c.name);
			else
				createCadseDefinition(c.name, "model." + c.name, 
						new KeyValue(CadseGCST.CADSE_lt_EXTENDS, c.refCadse.name));
			// TODO extends CAdse

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
			t.name = "Type_" + j;
			if (t.extendsType != -1)
				t.supertype = types[t.extendsType];
			t.superCountAttr = t.supertype == null ? 0 : t.supertype.superCountAttr
					+ t.supertype.attributes.length;
			createType(t);
		}
		 test_item_manager();
	}

	public void test_item_manager() throws Exception {

		Cadse c = finalType.cadse;
		GTCadseHelperMethods.addImportOnManifest(c.projectName,
				"fr.imag.adele.cadse.core.impl.ui",
				"fr.imag.adele.cadse.core.ui",
				"fr.imag.adele.cadse.cadseg.pages.ic",
				"fr.imag.adele.cadse.cadseg.pages.mc",
				"fr.imag.adele.cadse.core.impl.ui.mc",
				"fr.imag.adele.cadse.core.impl.ui.ic",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui");

		workspaceView.findTree().doubleClick(c.data_model.concat(finalType.name));
		GenerateManager manager = new GenerateManager();
		manager.setCadsePackageName( "model." + c.name);
		manager.setClassName(finalType.name);
		manager.setExtendsPart("extends "+finalType.supertype.name+"Manager");
		manager.addImports("fr.imag.adele.cadse.core.CadseGCST",
	"fr.imag.adele.cadse.core.InitAction",
	"fr.imag.adele.cadse.core.Item",
	"fr.imag.adele.cadse.core.ItemType",
	"fr.imag.adele.cadse.core.LinkType",
	"fr.imag.adele.cadse.core.ui.EPosLabel",
	"fr.imag.adele.cadse.core.ui.IPage",
	"fr.imag.adele.cadse.core.ui.RuningInteractionController",
	"fr.imag.adele.cadse.core.ui.UIField",
	"fr.imag.adele.cadse.core.util.CreatedObjectManager",
	"model.CADSE_UI.CADSE_UICST",
	"fr.imag.adele.cadse.core.impl.ui.PageImpl",
	"fr.imag.adele.cadse.core.impl.ui.UIFieldImpl",
	"fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor",
	"fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor",
	"fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform",
	"fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI");
		
		GTTextEditor editor = new GTTextEditor(finalType.name+"Manager.java");
		editor.show();
		editor.selectAll();
		editor.typeText(manager.classPart());
		editor.save();
		
		
		checkError();
		

	}

	private void checkError() throws Exception {
		for (int i = 0; i < cadses.length; i++) {
			Cadse cadse = cadses[i];
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
					cadse.projectName);
			assertNotNull(project);
			IJavaProject jp = JavaCore.create(project);
			assertNotNull(jp);
			GTCadseHelperMethods.checkError(jp, null);
		}
	}

	private void createType(Type t) {
		final GTTreePath it_A_path = t.cadse.data_model.concat(t.name);

		KeyValue superItA = null;
		if (t.supertype != null)
			superItA = new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE,
					t.supertype.cadse.data_model.concat(t.supertype.name));

		// Creates item type with default instance name
		if (superItA == null)
			createItemType(t.cadse.data_model, t.name, notAbstractKv, rootKv);
		else
			createItemType(t.cadse.data_model, t.name, notAbstractKv, rootKv,
					superItA);
		workspaceView.selectNode(it_A_path);
		for (int i = 0; i < t.attributes.length; i++) {
			Attribute attr = t.attributes[i];
			attr.name = "attr" + i;
			int l = (attr.sicpKv ? 1 : 0) + (attr.simpKv ? 1 : 0);
			KeyValue[] kv = new KeyValue[l];
			int j = 0;
			if (attr.sicpKv)
				kv[j++] = sicpKv;
			if (attr.simpKv)
				kv[j++] = simpKv;

			createBasicAttribute(it_A_path, attr.typeAttr, attr.name, kv);
		}
	}

}
