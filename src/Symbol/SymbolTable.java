package Symbol;



import java.util.Hashtable;

import lexer.*;
import ast.Id;


public class SymbolTable {
	
	private Hashtable<Word,Id> table;
	private SymbolTable prev;
	
	public SymbolTable(SymbolTable st) {
		table=new Hashtable<Word,Id>();
		prev=st;
	}
	
	
	
	
	
	public boolean put(Word w,Id i){
		if(get(w)==null){
		table.put(w, i);
		return true;
		}
		return false;
		
	}
	
	public Id get(Word w){
		
		
		for(SymbolTable t=this;t!=null;t=t.prev){
			Id found=t.table.get(w);			
			if(found!=null) return found;
			//else System.out.println(w+" not found in "+t.table);
		}
		
		return null;
	}

	public int size(){
		return table.size();
	}
	
	public void printValue(Word tok){
		//for(SymbolTable t=this;t!=null;t=t.prev){
			System.out.println(table.get(tok));
		//}
	}
	
}
