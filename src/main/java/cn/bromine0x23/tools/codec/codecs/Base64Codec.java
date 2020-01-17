package cn.bromine0x23.tools.codec.codecs;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BaseNCodec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public class Base64Codec extends CodecWithVariants<Base64> {

	private static final String VARIANT_ID_RFC1421     = "rfc1421";
	private static final String VARIANT_ID_RFC2045     = "rfc2045";
	private static final String VARIANT_ID_RFC4648     = "rfc4648";
	private static final String VARIANT_ID_RFC4648_URL = "rfc4648-url";

	private static final byte[] LINE_SEPARATOR = {'\r', '\n'};

	private static final Base64 RFC1421     = new Base64(BaseNCodec.PEM_CHUNK_SIZE, LINE_SEPARATOR, false);
	private static final Base64 RFC2045     = new Base64(BaseNCodec.MIME_CHUNK_SIZE, LINE_SEPARATOR, false);
	private static final Base64 RFC4648     = new Base64(0, LINE_SEPARATOR, false);
	private static final Base64 RFC4648_URL = new Base64(0, LINE_SEPARATOR, true);

	public Base64Codec() {
		super(createVariants());
	}

	@Override
	public String getId() {
		return "base64";
	}

	@Override
	public String getName() {
		return "Base64";
	}

	@Override
	public boolean supportEncode() {
		return true;
	}

	@Override
	public boolean supportDecode() {
		return true;
	}

	@Override
	protected String doEncode(Base64 base64, String input) {
		return base64.encodeToString(input.getBytes());
	}

	@Override
	protected String doDecode(Base64 base64, String input) {
		return new String(base64.decode(input));
	}

	@Override
	protected Base64 getVariant(String variantId) {
		switch (variantId) {
			case VARIANT_ID_RFC1421:
				return RFC1421;
			case VARIANT_ID_RFC2045:
				return RFC2045;
			case VARIANT_ID_RFC4648:
				return RFC4648;
			case VARIANT_ID_RFC4648_URL:
				return RFC4648_URL;
			default:
				return null;
		}
	}

	@Override
	protected Base64 getDefaultVariant() {
		return RFC4648;
	}

	private static List<Variant> createVariants() {
		List<Variant> variants = new ArrayList<>();
		variants.add(Variant.builder().id(VARIANT_ID_RFC4648).name("通用（RFC4648）").build());
		variants.add(Variant.builder().id(VARIANT_ID_RFC4648_URL).name("URL安全（RFC4648）").build());
		variants.add(Variant.builder().id(VARIANT_ID_RFC1421).name("PEM格式（RFC1421）").build());
		variants.add(Variant.builder().id(VARIANT_ID_RFC2045).name("MIME格式（RFC2045）").build());
		return variants;
	}
}
