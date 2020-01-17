package cn.bromine0x23.tools.codec.codecs;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public class URLCodec extends CodecWithVariants<org.apache.commons.codec.net.URLCodec> {

	private static final String VARIANT_ID_UTF_8    = "utf-8";
	private static final String VARIANT_ID_UTF_16BE = "utf-16be";
	private static final String VARIANT_ID_UTF_16LE = "utf-16le";
	private static final String VARIANT_ID_UTF_32BE = "utf-32be";
	private static final String VARIANT_ID_UTF_32LE = "utf-32le";
	private static final String VARIANT_ID_GBK      = "gbk";
	private static final String VARIANT_ID_GB18030  = "gb18030";

	private static final org.apache.commons.codec.net.URLCodec UTF_8    = new org.apache.commons.codec.net.URLCodec("UTF-8");
	private static final org.apache.commons.codec.net.URLCodec UTF_16BE = new org.apache.commons.codec.net.URLCodec("UTF-16BE");
	private static final org.apache.commons.codec.net.URLCodec UTF_16LE = new org.apache.commons.codec.net.URLCodec("UTF-16LE");
	private static final org.apache.commons.codec.net.URLCodec UTF_32BE = new org.apache.commons.codec.net.URLCodec("UTF-32BE");
	private static final org.apache.commons.codec.net.URLCodec UTF_32LE = new org.apache.commons.codec.net.URLCodec("UTF-32LE");
	private static final org.apache.commons.codec.net.URLCodec GBK      = new org.apache.commons.codec.net.URLCodec("GBK");
	private static final org.apache.commons.codec.net.URLCodec GB18030  = new org.apache.commons.codec.net.URLCodec("GB18030");

	public URLCodec() {
		super(createVariants());
	}

	@Override
	public String getId() {
		return "url";
	}

	@Override
	public String getName() {
		return "URL";
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
	protected String doEncode(org.apache.commons.codec.net.URLCodec codec, String input) throws IOException {
		try {
			return codec.encode(input);
		} catch (EncoderException e) {
			throw new IOException("编码失败", e);
		}
	}

	@Override
	protected String doDecode(org.apache.commons.codec.net.URLCodec codec, String input) throws IOException {
		try {
			return codec.decode(input);
		} catch (DecoderException e) {
			throw new IOException("编码失败", e);
		}
	}

	@Override
	protected org.apache.commons.codec.net.URLCodec getVariant(String variantId) {
		switch (variantId) {
			case VARIANT_ID_UTF_8:
				return UTF_8;
			case VARIANT_ID_UTF_16BE:
				return UTF_16BE;
			case VARIANT_ID_UTF_16LE:
				return UTF_16LE;
			case VARIANT_ID_UTF_32BE:
				return UTF_32BE;
			case VARIANT_ID_UTF_32LE:
				return UTF_32LE;
			case VARIANT_ID_GBK:
				return GBK;
			case VARIANT_ID_GB18030:
				return GB18030;
			default:
				return super.getVariant(variantId);
		}
	}

	@Override
	protected org.apache.commons.codec.net.URLCodec getDefaultVariant() {
		return UTF_8;
	}

	private static List<Variant> createVariants() {
		List<Variant> variants = new ArrayList<>();
		variants.add(Variant.builder().id(VARIANT_ID_UTF_8).name("UTF-8").build());
		variants.add(Variant.builder().id(VARIANT_ID_UTF_16BE).name("UTF-16BE").build());
		variants.add(Variant.builder().id(VARIANT_ID_UTF_16LE).name("UTF-16LE").build());
		variants.add(Variant.builder().id(VARIANT_ID_UTF_32BE).name("UTF-32BE").build());
		variants.add(Variant.builder().id(VARIANT_ID_UTF_32LE).name("UTF-32LE").build());
		variants.add(Variant.builder().id(VARIANT_ID_GBK).name("GBK").build());
		variants.add(Variant.builder().id(VARIANT_ID_GB18030).name("GB18030").build());
		return variants;
	}
}
