package cn.bromine0x23.tools.codec.codecs;

import org.apache.commons.codec.binary.Hex;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public class HexCodec implements Codec {

	private final Hex hex = new Hex();

	@Override
	public String getId() {
		return "hex";
	}

	@Override
	public String getName() {
		return "16进制";
	}

	@Override
	public boolean supportEncode() {
		return true;
	}

	@Override
	public String encode(String input, String variantId) {
		return new String(hex.encode(input.getBytes()));
	}

	@Override
	public boolean supportDecode() {
		return false;
	}
}
