package cn.bromine0x23.tools.codec.codecs;

import cn.bromine0x23.tools.codec.utility.MessageDigest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public class DigestCodec extends CodecWithVariants<MessageDigest> {

	private static final String VARIANT_ID_MD2         = "md2";
	private static final String VARIANT_ID_MD4         = "md4";
	private static final String VARIANT_ID_MD5         = "md5";
	private static final String VARIANT_ID_SHA_1       = "sha-1";
	private static final String VARIANT_ID_SHA_224     = "sha-224";
	private static final String VARIANT_ID_SHA_256     = "sha-256";
	private static final String VARIANT_ID_SHA_384     = "sha-384";
	private static final String VARIANT_ID_SHA_512     = "sha-512";
	private static final String VARIANT_ID_SHA_512_224 = "sha-512-224";
	private static final String VARIANT_ID_SHA_512_256 = "sha-512-256";
	private static final String VARIANT_ID_SHA3_224    = "sha3-224";
	private static final String VARIANT_ID_SHA3_256    = "sha3-256";
	private static final String VARIANT_ID_SHA3_384    = "sha3-384";
	private static final String VARIANT_ID_SHA3_512    = "sha3-512";
	private static final String VARIANT_ID_SM3         = "sm3";

	public DigestCodec() {
		super(createVariants());
	}

	@Override
	public String getId() {
		return "digest";
	}

	@Override
	public String getName() {
		return "消息摘要";
	}

	@Override
	public boolean supportEncode() {
		return true;
	}

	@Override
	protected String doEncode(MessageDigest messageDigest, String input) {
		return messageDigest.digestHex(input);
	}

	@Override
	protected MessageDigest getVariant(String variantId) {
		switch (variantId) {
			case VARIANT_ID_MD2:
				return MessageDigest.MD2;
			case VARIANT_ID_MD4:
				return MessageDigest.MD4;
			case VARIANT_ID_MD5:
				return MessageDigest.MD5;
			case VARIANT_ID_SHA_1:
				return MessageDigest.SHA_1;
			case VARIANT_ID_SHA_224:
				return MessageDigest.SHA_224;
			case VARIANT_ID_SHA_256:
				return MessageDigest.SHA_256;
			case VARIANT_ID_SHA_384:
				return MessageDigest.SHA_384;
			case VARIANT_ID_SHA_512:
				return MessageDigest.SHA_512;
			case VARIANT_ID_SHA_512_224:
				return MessageDigest.SHA_512_224;
			case VARIANT_ID_SHA_512_256:
				return MessageDigest.SHA_512_256;
			case VARIANT_ID_SHA3_224:
				return MessageDigest.SHA3_224;
			case VARIANT_ID_SHA3_256:
				return MessageDigest.SHA3_256;
			case VARIANT_ID_SHA3_384:
				return MessageDigest.SHA3_384;
			case VARIANT_ID_SHA3_512:
				return MessageDigest.SHA3_512;
			case VARIANT_ID_SM3:
				return MessageDigest.SM3;
			default:
				return null;
		}
	}

	private static List<Variant> createVariants() {
		List<Variant> variants = new ArrayList<>();
		variants.add(Variant.builder().id(VARIANT_ID_MD5).name("MD5").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_1).name("SHA-1").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_224).name("SHA-224").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_256).name("SHA-256").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_384).name("SHA-384").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_512).name("SHA-512").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_512_224).name("SHA-512/224").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_512_256).name("SHA-512/256").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA3_224).name("SHA3-224").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA3_256).name("SHA3-256").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA3_384).name("SHA3-384").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA3_512).name("SHA3-512").build());
		variants.add(Variant.builder().id(VARIANT_ID_SM3).name("SM3").build());
		variants.add(Variant.builder().id(VARIANT_ID_MD2).name("MD2").build());
		variants.add(Variant.builder().id(VARIANT_ID_MD4).name("MD4").build());
		return variants;
	}
}
