package cn.bromine0x23.tools.codec.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

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
	 * @see org.bouncycastle.jcajce.provider.digest.MD2.Digest
	 */
	MD2("MD2", 128),

	/**
	 * <a href="https://en.wikipedia.org/wiki/MD4">MD4</a>
	 *
	 * @see sun.security.provider.MD4
	 * @see org.bouncycastle.jcajce.provider.digest.MD4.Digest
	 */
	MD4("MD4", 128),

	/**
	 * <a href="https://en.wikipedia.org/wiki/MD5">MD5</a>
	 *
	 * @see sun.security.provider.MD5
	 * @see org.bouncycastle.jcajce.provider.digest.MD5.Digest
	 */
	MD5("MD5", 128),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-1">SHA-1</a>
	 *
	 * @see sun.security.provider.SHA
	 * @see org.bouncycastle.jcajce.provider.digest.SHA1.Digest
	 */
	SHA_1("SHA", 160),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-224
	 *
	 * @see sun.security.provider.SHA2.SHA224
	 * @see org.bouncycastle.jcajce.provider.digest.SHA224.Digest
	 */
	SHA_224("SHA-224", 224),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-256
	 *
	 * @see sun.security.provider.SHA2.SHA256
	 * @see org.bouncycastle.jcajce.provider.digest.SHA256.Digest
	 */
	SHA_256("SHA-256", 256),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-384
	 *
	 * @see sun.security.provider.SHA5.SHA384
	 * @see org.bouncycastle.jcajce.provider.digest.SHA384.Digest
	 */
	SHA_384("SHA-384", 384),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512
	 *
	 * @see sun.security.provider.SHA5.SHA512
	 * @see org.bouncycastle.jcajce.provider.digest.SHA512.Digest
	 */
	SHA_512("SHA-512", 512),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512/224
	 *
	 * @see sun.security.provider.SHA5.SHA512_224
	 * @see org.bouncycastle.jcajce.provider.digest.SHA512.DigestT224
	 * @since Java9
	 */
	SHA_512_224("SHA-512/224", 224),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>: SHA-512/256
	 *
	 * @see sun.security.provider.SHA5.SHA512_256
	 * @see org.bouncycastle.jcajce.provider.digest.SHA512.DigestT256
	 * @since Java9
	 */
	SHA_512_256("SHA-512/256", 256),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-224
	 *
	 * @see sun.security.provider.SHA3.SHA224
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest224
	 * @since Java9
	 */
	SHA3_224("SHA3-224", 224),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-256
	 *
	 * @see sun.security.provider.SHA3.SHA256
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest256
	 * @since Java9
	 */
	SHA3_256("SHA3-256", 256),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-384
	 *
	 * @see sun.security.provider.SHA3.SHA384
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest384
	 * @since Java9
	 */
	SHA3_384("SHA3-384", 384),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SHA-3">SHA-3</a>: SHA3-512
	 *
	 * @see sun.security.provider.SHA3.SHA512
	 * @see org.bouncycastle.jcajce.provider.digest.SHA3.Digest512
	 * @since Java9
	 */
	SHA3_512("SHA3-512", 512),

	/**
	 * <a href="https://en.wikipedia.org/wiki/SM3_(hash_function)">SM3</a>
	 *
	 * @see org.bouncycastle.jcajce.provider.digest.SM3.Digest
	 */
	SM3("SM3", 256),
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
			return java.security.MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException exception) {
			throw new IllegalStateException("Could not find MessageDigest with algorithm: " + algorithm, exception);
		}
	}
}
