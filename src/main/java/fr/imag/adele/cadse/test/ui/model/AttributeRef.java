package fr.imag.adele.cadse.test.ui.model;


public class AttributeRef  extends Attribute {

	private String cst;
	private String qCst;

	public AttributeRef(String cst, String qCst) {
		super(null, false, false, false, false);
		this.cst = cst;
		this.qCst = qCst;
	}
	
	@Override
	public String getCst() {
		return cst;
	}
	
	@Override
	public String getQCst() {
		return qCst;
	}

}
