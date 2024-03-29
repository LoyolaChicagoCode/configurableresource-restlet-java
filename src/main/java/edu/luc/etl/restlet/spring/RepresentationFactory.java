package edu.luc.etl.restlet.spring;

import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.resource.Representation;

/**
 * Uniform interface for factories that create concrete representations based on
 * specific template mechanisms.
 * 
 * @author Konstantin Laufer (laufer@cs.luc.edu)
 */
public interface RepresentationFactory {

	/**
	 * Creates a concrete representation for the given media type and data
	 * model.
	 * 
	 * @param defaultMediaType
	 *            the desired media type
	 * @param dataModel
	 *            the data model
	 * @return the representation
	 */
	Representation createRepresentation(MediaType defaultMediaType,
			Map<String, Object> dataModel);
}
