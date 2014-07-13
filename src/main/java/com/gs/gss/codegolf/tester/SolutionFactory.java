package com.gs.gss.codegolf.tester;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gs.gss.codegolf.solution.Solution;
import com.gs.gss.codegolf.solution.mcgale;

public class SolutionFactory {

	private SolutionFactory(){};
	
	public static Set<Solution> getSolutions() {
		Set<Solution> solutions = new HashSet<Solution>();
		solutions.add(new mcgale());
		
		return solutions;
	}
}
