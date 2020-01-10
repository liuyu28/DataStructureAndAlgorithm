package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Liuyu
 * @version 1.0 中缀表达式转逆波兰表达式并计算
 * @date 2020/1/10
 */
public class ReversePolishTest {

    public static void main(String[] args) {
        char c = '.';
        byte b =(byte)c;
        System.out.println(b);//ASCII码
        ReversePolish reversePolish = new ReversePolish();
        //String exp = "10 +3 /2.0+(6.7-1)";
        String exp = "1+((2+3)*4)-5";

        List<String> strings = reversePolish.dealExpression(exp);
        System.out.println(strings);

        //([1-9]\d*\.?\d*)|(0\.\d*[1-9])
        Stack<String> reversePolishExp = reversePolish.changeToReversePolishExp(strings);

       float result = reversePolish.calculate(reversePolishExp);


        System.out.println(result);
    }
    
    
    
}

class ReversePolish{

    public  List<String> dealExpression(String exp){
        List<String> list = new ArrayList<>();
        for(int i = 0;i < exp.length();i++){
            String unitS = "";
            char s = ' ';
            if(i < exp.length()){
                s = exp.substring(i,i+1).charAt(0);
            }
            int n = s - 48;
            if(n >=0 && n <= 9){
                unitS += s;
                while (i+1 < exp.length() ){
                    s = exp.substring(i+1,i+2).charAt(0);
                    n = s - 48;
                    if(n >=0 && n <= 9  || s-46==0){
                        unitS += s;
                        i++;
                        
                    }else{
                        break;
                    }
                }
                list.add(unitS);
            }else if(s - 32 == 0){
                continue;
            }else{
                list.add(unitS+s);
            }
        }

        return list;
    }

    /**
     * 需要两个栈，s1为符号栈，s2为数栈
     * 1、从左往右扫描中缀表达式
     * 2、若是数字，直接入栈s2
     * 3、若是符号：
     * （1）是“（”，直接s1；
     * （2）是“）”，反复将s1栈顶弹出，压入s2，直到s1栈顶为“（”；
     * （3）若是运算符：
     * （a）s1为空，或s1栈顶为"(",或优先级大于s1栈顶运算符，直接入s1；
     *       （b）否则，反复将s1栈顶弹出，压入s2，直到s1栈顶为“（”或s1栈空;
     * 4、表达式扫描完，将s1里的元素依次弹出，压入s2
     * 5、s2的逆序即为逆波兰表达式
     */
    public Stack<String> changeToReversePolishExp(List<String> strings) {
        
        Stack<String> operStack = new Stack<>();
        Stack<String> midStack = new Stack<>();

        for(String elem : strings){
            if(elem.matches("(\\d*\\.?\\d*)")){
                //是数字
                midStack.push(elem);
            }else if(elem.equals("(")){
                operStack.push(elem);
            }else if(elem.equals(")")){
                while (!operStack.peek().equals("(")){
                    midStack.push(operStack.pop());
                }
                operStack.pop();
            }else if(!operStack.isEmpty() && getPriority(elem) <= getPriority(operStack.peek())){
                while (!operStack.isEmpty() && !operStack.peek().equals("(")){
                    midStack.push(operStack.pop());
                }
                operStack.push(elem);
            }else{
                operStack.push(elem);
            }
        }
        
        while (!operStack.isEmpty()){
            midStack.push(operStack.pop());
        }
        
        Stack<String> finalStack = new Stack<>();
        while(!midStack.isEmpty()){
            finalStack.push(midStack.pop());
        }
        return finalStack;
    }
    
    public int getPriority(String elem){
        if(elem.equals("+")   || elem.equals("-") ){
            return 0;
        }else if(elem.equals("*")  || elem.equals("/") ){
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * 逆波兰表达式计算：
     * 从左到右扫描逆波兰表达式，遇到数字直接入栈，遇到运算符，
     * 依次从栈取出两个运算符计算后将结果再入栈，直到表达式扫描完毕，最后栈里的数字就是逆波兰表达式的计算
     * @param reversePolishExp
     * @return
     */
    public float calculate(Stack<String> reversePolishExp) {
        Stack<Float> re = new Stack<>();
            
            while (!reversePolishExp.isEmpty()){
                String elem = reversePolishExp.pop();
                if(elem.matches("(\\d*\\.?\\d*)")){
                    re.push(Float.parseFloat(elem));
                }else{
                    float a = re.pop();
                    float b = re.pop();
                    if(elem.equals("+")){
                        re.push(a+b);
                    }else if(elem.equals("-")){
                        re.push(b-a);
                    }else if(elem.equals("*")){
                        re.push(a*b);
                    }else if(elem.equals("/")){
                        re.push(b/a);
                    }
                }
            }
           
        return re.pop();
    }
    
}
