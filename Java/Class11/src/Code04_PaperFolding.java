public class Code04_PaperFolding {

    public static void process(int i,int N,boolean T)
    {
        if(i > N) return;

        process(i+1,N,true);
        System.out.print(T ? "凹" : "凸");
        process(i+1,N,false);
    }

    public static void main(String[] args)
    {
        process(0,2,true);
    }
}
