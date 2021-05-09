import java.util.ArrayList;

public class testing
{
    private ArrayList<Integer> x;
    
    public testing(ArrayList<Integer> y)
    {
        this.x = y;
    }
    
//    public void change()
//    {
//        ArrayList<Integer> temp = new ArrayList<>();
//
//        for (int i = 0; i < 11; i++)
//            temp.add(i);
//
//        x = temp;
//    }
    
    public static void main(String[] args)
    {
        ArrayList<Integer> arr = new ArrayList<>();
    
        for (int i = 10; i < 14; i++)
            arr.add(i);
    
        testing t = new testing(arr);
    
    
        System.out.println(t.x);
    
        for (int i = 10; i < 14; i++)
            arr.add(i);
        
        System.out.println(t.x);
        
//        t.change();

//        System.out.println(arr);
    }
}
