 interface Function_{}

   interface Function0<T> extends Function_{
 	public T apply();
 }


 interface Function1<F, T> extends Function_{
 	
	 public T apply(F param);
 	//@Override
 	//boolean equals(Object obj);
 }

  interface Function2<F1,F2,T>extends Function_{
	  public T apply(F1 param1,F2 param2);
  }
  
  interface Function3<F1,F2,F3,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3);
  }
  
  interface Function4<F1,F2,F3,F4,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4);
  }
  
  interface Function5<F1,F2,F3,F4,F5,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5);
  }
  
  interface Function6<F1,F2,F3,F4,F5,F6,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6);
  }
  
  interface Function7<F1,F2,F3,F4,F5,F6,F7,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7);
  }
  
  interface Function8<F1,F2,F3,F4,F5,F6,F7,F8,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7,F8 param8);
  }
  
  interface Function9<F1,F2,F3,F4,F5,F6,F7,F8,F9,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7,F8 param8,F9 param9);
  }
  
  interface Function10<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7,F8 param8,F9 param9,F10 param10);
  }
