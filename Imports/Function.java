 interface Function_ extends Serializable{}

   interface Function0<T> extends Function_{
 	public T apply()throws Exception;
 }


 interface Function1<F, T> extends Function_{
 	
	 public T apply(F param)throws Exception;
 	//@Override
 	//boolean equals(Object obj);
 }

  interface Function2<F1,F2,T>extends Function_{
	  public T apply(F1 param1,F2 param2)throws Exception;
  }
  
  interface Function3<F1,F2,F3,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3)throws Exception;
  }
  
  interface Function4<F1,F2,F3,F4,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4)throws Exception;
  }
  
  interface Function5<F1,F2,F3,F4,F5,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5)throws Exception;
  }
  
  interface Function6<F1,F2,F3,F4,F5,F6,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6)throws Exception;
  }
  
  interface Function7<F1,F2,F3,F4,F5,F6,F7,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7)throws Exception;
  }
  
  interface Function8<F1,F2,F3,F4,F5,F6,F7,F8,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7,F8 param8)throws Exception;
  }
  
  interface Function9<F1,F2,F3,F4,F5,F6,F7,F8,F9,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7,F8 param8,F9 param9)throws Exception;
  }
  
  interface Function10<F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,T> extends Function_{
	  public T apply(F1 param1, F2 param2,F3 param3,F4 param4,F5 param5,F6 param6,F7 param7,F8 param8,F9 param9,F10 param10)throws Exception;
  }
