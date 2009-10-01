package edu.luc.etl.restlet.spring;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Representation;

/**
 * A factory for representations based on the Restlet JSON template extension.
 * 
 * @author Konstantin Laufer (laufer@cs.luc.edu)
 */
public class JsonRepresentationFactory implements RepresentationFactory {

	@Override
	public Representation createRepresentation(
			final MediaType defaultMediaType,
			final Map<String, Object> dataModel) {
		final Map<Object, Object> model = new HashMap<Object, Object>();
		model.putAll(dataModel);
		return new JsonRepresentation(model);
	}
}
