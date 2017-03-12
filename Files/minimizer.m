function min_params = minimizer(fun)
minimizer= eval(fun)

min_var = @(v) minimizer(v(1),v(2),v(3),v(4));

v = [1,1,1,1]; 
a = [0,0,0,0];
min_params = fmincon(min_var,v,[],[]);
end