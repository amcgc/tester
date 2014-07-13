package com.gs.gss.codegolf.solution;

import java.math.BigInteger;

public class mcgale implements Solution {

	@Override
	public int nextPrime(int n) {
		return BigInteger.valueOf(n).nextProbablePrime().intValue() ;
	}

}
