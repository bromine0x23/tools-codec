package cn.bromine0x23.tools.codec.codecs;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod", "squid:S5344"})
public class StringSecurityCryptoCodec extends CodecWithVariants<PasswordEncoder> {

	private static final String VARIANT_ID_NOOP     = "noop";
	private static final String VARIANT_ID_BCRYPT   = "bcrypt";
	private static final String VARIANT_ID_SCRYPT   = "scrypt";
	private static final String VARIANT_ID_PBKDF2   = "pbkdf2";
	private static final String VARIANT_ID_ARGON2   = "argon2";
	private static final String VARIANT_ID_MD5      = "md5";
	private static final String VARIANT_ID_SHA_1    = "sha-1";
	private static final String VARIANT_ID_SHA_256  = "sha-256";
	private static final String VARIANT_ID_STANDARD = "standard";
	private static final String VARIANT_ID_LDAP     = "ldap";

	private static final PasswordEncoder DEFAULT  = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	private static final PasswordEncoder NOOP     = NoOpPasswordEncoder.getInstance();
	private static final PasswordEncoder BCRYPT   = new BCryptPasswordEncoder();
	private static final PasswordEncoder SCRYPT   = new SCryptPasswordEncoder();
	private static final PasswordEncoder PBKDF2   = new Pbkdf2PasswordEncoder();
	private static final PasswordEncoder ARGON2   = new Argon2PasswordEncoder();
	private static final PasswordEncoder MD5      = new MessageDigestPasswordEncoder("MD5");
	private static final PasswordEncoder SHA_1    = new MessageDigestPasswordEncoder("SHA-1");
	private static final PasswordEncoder SHA_256  = new MessageDigestPasswordEncoder("SHA-256");
	private static final PasswordEncoder STANDARD = new StandardPasswordEncoder();
	private static final PasswordEncoder LDAP     = new LdapShaPasswordEncoder();

	public StringSecurityCryptoCodec() {
		super(createVariants());
	}

	@Override
	public String getId() {
		return "spring-security-crypto-password";
	}

	@Override
	public String getName() {
		return "Spring Security Crypto PasswordEncoder";
	}

	@Override
	public boolean supportEncode() {
		return true;
	}

	@Override
	protected String doEncode(PasswordEncoder passwordEncoder, String input) {
		return passwordEncoder.encode(input);
	}

	@Override
	protected PasswordEncoder getDefaultVariant() {
		return DEFAULT;
	}

	@Override
	protected PasswordEncoder getVariant(String variantId) {
		switch (variantId) {
			case VARIANT_ID_NOOP:
				return NOOP;
			case VARIANT_ID_BCRYPT:
				return BCRYPT;
			case VARIANT_ID_SCRYPT:
				return SCRYPT;
			case VARIANT_ID_PBKDF2:
				return PBKDF2;
			case VARIANT_ID_ARGON2:
				return ARGON2;
			case VARIANT_ID_MD5:
				return MD5;
			case VARIANT_ID_SHA_1:
				return SHA_1;
			case VARIANT_ID_SHA_256:
				return SHA_256;
			case VARIANT_ID_STANDARD:
				return STANDARD;
			case VARIANT_ID_LDAP:
				return LDAP;
			default:
				return null;
		}
	}

	private static List<Variant> createVariants() {
		List<Variant> variants = new ArrayList<>();
		variants.add(Variant.builder().id(VARIANT_ID_NOOP).name("NoOp").build());
		variants.add(Variant.builder().id(VARIANT_ID_BCRYPT).name("BCrypt").build());
		variants.add(Variant.builder().id(VARIANT_ID_SCRYPT).name("SCrypt").build());
		variants.add(Variant.builder().id(VARIANT_ID_PBKDF2).name("PBKDF2").build());
		variants.add(Variant.builder().id(VARIANT_ID_ARGON2).name("Argon2").build());
		variants.add(Variant.builder().id(VARIANT_ID_MD5).name("MD5").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_1).name("SHA-1").build());
		variants.add(Variant.builder().id(VARIANT_ID_SHA_256).name("SHA-256").build());
		variants.add(Variant.builder().id(VARIANT_ID_STANDARD).name("Standard（过时的）").build());
		variants.add(Variant.builder().id(VARIANT_ID_LDAP).name("LDAP").build());
		return variants;
	}
}
