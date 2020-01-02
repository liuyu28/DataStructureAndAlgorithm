package stack;

import java.util.Scanner;

/**
 * @author Liuyu  数组实现栈
 * @version 1.0
 * @date 2020/1/2
 */
public class ArrayStackTest {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        boolean goOn = true;
        while (goOn){
            System.out.println("请输入操作");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()){
                case "size":
                    System.out.println("队列中数据大小为："+stack.size()+"个");
                    break;
                case "pop" :
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show" :
                    stack.show();
                    break;
                case  "push" :
                    System.out.println("请输入要添加的整数");
                    scanner = new Scanner(System.in);
                    try {
                        stack.push(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    System.out.println("退出");
                    goOn = false;
                    break;
                default:
                    System.out.println("不支持的操作");
            }

        }
    }
}

class ArrayStack{
    private int top;//指向栈顶
    
    private int[] arr;//存储源宿
    
    private int size;//栈的大小

    public ArrayStack(int size) {
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    /**
     * 是否满 
     * @return
     */
    public boolean isFull(){
        return top == size -1;
    }

    /**
     * 是否空
     * @return
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        //后进的先出，从栈顶开始
        if(isEmpty()){
            throw new RuntimeException("栈空~~");
        }
        int val = arr[top];
        top --;
        return val;
    }
    
    
    public void push(int val){
        if(isFull()){
            System.out.println("栈满~~");
            return;
        }
        top ++;
        arr[top] = val;
    }
    
    public void show(){
        if(isEmpty()){
            System.out.println("栈空~~");
        }
        for (int i = top;i>=0;i--){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    
    public int size(){
        return top+1;
    }
}
