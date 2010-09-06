package fr.imag.adele.cadse.test.ui.model;

public class Page {

	public String name;
	public Page[] overridePages;
	public Attribute[] attributes;
	public Attribute[] hidden;
	public Attribute[] readonly;

	public Page(String name, Page[] overridePages, Attribute[] attributes, Attribute[] hidden, Attribute[] readonly) {
		this.name = name;
		this.overridePages = overridePages;
		this.attributes = attributes;
		this.hidden = hidden;
		this.readonly = readonly;
	}
}
