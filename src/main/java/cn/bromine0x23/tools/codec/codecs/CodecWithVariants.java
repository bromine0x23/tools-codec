package cn.bromine0x23.tools.codec.codecs;

import lombok.Getter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public abstract class CodecWithVariants<TImplement> implements Codec {

	@Getter
	private final List<Variant> variants;

	protected CodecWithVariants(List<Variant> variants) {
		this.variants = Collections.unmodifiableList(variants);
	}

	@Override
	public String encode(String input, String variantId) throws IOException {
		if (!supportEncode()) {
			throw new IllegalArgumentException();
		}
		TImplement implement = findVariant(variantId);
		return doEncode(implement, input);
	}

	@Override
	public String decode(String input, String variantId) throws IOException {
		if (!supportDecode()) {
			throw new IllegalArgumentException();
		}
		TImplement implement = findVariant(variantId);
		return doDecode(implement, input);
	}

	protected String doEncode(TImplement implement, String input) throws IOException {
		throw new UnsupportedOperationException();
	}

	protected String doDecode(TImplement implement, String input) throws IOException {
		throw new UnsupportedOperationException();
	}

	protected TImplement getVariant(String variantId) {
		return null;
	}

	protected TImplement getDefaultVariant() {
		return null;
	}

	private TImplement findVariant(String variantId) {
		TImplement implement = variantId != null ? getVariant(variantId) : getDefaultVariant();
		if (implement == null) {
			throw new IllegalArgumentException();
		}
		return implement;
	}

}
