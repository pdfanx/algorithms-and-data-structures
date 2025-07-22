public class Code06_PrintB {

    public static void print(int num) {
        for(int i = 31;i>=0;i--){
            System.out.print((num & (1<<i))==0 ? "0" : "1");

        }
        System.out.println();
    }

        public static void main(String[] args) {
//            int a = Integer.MAX_VALUE;
//            print(a);
//            // 32位
//            int num = 893928328;
//            print(num);
//            int a = Integer.MIN_VALUE;
//            print(a);
//            int b = 1234466;
//            int a = ~b;
//            print(a);
//            print(b);
            int a=Integer.MIN_VALUE;
            print(a);
            print(a>>1);    //带符号左移
            print(a>>>1);   //无符号左移
            
        }
    }
