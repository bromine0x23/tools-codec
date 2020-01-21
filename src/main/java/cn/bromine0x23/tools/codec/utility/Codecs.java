package cn.bromine0x23.tools.codec.utility;

import cn.bromine0x23.tools.codec.codecs.*;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@UtilityClass
public class Codecs {

	private static final Map<String, Codec> IMPLEMENTS = new LinkedHashMap<>();

	static {
		put(new Base64Codec());
		put(new URLCodec());
		put(new MIMECodec());
		put(new HexCodec());
		put(new DigestCodec());
		put(new StringSecurityCryptoCodec());
		put(new Base32Codec());
	}

	public static Map<String, Codec> getCodecsAsMap() {
		return Collections.unmodifiableMap(IMPLEMENTS);
	}

	public static Collection<Codec> getCodecs() {
		return Collections.unmodifiableCollection(IMPLEMENTS.values());
	}

	public static Codec get(String id) {
		return IMPLEMENTS.get(id);
	}

	private static void put(Codec codec) {
		IMPLEMENTS.put(codec.getId(), codec);
	}
}
