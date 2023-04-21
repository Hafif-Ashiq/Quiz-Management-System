import java.util.Scanner;

public class Extraa_Stuff {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name : ");
        String name = "";
//        input.nextLine();
        while (true){
            name = input.nextLine();
            boolean verify = true;
            for (int i=0;i<name.length();i++){
                char ch = name.charAt(i);
                if (Character.isLetter(ch) || (ch == ' '))
                    continue;
                else{
                    System.out.println(name.charAt(i));
                    System.out.println("Enter valid name having letters or spaces only: ");
                    verify = false;
                    break;
                }
            }
            if (verify)
                break;
        }
        System.out.println("Name>>" + name);
    }
}