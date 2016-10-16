/**
 * Created by robin on 2016/10/12.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        String a =  "b" ;
        String b =  "b" ;

        System.out.print( a == b);

        String c = "d" ;
        String d = new String( "d" ).intern() ;
        System.out.println( c == d);
    }
}
