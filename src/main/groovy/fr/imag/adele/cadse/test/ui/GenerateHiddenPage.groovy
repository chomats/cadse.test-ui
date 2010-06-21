package fr.imag.adele.cadse.test.ui

class GenerateHiddenPage  extends GeneratePart {
	
	String cst = null;
	String cltHiddenAttributes ='cltHiddenAttributes';
	String name = 'Hidden attributes'
	String label= 'Hidden attributes'
	String title= 'Hidden attributes'
	String description= 'Hidden attributes'
	def hiddenAttributes = ['CADSE_UICST.IT_A_at_ATTR_']
	
	String typeAttached = 'CADSE_UICST.IT_B';
	
	String template ='''\
	{
	// Hidden attributes for ContentLT
	IPage ${cltHiddenAttributes} = new PageImpl(UUID.randomUUID(), 
			"${name}", "${label}",
			"${title}", "${description}", false, null);
	cltHiddenAttributes.addHiddenAttributes(${hiddenAttributes.join(', ')});
	${typeAttached}.addModificationPages(${cltHiddenAttributes});
	${typeAttached}.addCreationPages(${cltHiddenAttributes});
	
	<% if (cst != null) { print "$cst = $cltHiddenAttributes"%>
	}
'''
	
	public String initPart() {	
		def binding = [
				cst : cst,
				cltHiddenAttributes : cltHiddenAttributes,
				name : name,
				label: label,
				title: title,
				description : description,
				hiddenAttributes : hiddenAttributes,                        
				typeAttached:typeAttached  ]
		def engine = new groovy.text.GStringTemplateEngine()
		
		def template = engine.createTemplate(template).make(binding)
		return template.toString()
	}
}
