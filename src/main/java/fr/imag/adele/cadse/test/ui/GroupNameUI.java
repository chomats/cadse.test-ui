package fr.imag.adele.cadse.test.ui;


public class GroupNameUI extends GroupUI {

	public GroupNameUI() {
	}
	
	@Override
	String getCST() {
		return "PageInit.ITEM_GROUP_NAME";
	}
	
	@Override
	String getQCst() {
		return "fr.imag.adele.cadse.cadseg.pages.PageInit";
	}
	
	@Override
	boolean generateIt() {
		return false;
	}

}
