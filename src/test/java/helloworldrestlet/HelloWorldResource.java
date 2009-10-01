package helloworldrestlet;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.restlet.resource.Representation;
import org.restlet.resource.Variant;

import edu.luc.etl.restlet.spring.ConfigurableRestletResource;

/**
 * A very simple resource.
 * 
 * @author Konstantin Laufer (laufer@cs.luc.edu)
 */
public class HelloWorldResource extends ConfigurableRestletResource {

	@Override
	public Representation represent(Variant variant) {
		final Map<String, Object> dataModel = Collections.singletonMap("DATE",
				(Object) new Date());
		return createTemplateRepresentation(variant.getMediaType(), dataModel);
	}
}
