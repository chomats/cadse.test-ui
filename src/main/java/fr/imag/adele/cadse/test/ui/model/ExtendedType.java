package fr.imag.adele.cadse.test.ui.model;

public class ExtendedType extends Type {
	public Type[] typesExtended;
	
	public ExtendedType(Attribute[] attributes, Field[] field, Page[] pages, Type[] typesExtended) {
		super(null, attributes, field, pages);
		this.typesExtended = typesExtended;
	}

}
