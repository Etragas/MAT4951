Function Overview:
This tool serves to minimize a function in written in mathematica, using matlab libraries. The particular libraries will be generalized in the future.

It maintains responsibility for translating a mathematica file in to a matlab one, and then minimizing it.

User Files:
In the Files directory, there are two relevant files:
1. fun_to_min.m is a Mathematica file, which encodes the function in question. Currently the tool only support simple expressions.

2. minimizer.m is a Matlab file, which minimizes the function.
It takes as a parameters one string of the form:
‘@(var1,var2,…,varn)2*var1+3*var2…’

At the moment, the tool works for arbitrary variables, but only for variables. This will be resolved soon.

How to use:
1.Place your file in the Files directory and name it “fun_to_min.m”.
	An included example of fun_to_min is available.
2. Check the minimizer and make any adjustments you deem appropriate.
3. On Windows hit run.bat on osx his run.
