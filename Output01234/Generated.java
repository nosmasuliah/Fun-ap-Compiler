import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 class outside_adder implements Function0<Function1<Integer, Integer>> { 
public outside_adder(){} 
 
public Function1<Integer, Integer> apply(   ) throws Exception{ 
final Integer[] sum = { 10 };
return new Function1<Integer, Integer>() { 
 public Integer apply(  Integer x ) { 
sum[0] = sum[0]  +  x;
return sum[0];
}
 } 
;
}
 } 

 class outside_compare implements Function0<Function2<Double, Double, Boolean>> { 
public outside_compare(){} 
 
public Function2<Double, Double, Boolean> apply(   ) throws Exception{ 
return new Function2<Double, Double, Boolean>() { 
 public Boolean apply(  Double x, Double y ) { 
return x  ==  y;
}
 } 
;
}
 } 

 class fib implements Function1<Integer, Integer> { 
public fib(){} 
 
public Integer apply(   final Integer n ) throws Exception{ 
final Integer[] a = new Integer[1];
final Integer[] b = new Integer[1];
a[0] = Generated.service.submit( new Callable<Integer>() { 
public Integer call() throws Exception {
return new fib().apply(n  -  1) ;}}).get();;
b[0] = Generated.service.submit( new Callable<Integer>() { 
public Integer call() throws Exception {
return new fib().apply(n  -  2) ;}}).get();;
return a[0]  +  b[0];
}
 } 
public class Generated {
public static ExecutorService service = Executors.newCachedThreadPool();public static void main(String[] args) throws Exception { 
Function_ adder = new outside_adder().apply() ;
Function_ another_adder;
Function_ yet_another_adder;
Integer x;
Boolean a;
Double d = 2.3;
x = (Integer)adder.getClass().getMethod("apply"  , Integer.class).invoke(adder,5) ;
another_adder = adder;
x = (Integer)another_adder.getClass().getMethod("apply"  , Integer.class).invoke(another_adder,60) ;
yet_another_adder = new outside_adder().apply() ;
x = (Integer)yet_another_adder.getClass().getMethod("apply"  , Integer.class).invoke(yet_another_adder,60) ;
System.out.println((Integer)adder.getClass().getMethod("apply"  , Integer.class).invoke(adder,60) );
adder = new outside_compare().apply() ;
System.out.println((Boolean)adder.getClass().getMethod("apply"  , Double.class , Double.class).invoke(adder,1.1,2.2) );
}
}