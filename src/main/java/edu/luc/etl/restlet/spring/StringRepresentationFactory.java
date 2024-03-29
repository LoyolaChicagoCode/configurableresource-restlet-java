package edu.luc.etl.restlet.spring;

import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.resource.Representation;
import org.restlet.resource.StringRepresentation;
import org.restlet.util.Template;

/**
 * A factory for representations based on the Restlet String template.
 * 
 * @author Konstantin Laufer (laufer@cs.luc.edu)
 */
public class StringRepresentationFactory implements RepresentationFactory {

	private Template template;

	public void setTemplateString(final String templateString) {
		if (templateString == null)
			throw new IllegalArgumentException("tempateString == null");
		this.template = new Template(templateString);
	}

	public String getTemplateString() {
		return getTemplate().getPattern();
	}

	public Template getTemplate() {
		return template;
	}

	private MediaType mediaType;

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(final MediaType mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public Representation createRepresentation(
			final MediaType defaultMediaType,
			final Map<String, Object> dataModel) {
		MediaType actualMediaType = getMediaType();
		if (actualMediaType == null)
			actualMediaType = defaultMediaType;
		return new StringRepresentation(getTemplate().format(dataModel),
				actualMediaType);
	}
}
