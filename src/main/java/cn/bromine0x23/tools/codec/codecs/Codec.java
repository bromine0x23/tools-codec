package cn.bromine0x23.tools.codec.codecs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public interface Codec {

	@JsonProperty("id")
	String getId();

	@JsonProperty("name")
	String getName();

	@JsonProperty("supportEncode")
	default boolean supportEncode() {
		return false;
	}

	default String encode(String input, String variantId) throws IOException {
		throw new IllegalArgumentException();
	}

	@JsonProperty("supportDecode")
	default boolean supportDecode() {
		return false;
	}

	default String decode(String input, String variantId) throws IOException {
		throw new IllegalArgumentException();
	}

	@JsonProperty("variants")
	default List<Variant> getVariants() {
		return Collections.emptyList();
	}

	@Builder
	@Data
	class Variant {

		@JsonProperty("id")
		private String id;

		@JsonProperty("name")
		private String name;

	}
}
