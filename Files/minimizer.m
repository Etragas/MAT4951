function min_params = min_params(x);
min_fun= @(a11,a21,a31,a41)eval(x);

min_var = @(v) min_fun(v(1),v(2),v(3),v(4));

v = [1,1,1,1]; 
a = [0,0,0,0];
min_params = fminsearch(min_var,v);
end