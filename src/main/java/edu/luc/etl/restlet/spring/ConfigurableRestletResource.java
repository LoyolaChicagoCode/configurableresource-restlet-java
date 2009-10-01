package edu.luc.etl.restlet.spring;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.Variant;
import org.springframework.beans.BeanUtils;

/**
 * A Spring-configurable Restlet Resource.
 * 
 * @author Konstantin Laufer (laufer@cs.luc.edu)
 */
public class ConfigurableRestletResource extends Resource {

	// TODO generation of representations of response entities methods other
	// than GET
	// TODO think about how to deal with dependencies on so many
	// concrete representations

	@Override
	public void init(final Context context, final Request request,
			final Response response) {
		// workaround for overzealous init method
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
				"before init: variants = " + getVariants());
		final ResourcePropertyHolder backup = new ResourcePropertyHolder();
		BeanUtils.copyProperties(this, backup);
		super.init(context, request, response);
		BeanUtils.copyProperties(backup, this);
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
				"after init: variants = " + getVariants());
	}

	@Override
	public List<Variant> getVariants() {
		final List<Variant> variants = new LinkedList<Variant>();
		for (MediaType mediaType : getRepresentationTemplates().keySet())
			variants.add(new Variant(mediaType));
		return variants;
	}

	@Override
	public void setVariants(java.util.List<Variant> variants) {
		throw new UnsupportedOperationException(
				"use setRepresentationTemplates instead");
	}

	@SuppressWarnings("unchecked")
	public Map<MediaType, RepresentationFactory> getRepresentationTemplates() {
		return representationTemplates != null ? representationTemplates
				: Collections.EMPTY_MAP;
	}

	public void setRepresentationTemplates(
			final Map<MediaType, RepresentationFactory> representationTemplates) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
				"setRepresentationTemplates: " + representationTemplates);
		if (this.representationTemplates == null)
			this.representationTemplates = new LinkedHashMap<MediaType, RepresentationFactory>();
		else
			this.representationTemplates.clear();
		this.representationTemplates.putAll(representationTemplates);
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
				"setRepresentationTemplates: " + representationTemplates);
	}

	private LinkedHashMap<MediaType, RepresentationFactory> representationTemplates;

	protected Representation createTemplateRepresentation(
			final MediaType mediaType, final Map<String, Object> dataModel) {
		final RepresentationFactory factory = representationTemplates
				.get(mediaType);
		return factory.createRepresentation(mediaType, dataModel);
	}

	protected final static class ResourcePropertyHolder {

		private boolean modifiable;

		public boolean isModifiable() {
			return modifiable;
		}

		public void setModifiable(final boolean modifiable) {
			this.modifiable = modifiable;
		}

		private boolean available;

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

		private boolean negotiateContent;

		public boolean isNegotiateContent() {
			return negotiateContent;
		}

		public void setNegotiateContent(boolean negotiateContent) {
			this.negotiateContent = negotiateContent;
		}

		private boolean readable;

		public boolean isReadable() {
			return readable;
		}

		public void setReadable(boolean readable) {
			this.readable = readable;
		}
	}
}
