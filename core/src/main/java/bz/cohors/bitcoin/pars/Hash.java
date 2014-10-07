// -*-coding: mule-utf-8-unix; fill-column: 58; -*-

/**
 * @file
 * Altcoin parameters: hash function.
 *
 * @author Sergei Lodyagin
 */

package bz.cohors.bitcoin.pars;

import static com.hashengineering.crypto.X11.x11Digest;
import static bz.cohors.bitcoin.hash.Brittcoin.brittcoinDigest;

public class Hash
{
	abstract public class Fun 
	{
	  abstract public byte[] hash(
	    byte[] input, 
	    int offset, 
	    int length
	  ); 
	  
	  public byte[] hash(byte[] input)
	  {
		  return hash(input, 0, input.length);
	  }
	}
	
	class X11 extends Fun
	{
	  public byte[] hash(
	    byte[] input, 
	    int offset, 
	    int length
	  )
	  {
	    return x11Digest(input, offset, length);
	  }
	}
	
	class Brittcoin extends Fun
	{
      public byte[] hash(
        byte[] input, 
        int offset, 
		int length
      )
      {
        byte [] buf = new byte[length];
        for(int i = 0; i < length; ++i)
        {
          buf[i] = input[offset + i];
        }
        return brittcoinDigest(buf);
      }
	}
	
    private Hash() {} // a singleton
	
	private static class Holder
	{
	  private static final Hash the_hash = new Hash();
	  private static final Fun the_fun = the_hash.new Brittcoin();
	}
	
	public static Fun instance()
	{
	    return Holder.the_fun;
	}

};