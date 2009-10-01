package edu.luc.etl.restlet.spring;

import java.io.IOException;
import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.resource.Representation;

import freemarker.template.Configuration;

/**
 * A factory for representations based on the Restlet Freemarker template
 * extension.
 * 
 * @author Konstantin Laufer (laufer@cs.luc.edu)
 */
public class FreemarkerRepresentationFactory implements RepresentationFactory {

	private MediaType mediaType;

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(final MediaType mediaType) {
		this.mediaType = mediaType;
	}

	private String templateName;

	public void setTemplateName(final String templateName) {
		this.templateName = templateName;
	}

	/**
	 * The Freemarker configuration for this resource.
	 */
	private Configuration freemarkerConfig;

	/**
	 * Sets the Freemarker configuration for this resource.
	 * 
	 * @param freemarkerConfig
	 *            the Freemarker configuration
	 */
	public void setFreemarkerConfig(final Configuration freemarkerConfig) {
		this.freemarkerConfig = freemarkerConfig;
	}

	@Override
	public Representation createRepresentation(
			final MediaType defaultMediaType,
			final Map<String, Object> dataModel) {
		MediaType actualMediaType = getMediaType();
		if (actualMediaType == null)
			actualMediaType = defaultMediaType;
		try {
			return new TemplateRepresentation(freemarkerConfig
					.getTemplate(templateName), dataModel, actualMediaType);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
