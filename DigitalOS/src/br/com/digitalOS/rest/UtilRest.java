package br.com.digitalOS.rest;

import java.io.StringWriter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.codehaus.jackson.map.ObjectMapper;

public class UtilRest {

	public String buildResponse(Object result) {
		StringWriter fw = new StringWriter();
		try {
			ObjectMapper mapper = new ObjectMapper();

			mapper.writeValue(fw, result);
			return fw.toString();
		} catch (Exception ex) {
			return null;
		}
	}

	public Response buildErrorResponse(String str) {
		ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		rb = rb.entity(str);
		rb = rb.type("text/plain");

		return rb.build();
	}
}
