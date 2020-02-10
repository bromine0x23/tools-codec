package cn.bromine0x23.tools.codec.handlers;

import cn.bromine0x23.tools.codec.codecs.Codec;
import cn.bromine0x23.tools.codec.utility.Codecs;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.io.IOException;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public class CodecsHandler {

	public void index(RoutingContext context) {
		context.response()
			.putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
			.end(Json.encodeToBuffer(Codecs.getCodecs()));
	}

	public void encode(RoutingContext context) {
		Payload payload = extractPayload(context);
		Codec   codec   = Codecs.get(payload.codecId);
		if (codec == null) {
			context.response()
				.setStatusCode(HttpResponseStatus.NOT_FOUND.code())
				.end("Codec `" + payload.codecId + "` not found.");
			return;
		}
		if (!codec.supportEncode()) {
			context.response()
				.setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
				.end("Codec `" + payload.codecId + "` doesn't support encode.");
			return;
		}
		try {
			String output = codec.encode(payload.input, payload.variantId);
			context.response()
				.putHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
				.end(output);
		} catch (IOException exception) {
			context.fail(HttpResponseStatus.BAD_REQUEST.code(), exception);
		}
	}

	public void decode(RoutingContext context) {
		Payload payload = extractPayload(context);
		Codec   codec   = Codecs.get(payload.codecId);
		if (codec == null) {
			context.response()
				.setStatusCode(HttpResponseStatus.NOT_FOUND.code())
				.end("Codec `" + payload.codecId + "` not found.");
			return;
		}
		if (!codec.supportDecode()) {
			context.response()
				.setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
				.end("Codec `" + payload.codecId + "` doesn't support decode.");
			return;
		}
		try {
			String output = codec.decode(payload.input, payload.variantId);
			context.response()
				.putHeader(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8")
				.end(output);
		} catch (IOException exception) {
			context.fail(HttpResponseStatus.BAD_REQUEST.code(), exception);
		}
	}

	private static Payload extractPayload(RoutingContext context) {
		HttpServerRequest request = context.request();
		Payload           payload = new Payload();
		payload.codecId   = request.getParam("id");
		payload.variantId = request.getParam("variantId");
		payload.input     = context.getBodyAsString();
		return payload;
	}

	private static class Payload {
		private String codecId;
		private String variantId;
		private String input;
	}
}
