package bz.cohors.bitcoin.pars;

import java.math.BigInteger;

import com.google.bitcoin.core.Block;
import com.google.bitcoin.core.StoredBlock;
import com.google.bitcoin.store.BlockStore;
import com.google.bitcoin.store.BlockStoreException;

public class Difficulty {

	public final long minDifficultyByDesign;
	
	public Difficulty(long minDifficulty)
	{
		minDifficultyByDesign = minDifficulty;
	}
	
	public long nextBlockDifficulty(
			long desiredTimespan,
			BlockStore store, 
			StoredBlock lastBlk
	)
	{
		StoredBlock prevBlk;
    try {
	    prevBlk = lastBlk.getPrev(store);
    } catch (BlockStoreException e) {
    	return minDifficultyByDesign;
    }
		if (prevBlk == null)
			return minDifficultyByDesign;
		
		Block lastHeader = lastBlk.getHeader();
		Block prevHeader = prevBlk.getHeader();
		long actualTimespan = (lastHeader.getTime().getTime() 
				- prevHeader.getTime().getTime()) / 1000;
		
    {
      //Amplitude Filter by daft27
      actualTimespan = desiredTimespan 
        + (actualTimespan - desiredTimespan)/8;
    
      //Guts of DigiShield Retarget
      if (actualTimespan < desiredTimespan * 3/4)
        actualTimespan = desiredTimespan * 3/4;
      
      if (actualTimespan > desiredTimespan * 3/2) 
        actualTimespan = desiredTimespan * 3/2;
    }

    actualTimespan = Math.max(actualTimespan, 1);

    // Retarget
    BigInteger lastBlockDifficulty = 
    	lastHeader.getDifficultyTargetAsInteger();
    BigInteger bnNew = lastBlockDifficulty;
    bnNew = bnNew.multiply(BigInteger.valueOf(actualTimespan));
    bnNew = bnNew.divide(BigInteger.valueOf(desiredTimespan));
  
    BigInteger mdbd = BigInteger.valueOf(minDifficultyByDesign);
    if (bnNew.compareTo(mdbd) > 0)
      bnNew = mdbd;
		
		return mdbd.longValue();
	}

	public long nextBlockDifficulty(

}

	
