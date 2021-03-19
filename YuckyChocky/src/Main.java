import java.util.Scanner;

public class Main {

    public static void main(String[]args){
        Scanner s=new Scanner(System.in);
        System.out.println("\n\n-----------------------\nGame Works/looks best at size 800x1000 but can be played looking okay at any 4/5 width/height ratio, may look bad if ran with strange sizes");
        System.out.print("Enter the width(pixels) that you wish to play with:  ");
        int width=s.nextInt();
        System.out.print("Enter the height(pixels) that you wish to play with: ");
        int height=s.nextInt();
        System.out.println("Select a size from the menu: \n1. 4x4\n2. 8x8\n3. 16x16\n4.256x256");
        int choice=-1;
        int c=s.nextInt();
        if(c==1)
            choice=4;
        if(c==2)
            choice=8;
        if(c==3)
            choice=16;
        if( c==4)
            choice=256;

        Frame f=new Frame(choice,width,height);
        while(true){
            if(f.getP().isReset())
                f=new Frame(choice,width,height);
        }
    }
}
