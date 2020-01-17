package cn.bromine0x23.tools.codec.codecs;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.net.BCodec;
import org.apache.commons.codec.net.QCodec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public class MIMECodec extends CodecWithVariants<MIMECodec.StringCodec> {

	private static final String VARIANT_ID_BASE64           = "base64";
	private static final String VARIANT_ID_QUOTED_PRINTABLE = "quoted-printable";

	private static final StringCodec BASE64           = new StringCodec(new BCodec());
	private static final StringCodec QUOTED_PRINTABLE = new StringCodec(new QCodec());

	public MIMECodec() {
		super(createVariants());
	}

	@Override
	public String getId() {
		return "mime";
	}

	@Override
	public String getName() {
		return "MIME";
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
	protected String doEncode(StringCodec codec, String input) throws IOException {
		try {
			return codec.encode(input);
		} catch (EncoderException e) {
			throw new IOException("编码失败", e);
		}
	}

	@Override
	protected String doDecode(StringCodec codec, String input) throws IOException {
		try {
			return codec.decode(input);
		} catch (DecoderException e) {
			throw new IOException("编码失败", e);
		}
	}

	@Override
	protected StringCodec getVariant(String variantId) {
		switch (variantId) {
			case VARIANT_ID_BASE64:
				return BASE64;
			case VARIANT_ID_QUOTED_PRINTABLE:
				return QUOTED_PRINTABLE;
			default:
				return null;
		}
	}

	private static List<Variant> createVariants() {
		List<Variant> variants = new ArrayList<>();
		variants.add(Variant.builder().id(VARIANT_ID_BASE64).name("Base64").build());
		variants.add(Variant.builder().id(VARIANT_ID_QUOTED_PRINTABLE).name("Quoted-Printable").build());
		return variants;
	}

	public static class StringCodec implements StringEncoder, StringDecoder {

		private final Object delegate;

		private StringCodec(Object delegate) {
			assert delegate instanceof StringEncoder;
			assert delegate instanceof StringDecoder;
			this.delegate = delegate;
		}

		@Override
		public String encode(String source) throws EncoderException {
			return ((StringEncoder) delegate).encode(source);
		}

		@Override
		public Object encode(Object source) throws EncoderException {
			return ((StringEncoder) delegate).encode(source);
		}

		@Override
		public String decode(String source) throws DecoderException {
			return ((StringDecoder) delegate).decode(source);
		}

		@Override
		public Object decode(Object source) throws DecoderException {
			return ((StringDecoder) delegate).decode(source);
		}
	}
}
