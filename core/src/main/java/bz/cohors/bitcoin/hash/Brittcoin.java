package bz.cohors.bitcoin.hash;

import java.security.GeneralSecurityException;
import java.util.Date;

import com.lambdaworks.crypto.SCrypt;

/**
 * 
 * @author Sergei Lodyagin
 *
 */
public class Brittcoin {

	/**
	 * 
	 * Scrypt parameters - N, r, p
	 */
	static class NFactor {
		public int N;
		public int r;
		public int p;
		
		NFactor(int N_, int r_, int p_) {
			N = N_;
			r = r_;
			p = p_;
		}
	}

	public static NFactor get_n_factor(Date blockTime) {
		return new NFactor(1024, 8, 1); // TODO
	}
	
	public static byte[] brittcoinDigest(byte[] input) 
	{
		NFactor nf = get_n_factor(new Date());
		try {
			return SCrypt.scrypt(input, input, nf.N, nf.r, nf.p, 32);
		} catch (GeneralSecurityException e) {
			return null;
		}
	}
	
}
