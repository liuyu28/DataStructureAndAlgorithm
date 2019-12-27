import java.util.Scanner;

/**
 * @author Liuyu  用数组实现环形队列
 * @version 1.0
 * @date 2019/12/27
 */
public class CircleQueueTest {

    public static void main(String[] args) {
        
        //实际有效数据为3个
        CircleQueue circleQueue = new CircleQueue(4);
        boolean goOn = true;
        while (goOn){
            System.out.println("请输入操作");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()){
                case "size":
                    System.out.println("队列中数据大小为："+circleQueue.size()+"个");
                    break;
                case "get" :
                    try {
                        System.out.println(circleQueue.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "getHead" :
                    try {
                        System.out.println(circleQueue.getHead());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show" :
                    circleQueue.show();
                    break;
                case  "add" :
                    System.out.println("请输入要添加的整数");
                    scanner = new Scanner(System.in);
                    try {
                        circleQueue.add(scanner.nextInt());
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
    
    static class CircleQueue{
        int front;
        
        int rear;
        
        int maxSize;
        
        int[] arr;
        
        public CircleQueue(int size){
            front = 0;
            rear = 0;
            maxSize = size;
            arr = new int[size];
        }
        
        public boolean isEmpty(){
            return rear == front;
        }
        
        public boolean isFull(){
            return (rear + 1) % maxSize == front;
        }
        
        public int size(){
            return (rear + maxSize - front) % maxSize;
        }
        
        public void add(int value) throws Exception {
            if(isFull()){
                throw  new Exception("队列已满，不能添加");
            }
            arr[rear] = value;
            rear = (rear + 1) % maxSize;
            System.out.println("添加成功");
        }
        
        public int get() throws Exception {
            if(isEmpty()){
                throw  new Exception("队列为空，不能取数");
            }
            
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }
        
        public int getHead() throws Exception {
            if(isEmpty()){
                throw  new Exception("队列为空，不能取数");
            }

            return arr[front];
        }
        
        public void show(){
            if(isEmpty()){
                System.out.println("队列中没有数据");
            }else{
                for(int i = front,size = size();i<front+size;i++){
                    System.out.printf("arr[%d]=%d",i % maxSize,arr[i%maxSize]);
                    System.out.println();
                }
            }
        }
        
    }
}
