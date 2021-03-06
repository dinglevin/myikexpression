/**
 * 
 */
package org.levin.ikexpression.operators.define;

import org.levin.ikexpression.exception.IllegalExpressionException;
import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.datameta.Reference;
import org.levin.ikexpression.operators.OperatorExecutor;
import org.levin.ikexpression.operators.Operator;

/**
 * 逻辑非操作
 * @author 林良益，卓诗垚
 * @version 2.0 
 * 2008-09-26
 */
public class Op_NOT implements OperatorExecutor {

	public static final Operator THIS_OPERATOR = Operator.NOT;
	
	/* (non-Javadoc)
	 * @see org.levin.ikexpression.op.OperatorExecutor#execute(org.levin.ikexpression.ExpressionToken[])
	 */
	public Constant execute(Constant[] args) throws IllegalExpressionException {

		if(args == null || args.length != 1){
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "参数个数不匹配");
		}

		Constant first = args[0];
		if(null == first || null == first.getDataValue()){
			//抛NULL异常
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		}
	
		//如果第一参数为引用，则执行引用
		if(first.isReference()){
			Reference firstRef = (Reference)first.getDataValue();
			first = firstRef.execute();
		}
		
		if(BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  first.getDataType()){
			Boolean result = !first.getBooleanValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_BOOLEAN , result);
			
		}else {
			//抛异常
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误");

		}
		
	}

	/* (non-Javadoc)
	 * @see org.levin.ikexpression.op.OperatorExecutor#verify(int, org.levin.ikexpression.ExpressionToken[])
	 */
	public Constant verify(int position, BaseDataMeta[] args)
			throws IllegalExpressionException {

		if(args == null){
			throw new IllegalArgumentException("运算操作符参数为空");
		}
		if(args.length != 1){
			//抛异常
			throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数个数不匹配"
						, THIS_OPERATOR.getToken()
						, position
					);
		}
		
		BaseDataMeta first = args[0];
		if(first == null){
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		}		

		if(BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  first.getDataType()){
			return new Constant(BaseDataMeta.DataType.DATATYPE_BOOLEAN , Boolean.FALSE);
			
		}else {
			//抛异常
			throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误"
					, THIS_OPERATOR.getToken()
					, position
					);

		}		
	}
}
