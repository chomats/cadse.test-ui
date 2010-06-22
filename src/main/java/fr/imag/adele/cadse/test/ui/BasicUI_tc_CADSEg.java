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

import java.util.Collection;

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
import fr.imag.adele.cadse.test.generatorManager.GenerateFieldPart;
import fr.imag.adele.cadse.test.generatorManager.GenerateHiddenPage;
import fr.imag.adele.cadse.test.generatorManager.GenerateInnerClass;
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
	
	@Test
	public void main() throws Exception {
		for (int i = 0; i < cadses.length; i++) {
			Cadse c = cadses[i];
			if (c.refCadse == null)
				createCadseDefinition(c.name, c.packageName);
			else
				createCadseDefinition(c.name, c.packageName, 
						new KeyValue(CadseGCST.CADSE_lt_EXTENDS, c.refCadse.name));
		}

		for (int j = 0; j < types.length; j++) {
			Type t = types[j];
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
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui",
				"fr.imag.adele.teamwork.db");

		workspaceView.findTree().doubleClick(c.data_model.concat(finalType.name));
		GenerateManager manager = new GenerateManager();
		manager.setCadsePackageName( "model." + c.name);
		manager.setClassName(finalType.name);
		manager.setExtendsPart("extends "+finalType.supertype.name+"Manager");
		//model.CADSE_UI_1_0.managers.Type_1_0Manager
		if (finalType.cadse != finalType.supertype.cadse) {
			manager.addImports(finalType.supertype.cadse.packageName+".managers."+finalType.supertype.name+"Manager");
			GTCadseHelperMethods.addImportOnManifest(c.projectName,
					"fr.imag.adele.cadse.core.util");
		}
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
	"fr.imag.adele.cadse.core.impl.ui.PageImpl",
	"fr.imag.adele.cadse.core.impl.ui.UIFieldImpl",
	"fr.imag.adele.cadse.core.impl.ui.ic.IC_Descriptor",
	"fr.imag.adele.cadse.core.impl.ui.mc.MC_Descriptor",
	"fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform",
	"fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI");
		
		
		GenerateHiddenPage ghp = new GenerateHiddenPage();
		ghp.setCltHiddenAttributes("hiddenPage");
		ghp.setTypeAttached(finalType.getCst());
		ghp.addImports(finalType.cadse.getQCst());
		
		for (int j = 0; j < types.length; j++) {
			Type t = types[j];
			for (int i = 0; i < t.attributes.length; i++) {
				Attribute attr = t.attributes[i];
				if (attr.hidden) {
					ghp.addHiddenAttribute(attr.getCst());
					ghp.addImports(attr.owner.cadse.getQCst());
				}
				if (attr.override) {
					String clsName = "MyClass_"+attr.name;
					
					GenerateFieldPart gfp = new GenerateFieldPart();
					gfp.setAttachedType(attr.owner.getCst());
					gfp.addImports(attr.owner.cadse.getQCst());
					gfp.setAttr(attr.getCst());
					gfp.setFieldClass(clsName);
					manager.addInitPart(gfp);
					
					GenerateInnerClass gic = new GenerateInnerClass();
					gic.setClassName(clsName );
					manager.addInnerPart(gic);
				}
			}
		}
		
		manager.addInitPart(ghp);
		
		System.out.println("Open manager of " + finalType.getCst());
		GTTextEditor editor = new GTTextEditor(finalType.name+"Manager.java");
		editor.show();
		editor.selectAll();
		editor.typeText(manager.classPart());
		editor.save();
		editor.close();
		
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
