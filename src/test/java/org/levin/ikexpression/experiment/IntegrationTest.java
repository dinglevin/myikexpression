package org.levin.ikexpression.experiment;

import org.junit.Ignore;
import org.junit.Test;
import org.levin.ikexpression.ExpressionEvaluator;

import java.util.ArrayList;

/**
 * 整合测试
 *
 * @author 林良益
 */
@Ignore
public class IntegrationTest {
    /**
     * 括号优先级测试
     */
    @Test
    public void testPriority() {
        System.out.println("testPriority");

        ArrayList<String> expressions = new ArrayList<String>();
        expressions.add("-(10 + (23 - 3) * (4 / 5)) % 6");
        expressions.add("true || false && false ? false : true ");//false
        expressions.add("(true || false) && false \r\n? false : true ");//true
        expressions.add("!true || !false && (false ? \r\nfalse : true) ");//true
        expressions.add("!(true || !false && \r\n(false ? false : true)) ");//false
        expressions.add("!(true || !false && false ? false : true) ");//true
        expressions.add("[2009-08-08] + false + 123 \r\n+ \"a String\" + null + 1234 + 12345.88 + true");
        expressions.add("[2009-08-08] + false + 123 + \"a String\" + null \r\n+ (1234 + 12345.88) + true");

        for (String expression : expressions) {
            System.out.println("expression : " + expression);
            System.out.println(ExpressionEvaluator.compile(expression));
            Object result = ExpressionEvaluator.evaluate(expression);
            System.out.println("result = " + result);
            System.out.println();
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------testPriority over------------------");
        System.out.println("----------------------------------------------------");
    }

    /**
     * 函数表达式混合测试
     */
    @Test
    public void testMess() {
        System.out.println("testMess");

        ArrayList<String> expressions = new ArrayList<String>();
        expressions.add("$CONTAINS(\"aabbcc\" + \"abc\",\"abc\")");
        expressions.add("$CONTAINS(\"aabbcc\" + \"abc\",\"abc\") ? \r\n$STARTSWITH(\"abcbcc\",\"abc\") : \r\n$ENDSWITH(\"aabcbcc\",\"abc\") ");
        expressions.add("$CONTAINS(\"aabbcc\" , \"abc\") ?\r\n $STARTSWITH(\"abcbcc\",\"abc\") \r\n: $ENDSWITH(\"aabcbcc\",\"abc\") ");
        expressions.add("$CALCDATE([2008-03-01],\r\n0,0,-(10 + (23 - 3) * (4 / 5)) % 6,0,0,0)");

        for (String expression : expressions) {
            System.out.println("expression : " + expression);
            System.out.println(ExpressionEvaluator.compile(expression));
            Object result = ExpressionEvaluator.evaluate(expression);
            System.out.println("result = " + result);
            System.out.println();
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------testMess over------------------");
        System.out.println("----------------------------------------------------");
    }
}
