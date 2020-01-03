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
                case "size":
                    System.out.println("栈中数据大小为："+stack.size()+"个");
                    break;
                case "pop" :
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show" :
                    System.out.println("栈遍历：");
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

class OneDirectionLinkedListStack{
    //指向头结点，不能动
    private Node first;  
    
    public OneDirectionLinkedListStack(){
        first = new Node(-1);
    }
    
    public boolean isEmpty(){
        return null == first.getNext();
    }
    
    public void push(int value){
        //找到最后的节点
        Node temp = first;
        
        //遍历找到最后一个元素
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
        if(isEmpty()){
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
    
    public int size(){
        int size = 0;
        //头节点不能移动，使用临时节点
        Node temp = first;
        while (null != temp.getNext()){
            size ++;
            temp = temp.getNext();
        }
        return size;
    }
    
    //遍历栈元素,不改变原链表的顺序
    public void show(){
        if(isEmpty()){
            System.out.println("栈空~~");
            return;
        }
        //栈先进后出的特点，遍历顺序是链表从后先前
        //需要先反转
        Node head = new Node(-1);//新链表的头节点

        Node temp = first;//遍历原节点时用的临时节点
        Node node;
        while (null != temp.getNext()){
            temp = temp.getNext();
            node = new Node(temp.getNo());
            node.setNext(head.getNext());
            head.setNext(node);
        }

        //遍历反向后的链表
        temp = head;
        while (null != temp.getNext()){
            temp = temp.getNext();
            System.out.printf("%d\n",temp.getNo());
        }
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
