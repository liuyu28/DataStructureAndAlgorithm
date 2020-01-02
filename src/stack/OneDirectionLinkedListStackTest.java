package stack;

import java.util.Scanner;

/**
 * @author Liuyu  单向链表实现栈
 * @version 1.0
 * @date 2020/1/2
 */
public class OneDirectionLinkedListStackTest {

    public static void main(String[] args) {
        OneDirectionLinkedListStack stack = new OneDirectionLinkedListStack();
        boolean goOn = true;
        while (goOn){
            System.out.println("请输入操作");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()){
                /*case "size":
                    System.out.println("队列中数据大小为："+stack.size()+"个");
                    break;*/
                case "pop" :
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                /*case "show" :
                    stack.show();
                    break;*/
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

class OneDirectionLinkedListStack{
    //指向头结点，不能动
    private Node first;
    
    private Node top;
    
    
    public OneDirectionLinkedListStack(){
        first = new Node(-1);
    }
    
    public boolean isEmpty(){
        return null == first.getNext();
    }
    public void push(int value){
        //找到最后的节点
        Node temp = first;
        while (true){
            if(null == temp.getNext()){
                //temp是最后一个节点
                break;
            }
            temp = temp.getNext();
        }
        
        Node node = new Node(value);
        temp.setNext(node);
    }
    
    public int pop(){
        //找到最后节点出栈
        Node temp = first;
        if(null == temp.getNext()){
            throw new RuntimeException("栈空~~");
        }
        //保留最后节点的前一个
        Node topBefore = temp;
        temp = temp.getNext();
        while (null != temp.getNext()){
            topBefore = temp;
            temp = temp.getNext();
        }
        topBefore.setNext(null);
        return temp.getNo();
    }
    
}

class Node{
    
    private int no;
    private Node next;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
