/**
 * @author Liuyu  稀疏数组
 * @version 1.0
 * @date 2019/12/27
 */
public class SparseArrayTest {

    public static void main(String[] args) {
        //创建一个二维数组
        int[][] twoDiArr = new int[5][5];
        //赋几个的值，其他为默认为0
        twoDiArr[0][3] = 10;
        twoDiArr[1][4] = 7;
        twoDiArr[3][1] = 9;
        twoDiArr[3][4] = 3;
        twoDiArr[4][1] = 8;
        //显示二维数组
        System.out.println("原数组为：");
        for(int arr[] : twoDiArr){
            for (int i: arr) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
        
        //二维数组转稀疏数组
        //遍历二维数组，找到不为0值的数值个数
        
        int count = 0;
        for(int arr[] : twoDiArr){
            for (int i: arr) {
                if(i != 0){
                    count ++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparseArray = new int[count+1][3];
        sparseArray[0][0] = twoDiArr.length;
        sparseArray[0][1] = twoDiArr[0].length;
        sparseArray[0][2] = count;
        
        //将二维数组中的不为0值，放入稀疏数组中
        int depth = 0;
        for(int i = 0;i < twoDiArr.length;i ++){
            for (int j = 0;j< twoDiArr[i].length;j++) {
                if(twoDiArr[i][j] != 0){
                    depth ++;
                    sparseArray[depth][0] = i;
                    sparseArray[depth][1] = j;
                    sparseArray[depth][2] = twoDiArr[i][j];
                }
            }
        }

        System.out.println("稀疏数组为：");
        //展示稀疏数组
        for(int arr[] : sparseArray){
            for (int i: arr) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
        
        //稀疏数组转二维数组
        System.out.println("还原后二维数组是");
        int[][] newArr = new int[sparseArray[0][0]][sparseArray[0][1]];
        
        for (int i = 1;i<sparseArray.length;i++){
             newArr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        for(int arr[] : twoDiArr){
            for (int i: arr) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
    }
    
    
}
