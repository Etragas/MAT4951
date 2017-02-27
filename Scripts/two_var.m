function two_var_fun = two_var(x)
two_var_fun= @(a11,a21,a31,a41)eval(x);

min_var = @(v) two_var_fun(v(1),v(2),v(3),v(4));

v = [1,1,1,1]; 
a = [0,0,0,0];
fminsearch(min_var,v)