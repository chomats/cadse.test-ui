package fr.imag.adele.cadse.test.ui.model;

public class GroupUI {
	String cst;
	public String label;
	public boolean hasBox;
	public Attribute[] attributes;
	public GroupUI     overrideG;
	public Type attachedType;
	public int column;
	
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
	
	public boolean generateIt() {
		return true;
	}
	
	public String getCST() {
		return cst;
	}
	
	public String getQCst() {
		return null;
	}
}
