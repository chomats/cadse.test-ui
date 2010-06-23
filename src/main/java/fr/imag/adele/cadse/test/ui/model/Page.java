package fr.imag.adele.cadse.test.ui.model;

public class Page {

	private String name;

	public Page(String name, Page[] overridePages, Attribute[] attributes, Attribute[] hidden, Attribute[] readonly) {
		this.name = name;
	}
}
