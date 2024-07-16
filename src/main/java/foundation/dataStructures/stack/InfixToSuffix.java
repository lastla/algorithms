package foundation.dataStructures.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 中缀表达是转后缀表达式：
 * 1.遇到非运算符 直接拼接字符串
 * 2. 遇到运算符 + - * /
 *      它的优先级比栈顶元素高，则入栈
 *      否则把栈里优先级 >= 它的都出栈，它再入栈
 * 3. 便利完成，栈里剩余运算符依次出栈
 * 4.遇到（）
 *      -（直接入栈，并且优先级小于加减乘除
 *      -）把左括号之前的全部出栈
 */
public class InfixToSuffix {

    public static void main(String[] args) {
        System.out.println(solution("a+b"));
        System.out.println(solution("a+b-c"));
        System.out.println(solution("a+b*c"));
        System.out.println(solution("a*b-c"));
        System.out.println(solution("(a+b)*c"));
        System.out.println(solution("(a+b*c-d)*e"));
        System.out.println(solution("a*(b+c)"));
        Queue queue = new LinkedList();

    }

    static int priority(char c){
        if(c=='+' || c=='-'){
            return 1;
        } else if (c=='*' || c=='/') {
            return 2;
        } else if (c=='(') {
            return 0;
        } else{
            throw new IllegalArgumentException("非法的运算符 "+c);
        }
    }

    static String solution(String exp){
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for (int i = 0; i <exp.length() ; i++) {
            char c = exp.charAt(i);
            if(c=='+' ||c=='-' ||c=='*' ||c=='/'){
                if(stack.isEmpty()){
                    stack.push(c);
                }else{
                    if(priority(c)>priority(stack.peek())){
                        stack.push(c);
                    }else{
                        while (!stack.isEmpty()&&priority(stack.peek())>=priority(c)){
                            sb.append(stack.pop());
                        }
                        stack.push(c);
                    }
                }
            } else if (c=='(') {
                stack.push(c);
            } else if (c==')') {
                while (stack.peek()!='('){
                    sb.append(stack.pop());
                }
                stack.pop();
            } else {
                sb.append(c);
            }
        }
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
