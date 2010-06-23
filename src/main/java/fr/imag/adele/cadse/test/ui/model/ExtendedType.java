package fr.imag.adele.cadse.test.ui.model;

public class ExtendedType extends Type {
	public Type[] typesExtended;
	
	public ExtendedType(Attribute[] attributes, Field[] field, Type[] typesExtended) {
		super(null, attributes, field);
		this.typesExtended = typesExtended;
	}

}
