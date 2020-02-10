package cn.bromine0x23.tools.codec.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.MessageFormat;

/**
 * 消息摘要算法工具类
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@AllArgsConstructor
public enum MessageDigest {

	/**
	 * <a href="https://en.wikipedia.org/wiki/MD2_(hash_function)">MD2</a>
	 *
	 * @see sun.security.provider.MD2
	 */
	MD2("MD2", 128),
	/**
	 * <a href="https://en.wikipedia.org/wiki/MD4">MD4</a>
	 *
	 * @see sun.security.provider.MD4
	 */
	MD4("MD4", 128),
	/**
	 * <a href="https://en.wikipedia.org/wiki/MD5">MD5</a>
	 *
	 * @see sun.security.provider.MD5
	 */
	MD5("MD5", 128),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-1">SHA-1</a>
	 *
	 * @see sun.security.provider.SHA
	 */
	SHA_1("SHA", 160),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-224
	 *
	 * @see sun.security.provider.SHA2.SHA224
	 */
	SHA_224("SHA-224", 224),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-256
	 *
	 * @see sun.security.provider.SHA2.SHA256
	 */
	SHA_256("SHA-256", 256),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-384
	 *
	 * @see sun.security.provider.SHA5.SHA384
	 */
	SHA_384("SHA-384", 384),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512
	 *
	 * @see sun.security.provider.SHA5.SHA512
	 */
	SHA_512("SHA-512", 512),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512/224
	 *
	 * @see sun.security.provider.SHA5.SHA512_224
	 * @since Java9
	 */
	SHA_512_224("SHA-512/224", 224),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512/256
	 *
	 * @see sun.security.provider.SHA5.SHA512_256
	 * @since Java9
	 */
	SHA_512_256("SHA-512/256", 256),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-224
	 *
	 * @see sun.security.provider.SHA3.SHA224
	 * @since Java9
	 */
	SHA3_224("SHA3-224", 224),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-256
	 *
	 * @see sun.security.provider.SHA3.SHA256
	 * @since Java9
	 */
	SHA3_256("SHA3-256", 256),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-384
	 *
	 * @see sun.security.provider.SHA3.SHA384
	 * @since Java9
	 */
	SHA3_384("SHA3-384", 384),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-512
	 *
	 * @see sun.security.provider.SHA3.SHA512
	 * @since Java9
	 */
	SHA3_512("SHA3-512", 512),

	/**
	 * <a href="https://en.wikipedia.org/wiki/MD2_(hash_function)">MD2</a>
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.MD2.Digest
	 */
	BC_MD2("MD2", 128, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/MD4">MD4</a>
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.MD4.Digest
	 */
	BC_MD4("MD4", 128, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/MD5">MD5</a>
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.MD5.Digest
	 */
	BC_MD5("MD5", 128, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-1">SHA-1</a>
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA1.Digest
	 */
	BC_SHA_1("SHA", 160, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-224
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA224.Digest
	 */
	BC_SHA_224("SHA-224", 224, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-256
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA256.Digest
	 */
	BC_SHA_256("SHA-256", 256, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-384
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA384.Digest
	 */
	BC_SHA_384("SHA-384", 384, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA512.Digest
	 */
	BC_SHA_512("SHA-512", 512, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512/224
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA512.DigestT224
	 * @since Java9
	 */
	BC_SHA_512_224("SHA-512/224", 224, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512/256
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA512.DigestT256
	 * @since Java9
	 */
	BC_SHA_512_256("SHA-512/256", 256, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-224
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest224
	 * @since Java9
	 */
	BC_SHA3_224("SHA3-224", 224, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-256
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest256
	 * @since Java9
	 */
	BC_SHA3_256("SHA3-256", 256, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-384
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest384
	 * @since Java9
	 */
	BC_SHA3_384("SHA3-384", 384, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-512
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest512
	 * @since Java9
	 */
	BC_SHA3_512("SHA3-512", 512, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/SM3_(hash_function)">SM3</a>
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SM3.Digest
	 */
	BC_SM3("SM3", 256, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/GOST_(hash_function)">GOST</a>: GOST-3411
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.GOST3411.Digest
	 */
	BC_GOST3411("GOST3411", 256, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/GOST_(hash_function)">GOST</a>: GOST-3411-2012-256
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.GOST3411.Digest2012_256
	 */
	BC_GOST3411_2012_256("GOST3411-2012-256", 256, "BC"),
	/**
	 * <a href="https://en.wikipedia.org/wiki/GOST_(hash_function)">GOST</a>: GOST-3411-2012-512
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.GOST3411.Digest2012_512
	 */
	BC_GOST3411_2012_512("GOST3411-2012-512", 512, "BC"),
	;

	/**
	 * 算法名
	 */
	@Getter
	private final String algorithm;

	/**
	 * 结果比特长度
	 */
	@Getter
	private final int bits;

	@Getter
	private final String provider;

	MessageDigest(String algorithm, int bits) {
		this.algorithm = algorithm;
		this.bits      = bits;
		this.provider  = null;
	}

	/**
	 * 对 <em>字节数组</em> 执行消息摘要算法，返回 <em>字节数组</em> 。
	 *
	 * @param message 消息
	 * @return 摘要结果
	 * @throws IllegalStateException 环境不支持对应的消息摘要算法
	 */
	public byte[] digest(byte[] message) {
		return getJceMessageDigest().digest(message);
	}

	/**
	 * 对 <em>字符串</em> 消息执行消息摘要算法，返回 <em>字节数组</em> 。
	 *
	 * @param message 消息
	 * @return 摘要结果
	 * @throws IllegalStateException 环境不支持对应的消息摘要算法
	 */
	public byte[] digest(@NonNull String message) {
		return digest(message.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * 对 <em>字节数组</em> 执行消息摘要算法，返回 <em>小写16进制</em> 格式的<em>字符串</em> 。
	 *
	 * @param message 消息
	 * @return 摘要结果
	 * @throws IllegalStateException 环境不支持对应的消息摘要算法
	 */
	public String digestHex(byte[] message) {
		return Hex.encodeHexString(digest(message));
	}

	/**
	 * 对 <em>字符串</em> 执行消息摘要算法，返回 <em>小写16进制</em> 格式的<em>字符串</em> 。
	 *
	 * @param message 消息
	 * @return 摘要结果
	 * @throws IllegalStateException 环境不支持对应的消息摘要算法
	 */
	public String digestHex(@NonNull String message) {
		return Hex.encodeHexString(digest(message));
	}

	private java.security.MessageDigest getJceMessageDigest() {
		try {
			if (provider != null) {
				return java.security.MessageDigest.getInstance(algorithm, provider);
			} else {
				return java.security.MessageDigest.getInstance(algorithm);
			}
		} catch (NoSuchAlgorithmException exception) {
			String message = MessageFormat.format("Could not find MessageDigest with algorithm `{0}`.", algorithm);
			throw new IllegalStateException(message, exception);
		} catch (NoSuchProviderException exception) {
			String message = MessageFormat.format("Could not find MessageDigest with algorithm `{0}` from provider `{1}` .", algorithm, provider);
			throw new IllegalStateException(message, exception);
		}
	}
}
