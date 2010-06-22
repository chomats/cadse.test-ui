package fr.imag.adele.cadse.test.ui;

public class GroupUI {
	String cst;
	String label;
	boolean hasBox;
	Attribute[] attributes;
	GroupUI     overrideG;
	Type attachedType;
	int column;
	
	/**
	 * @param label the label or null
	 * @param hasBox has a box ?
	 * @param attributes (int type, int attribut)
	 */
	public GroupUI(GroupUI overrideG, Type attachedType, String label, int column, boolean hasBox, Attribute... attributes) {
		this.label = label;
		this.hasBox = hasBox;
		this.attributes = attributes;
		this.overrideG = overrideG;
		this.attachedType = attachedType;
		this.column = column;
	}
	
	public GroupUI() {
		
	}
	
	boolean generateIt() {
		return true;
	}
	
	String getCST() {
		return cst;
	}
	
	String getQCst() {
		return null;
	}
}
