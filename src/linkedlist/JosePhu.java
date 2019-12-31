package linkedlist;

/**
 * @author Liuyu
 * @version 1.0
 * @date 2019/12/31
 */
public class JosePhu {
    public static void main(String[] args) {
        CircleLinked circleLinked = new CircleLinked();
        
        try {
            circleLinked.createCircleLinked(10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("循环列表元素总个数为 %d",circleLinked.size());
        System.out.println();
        
        System.out.println("循环列表里元素为：");
        circleLinked.showList();

        System.out.println("约瑟夫出队顺序：");
        try {
            circleLinked.josePhu(3,10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class CircleLinked{
    private Node first;
    
    private Node last;
    
    public void createCircleLinked(int size) throws Exception {
        //至少要2个
        if(size < 2){
            throw new Exception("数量不符合要求");
        }
        
        Node node;
        Node temp = null;//保存上一个添加的节点
        for (int i = 1;i < size +1;i++){
            node = new Node(i);
            
            if(i == 1){
                first = node;
                first.setNext(first);
            }else{
                node.setNext(first);
                temp.setNext(node);
            }
            temp = node;
        }
    }
    
    public void showList(){
        if(first == null){
            System.out.println("队列为空");
            return;
        }
        Node temp = first;
        while(true){
            System.out.println(temp.getNo());
            if(temp.getNext() == first){
                //说明是最后一个
                break;
            }
            temp = temp.getNext();
        }
    }
    
    public int size(){
        int count = 0;
        if(first == null){
            return count;
        }
        count ++;
        Node temp = first;
        while (temp.getNext() != first){
            count ++;
            temp = temp.getNext();
        }
        return count;
    }
    
    //找到开始位置
    //约瑟夫  出队：1、保存要删除节点的前一个节点  2、节点删除，修改next阈  3、移动到下一个开始的位置
    public void josePhu(int startNo,int count) throws Exception {
        if(startNo > size()){
            throw new Exception("起始位置超过最大个数");
        }
        
        if(count < 1){
            throw new Exception("标识间隔次数错误");
        }

        //Node start = first;//起始位置
        //先找到起始位置
        for(int i = 1;i< startNo ;i++){
            first = first.getNext();
        }
        Node temp = null;//保存删除的前一个节点位置
        while (true){
            if(first.getNext() == first){
                System.out.printf("%d出列",first.getNo());
                System.out.println();
                first = null;
                break;
            }
            for(int i =1;i < count;i++){
                temp = first;
                first = first.getNext();
            }
            System.out.printf("%d出列",first.getNo());
            System.out.println();

            //移除节点
            first = first.getNext();
            temp.setNext(first);
        }

        showList();
    }
}

class Node{
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}



