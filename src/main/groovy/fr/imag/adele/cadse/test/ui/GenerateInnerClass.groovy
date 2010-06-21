package fr.imag.adele.cadse.test.ui

class GenerateInnerClass  extends GeneratePart {
	
	String className = 'MyClass'
		String extendedClassName = 'DTextUI<RuningInteractionController>'
			String body = '';
	String template = '''
		static class ${className} extends ${extendedClassName} {
		
		$body
	}
		'''
		
		
	public String innerPart() {	
		def binding = [
				className : className,
				extendedClassName : extendedClassName,
				body : body]
		def engine = new groovy.text.GStringTemplateEngine()
		
		def template = engine.createTemplate(template).make(binding)
		return template.toString()
	}
}
