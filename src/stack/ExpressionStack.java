package stack;

/**
 * @author Liuyu  数组栈实现中缀表达式的计算 计算只包括 + - * \
 * @version 1.0
 * @date 2020/1/3
 */
public class ExpressionStack {
    public static void main(String[] args) {
        ArrayStack2 operStack = new ArrayStack2(10);
        ArrayStack2 numStack = new ArrayStack2(10);
        
        String expression = "700*2-30+3-40";
        
        //遍历表达式
        //1、若是数字，入数栈
        //2、若是符号，先判断符号栈是否为空
        //2.1、为空，直接入符号栈
        //2.2、不为空，取符号栈顶，判断当前符号与栈顶符号的优先级，若小，数栈弹出两个数，符号栈弹出一个符号进行计算
        //并把计算结果入数栈，再把运算符入符号栈，若大，直接入符号栈
        //当符号栈为空，说明整个表达式计算结束，取出数栈中的数（此时数栈只有一个数据），为表达式结果
        
        int index = 0;
        int result = 0 ;
        int num1 = 0;
        int num2 = 0;
        String temp = "";
        char ch;
        while (true){
            ch = expression.substring(index, index + 1).charAt(0);
            if(operStack.isOper(ch)){
                //是运算符
                if(!operStack.isEmpty()){
                    //运算符栈不为空
                    //判断当前符号与栈顶符号的优先级
                    if(operStack.operPriority(ch) > operStack.operPriority(operStack.peek())){
                        //当前符号 大，直接入符号栈
                        operStack.push(ch);
                    }else{
                        //当前符号 小
                        //数栈弹出两个数，符号栈弹出一个符号进行计算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        result = numStack.operation(num1,num2,operStack.pop());
                        numStack.push(result);
                        operStack.push(ch);
                    }
                }else{
                    //运算符栈为空
                    //直接入符号栈
                    operStack.push(ch);
                }
            }else{
                //是数字
                //多位数字的处理！！！
                temp += ch;
                
                //往后再取
                boolean goOn = true;
                while(goOn){
                    if(index >= expression.length() - 1){
                        //若已经是最后一位，不再往后取
                       goOn = false;
                    }else{
                        ch = expression.substring(index+1,index+2).charAt(0);
                        if(!operStack.isOper(ch)){
                            temp += ch;
                            index ++;
                        }else{
                            //不是数字，不再往后走
                            goOn = false;
                        }
                    }
                }
                numStack.push(Integer.parseInt(temp));
                temp = "";
            }
            index ++;
            //用大于判断是否遍历结束
            if(index > expression.length() - 1){
                //说明表达式遍历结束
                break;
            }
        }
        
        //如果已遍历完
        while(!operStack.isEmpty()){
            num1 = numStack.pop();
            num2 = numStack.pop();
            result = numStack.operation(num1,num2,operStack.pop());
            numStack.push(result);
        }

        result = numStack.pop();
        System.out.printf("%s = %d\n",expression,result);
        
    }
}


class ArrayStack2 {
    private int top;//指向栈顶

    private int[] arr;//存储元素

    private int size;//栈的大小

    public ArrayStack2(int size) {
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    /**
     * 是否满
     *
     * @return
     */
    public boolean isFull() {
        return top == size - 1;
    }

    /**
     * 是否空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    public int peek(){
        return arr[top];
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        //后进的先出，从栈顶开始
        if (isEmpty()) {
            throw new RuntimeException("栈空~~");
        }
        int val = arr[top];
        top--;
        return val;
    }


    public void push(int val) {
        if (isFull()) {
            System.out.println("栈满~~");
            return;
        }
        top++;
        arr[top] = val;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("栈空~~");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int size() {
        return top + 1;
    }
    
    //判断是否是运算符号
    public boolean isOper(int oper){
        return '+' == oper || '-' == oper || '*' == oper || '/' == oper;
    }
    
    //为+ - * /确定等级
    public int operPriority(int oper){
        if('+' == oper || '-' == oper){
            return 0;
        }else if('*' == oper || '/' == oper){
            return 1;
        }else{
            return -1;
        }
    }
    
    //计算
    public int operation(int num1,int num2,int oper){
        int result = 0;
        switch (oper){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;

        }
        return result;
    }
}
