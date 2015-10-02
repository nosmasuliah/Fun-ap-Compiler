package ast;

import lexer.DataType;


/**
 * @author samy
 *
 */
public class Declaration extends Expr {
	
	Expr defaultVal;
	Id declaredId;
	public Declaration(Id declaredId) {
		super(declaredId.op,declaredId.type);
		
		this.declaredId=declaredId;
		
		defaultVal=null;
			}
	
	public Declaration(Id declaredId,Expr defaultVal) {
		super(declaredId.op, declaredId.type);
		
		this.defaultVal=defaultVal;
		this.declaredId=declaredId;
		if(declaredId instanceof FunctionDeligate) this.type=DataType.Function; 
	}
	
	public Declaration() {
		super(null, null);
		
	}
	
	
	
	@Override
	public String toString() {
		StringBuffer buf=new StringBuffer();
		

		
		if(declaredId.isFreeVar){
			buf.append("final ");
			buf.append(declaredId.type.toString());
			buf.append("[] ");
			buf.append(declaredId.op);
			buf.append(" = ");
			if(defaultVal==null){
				buf.append("new "+declaredId.type+"[1]");
			}
			else{
				buf.append("{ " +defaultVal.toString()+ " }");
			}
		}else{
			
			buf.append(type.toString()+" ");
			buf.append(this.op);
			if(defaultVal!=null){
				buf.append(" = ");
				buf.append(defaultVal.toString());
			}
		}
		
		buf.append(";");
		return buf.toString();
	}



	public static final Declaration NullDecl=new Declaration();
	
	@Override
	public 	boolean containsNestedFunc() {
		if(this.defaultVal!=null && this.defaultVal.containsNestedFunc()) return true;
		return false;
	}

}
