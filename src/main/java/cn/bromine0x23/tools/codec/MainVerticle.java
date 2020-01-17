package cn.bromine0x23.tools.codec;

import cn.bromine0x23.tools.codec.handlers.CodecsHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.jade.JadeTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@Slf4j
public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) {
		Security.addProvider(new BouncyCastleProvider());
		vertx
			.createHttpServer()
			.requestHandler(createRouter())
			.listen(port(), http -> {
				if (http.succeeded()) {
					startPromise.complete();
					log.info("HTTP server started on port {}", http.result().actualPort());
				} else {
					startPromise.fail(http.cause());
				}
			});
	}

	private int port() {
		var value = System.getProperty("http.port", System.getenv("HTTP_PORT"));
		return value != null ? Integer.parseInt(value) : 8888;
	}

	private Router createRouter() {
		var router = Router.router(vertx);
		router.route()
			.handler(LoggerHandler.create())
			.handler(BodyHandler.create(false))
			.handler(ResponseContentTypeHandler.create())
			.failureHandler(ErrorHandler.create());

		router.mountSubRouter("/api/codecs", createCodecsRouter());

		router.route("/webjars/*")
			.handler(StaticHandler.create("META-INF/resources/webjars"));
		router.get()
			.handler(StaticHandler.create("public"))
			.handler(TemplateHandler.create(createTemplateEngine()));
		return router;
	}

	private Router createCodecsRouter() {
		var router        = Router.router(vertx);
		var codecsHandler = new CodecsHandler();
		router.get("/").handler(codecsHandler::index);
		router.post("/:id/encode").handler(codecsHandler::encode);
		router.post("/:id/decode").handler(codecsHandler::decode);
		router.post("/:id/:variantId/encode").handler(codecsHandler::encode);
		router.post("/:id/:variantId/decode").handler(codecsHandler::decode);
		return router;
	}

	private TemplateEngine createTemplateEngine() {
		return JadeTemplateEngine.create(vertx);
	}
}
