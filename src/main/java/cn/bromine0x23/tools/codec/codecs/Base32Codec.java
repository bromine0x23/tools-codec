package cn.bromine0x23.tools.codec.codecs;

import org.apache.commons.codec.binary.Base32;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public class Base32Codec extends CodecWithVariants<Base32> {

	private static final String VARIANT_ID_RFC4648     = "rfc4648";
	private static final String VARIANT_ID_RFC4648_HEX = "rfc4648-hex";

	private static final Base32 RFC4648     = new Base32(false);
	private static final Base32 RFC4648_HEX = new Base32(true);

	public Base32Codec() {
		super(createVariants());
	}

	@Override
	public String getId() {
		return "base32";
	}

	@Override
	public String getName() {
		return "Base32";
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
	protected String doEncode(Base32 base32, String input) {
		return base32.encodeToString(input.getBytes());
	}

	@Override
	protected String doDecode(Base32 base32, String input) {
		return new String(base32.decode(input));
	}

	@Override
	protected Base32 getVariant(String variantId) {
		switch (variantId) {
			case VARIANT_ID_RFC4648:
				return RFC4648;
			case VARIANT_ID_RFC4648_HEX:
				return RFC4648_HEX;
			default:
				return null;
		}
	}

	@Override
	protected Base32 getDefaultVariant() {
		return RFC4648;
	}

	private static List<Variant> createVariants() {
		List<Variant> variants = new ArrayList<>();
		variants.add(Variant.builder().id(VARIANT_ID_RFC4648).name("通用（RFC4648）").build());
		variants.add(Variant.builder().id(VARIANT_ID_RFC4648_HEX).name("Hex变种（RFC4648）").build());
		return variants;
	}
}
