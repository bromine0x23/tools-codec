package cn.bromine0x23.tools.codec.handlers;

import cn.bromine0x23.tools.codec.codecs.Codec;
import cn.bromine0x23.tools.codec.utility.Codecs;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpHeaders;
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
		String codecId   = context.request().getParam("id");
		String variantId = context.request().getParam("variantId");
		String input     = context.getBodyAsString();
		Codec  codec     = Codecs.get(codecId);
		if (codec == null) {
			context.response()
				.setStatusCode(HttpResponseStatus.NOT_FOUND.code())
				.end("Codec `" + codecId + "` not found.");
			return;
		}
		if (!codec.supportEncode()) {
			context.response()
				.setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
				.end("Codec `" + codecId + "` doesn't support encode.");
			return;
		}
		try {
			String output = codec.encode(input, variantId);
			context.response()
				.putHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
				.end(output);
		} catch (IOException exception) {
			context.fail(HttpResponseStatus.BAD_REQUEST.code(), exception);
		}
	}

	public void decode(RoutingContext context) {
		String codecId   = context.request().getParam("id");
		String variantId = context.request().getParam("variantId");
		String input     = context.getBodyAsString();
		Codec  codec     = Codecs.get(codecId);
		if (codec == null) {
			context.response()
				.setStatusCode(HttpResponseStatus.NOT_FOUND.code())
				.end("Codec `" + codecId + "` not found.");
			return;
		}
		if (!codec.supportDecode()) {
			context.response()
				.setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
				.end("Codec `" + codecId + "` doesn't support decode.");
			return;
		}
		try {
			String output = codec.decode(input, variantId);
			context.response()
				.putHeader(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8")
				.end(output);
		} catch (IOException exception) {
			context.fail(HttpResponseStatus.BAD_REQUEST.code(), exception);
		}
	}
}
