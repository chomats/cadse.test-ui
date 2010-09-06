package fr.imag.adele.cadse.test.ui.model;


public class GroupNameUI extends GroupUI {

	public GroupNameUI() {
	}
	
	@Override
	public String getCST() {
		return "PageInit.ITEM_GROUP_NAME";
	}
	
	@Override
	public String getQCst() {
		return "fr.imag.adele.cadse.cadseg.pages.PageInit";
	}
	
	@Override
	public boolean generateIt() {
		return false;
	}

}
