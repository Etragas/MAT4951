#!/bin/bash
cd "$(dirname "$0")"
java -jar "$(dirname "$0")"out/artifacts/src_jar/src.jar -genPackage "" -genPackageArguments package_args -genKernelArguments "-linkmode launch -linkname"  -genKernel "/Applications/Mathematica.app/Contents/MacOS/MathKernel" -minKernel "/Applications/MATLAB_R2016b.app/bin/matlab" -minKernelArguments "" 


