
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class quiz_management {

    public static Scanner input = new Scanner(System.in);

    public static File emails = new File("Emails.txt");
    public static File passwords = new File("Passwords.txt");
    public static File designation = new File("Designations.txt");
    public static File information = new File("Information.txt");


    public static File quizzes_folder = new File("Quiz_Questions/");
    public static File short_Questions_folder = new File("Short_Questions/");
    public static File TorF_folder = new File("True_or_False/");
    public static File Long_Questions_folder = new File("Long_Questions/");


    public static FileInputStream email_inputStream;


    public static BufferedReader email_Buff ;

    public static Scanner emails_Sc;

    public static Scanner password_Sc;

    public static Scanner designation_Sc;




    public static void main(String args[]) throws IOException {

            emails.createNewFile();
            passwords.createNewFile();
            designation.createNewFile();
            information.createNewFile();


        try {
            email_inputStream = new FileInputStream(emails);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         email_Buff = new BufferedReader(new InputStreamReader(email_inputStream));

        emails_Sc = new Scanner(emails);
        password_Sc = new Scanner(passwords);
        designation_Sc = new Scanner(designation);
        quizzes_folder.mkdirs();
        short_Questions_folder.mkdirs();
        TorF_folder.mkdirs();
        Long_Questions_folder.mkdirs();

        while (true) {

            System.out.println("\n---------- W E L C O M E   T O   Q U I Z   M A N A G E M E N T   S Y S T E M ----------\n");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">\tEnter 1 to login as Student\n" +
                    ">\tEnter 2 to login as Teacher\n" +
                    ">\tEnter 3 to login as Admin\n" +
                    ">\tEnter 0 to quit Program:");
            System.out.println("Enter your Choice: ");
            int login_input  = 0;
            while (true){
                try {
                    login_input = input.nextInt();
                    if (login_input>=0 && login_input<= 3)
                        break;
                    else System.out.println("Enter valid Number as 1,2,3 or 0 to continue: ");
                }catch (InputMismatchException e){
                    System.out.println("Enter valid Number to continue");
                    String blank = input.next();
                }
            }

            if (login_input == 1) {
                Student();
            } else if (login_input == 2) {
                Teacher();
            } else if (login_input == 3) {
                ADMIN();
            } else if (login_input == 0) {
                break;
            }


        }

    }


    public static void ADMIN() throws IOException {
        while (true) {
            System.out.println("Enter email: ");
            int logged_in = 0;
            String email = input.next();
            if (email.equals("admin@gmail.com")){
                System.out.println("Enter password: ");
                String blank = input.nextLine();
                String pass = input.nextLine();
                if (pass.equals("admin"))
                    logged_in = 3;
            }
            else
                logged_in = login_As(email) ;
            boolean continu = false;
            if (logged_in == 3) {
                while (true){
                    System.out.println("\n--------------- A  D  M  I  N ---------------\n");
                    System.out.println(">\tEnter 1 to view Students Information \n" +
                            ">\tEnter 2 to view Teachers Information \n" +
                            ">\tEnter 3 to add Teacher or Student record \n" +
                            ">\tEnter 4 to edit profiles \n" +
                            ">\tEnter 5 to view Quiz Questions \n" +
                            ">\tEnter 6 to view Examination Questions \n" +
                            ">\tEnter 7 to view Previous Terminal Examinations \n" +
                            ">\tEnter 0 to quit this menu:");
                    System.out.println("Enter your Choice: ");
                    int choice ;
                    while (true){
                        try {
                            choice = input.nextInt();
                            if (choice>=0 && choice<=7)
                                break;
                            else
                                System.out.print("Enter valid number from 0 to 7 to continue: ");
                        }catch (InputMismatchException e){
                            System.out.println("Enter valid input from 0 to 7 to continue: ");
                            String blank = input.next();
                        }
                    }

                    if (choice==0) {
                        continu = true;
                        break;
                    }
                    else if (choice==1){
                        System.out.println("\n--------------- V I E W   S T U D E N T S   I N F O R M A T I O N ---------------\n");
                        viewStudentRecord();

                    }
                    else if (choice==2){
                        System.out.println("\n--------------- V I E W   T E A C H E R S   I N F O R M A T I O N ---------------\n");
                            viewTeacherRecord();
                    }
                    else if (choice==3){
                      // ----------------------------- Add Teacher or Student or Admin Record -----------------
                        add_Teacher_or_Student_or_Admin();
                    }
                    else if (choice==4){
                        System.out.println("\n--------------- E D I T   P R O F I L E   I N F O R M A T I O N ---------------\n");
                        editRecord();
                    }
                    else if (choice==5){
                        System.out.println("\n--------------- V I E W   A L L   Q U I Z   Q U E S T I O N S ---------------\n");
                        viewQuizzes();
                    }
                    else if (choice==6){
                        viewExamQs();
                    }
                    else if (choice==7){
                        System.out.println("\n--------------- V I E W   P R E V I O U S  T E R M I N A L   E X A M I N A T I O N   Q U E S T I O N S ---------------\n");
                        viewTerminal();
                    }
                }
            } else {
                System.out.println("\nEnter 1 if you want to quit to previous menu: \n " +
                        "Else enter 0 to try again..");
                int inp = 0;
                while (true){
                    try {
                        inp = input.nextInt();
                        if (inp==0 || inp ==1)
                            break;
                        else
                            System.out.println("Enter valid input as 0 or 1: ");
                    }catch (InputMismatchException e){
                        System.out.println("Enter valid input as 0 or 1: ");
                        String blank = input.next();
                    }
                }

                if (inp == 1){
                    continu = true;
                }

            }
            if (continu)
                break;
        }
    }


    //--------------------- S T U D E N T ---------------------------------
    public static void Student() throws IOException {
        int choice = 0;
        while (true) {
            System.out.println("Enter email: ");
            String email = input.next();
            int student_present = login_As(email);

            if (student_present == 1) {
                while (true){

                    System.out.println("\n--------------- S T U D E N T ---------------\n");

                    System.out.println(">\tEnter 1 to attempt quiz: \n" +
                            ">\tEnter 2 to view your profile : \n" +
                            ">\tEnter 0 to quit to previous menu: ");
                    System.out.println("Enter your choice: ");
                    int in ;
                    while (true){
                        try {
                            in = input.nextInt();
                            if (in >=0 && in<= 2){
                                break;
                            }
                            else System.out.println("Enter valid Option: ");
                        }
                        catch (InputMismatchException e){
                            System.out.println("Enter valid Option: ");
                            String str = input.next();
                        }
                    }
//                    quizFormation(email);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (in == 1){
                        quizFormation(email);
                    }
                    else if(in == 2){
                        viewPersonalProfile(email);
                    }
                    else if (in == 0)
                        break;


                }
            } else if (student_present == 2) {
                System.out.println("\nYou are not Designated as a STUDENT...\nTRY LOGGING IN AGAIN!!!\n"+
                        "Enter 1 to enter email again!!  \n" +
                                "Or Enter 0 to quit to menu:");
                while (true){
                    try {
                        choice = input.nextInt();
                        if (choice == 0 || choice == 1)
                            break;
                        else
                            System.out.println("Enter valid number as 0 or 1: ");
                    }catch (InputMismatchException e){
                        System.out.println("Enter valid number as 0 or 1: " );
                        String blank = input.next();
                    }
                }

            } else {
                System.out.println("Enter 1 to enter email again!!  \n" +
                        "Or Enter 0 to quit to menu:");
                while (true){
                    try {
                        choice = input.nextInt();
                        if (choice == 0 || choice == 1)
                            break;
                        else
                            System.out.println("Enter valid number as 0 or 1: ");
                    }catch (InputMismatchException e){
                        System.out.println("Enter valid number as 0 or 1: " );
                        String blank = input.next();
                    }
                }

            }
            if (choice == 0){
                break;
            }

        }
    }
    //--------------------------- T E A C H E R ---------------------------


    public static void Teacher() throws IOException {

//------------------------------------------------------
        // --------------------------------------- Teacher ----------------------------------------
        while (true) {

            System.out.println("Enter email: ");
            String email = input.next();
            int teacher = login_As(email);

            if (teacher == 2) {

                while (true) {
                    System.out.println("\n--------------- T E A C H E R ---------------\n");

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(">\tPress 1 to add Questions to Quiz \n" +
                            ">\tPress 2 to view Average Results of Students \n" +
                            ">\tPress 3 to view or modify previous Quiz Questions \n" +
                            ">\tEnter 4 to add Questions for Examination\n" +
                            ">\tEnter 5 to view or modify all Questions in Examination Database\n" +
                            ">\tEnter 6 to generate a paper for Terminal Examination \n"+
                            ">\tPress 0 to quit to previous Menu:");
                    System.out.println("Enter your Choice: ");
                    // field validation required
                    int choice = 0;
                    while (true){
                        try {
                            choice = input.nextInt();
                            if (choice >= 0 && choice <= 6){
                                break;
                            }
                            else System.out.println("Enter valid input from 0 to 6: ");
                        }catch (InputMismatchException e){
                            System.out.println("Enter valid input from 0 to 6");
                            String blank = input.next();
                        }

                    }

                    if (choice == 0) {
                        teacher = 0;
                        System.out.println(teacher);
                        break;
                    }
                    else if (choice == 1) {
                        System.out.println("\n--------------- A D D   Q U E S T I O N S   T O   Q U I Z   D A T A B A S E ---------------\n");

                        add_Quiz_Questions(email);
                    } else if (choice == 2) {
                        System.out.println("\n--------------- V I E W   S T U D E N T S   A V E R A G E   A N D   I N F O R M A T I O N ---------------\n");
                            viewStudentRecord();
                    } else if (choice == 3) {
                        System.out.println("\n--------------- P R E V I O U S   Q U I Z  Q U E S T I O N S ---------------\n");
                        System.out.println("\nThe Previous Quizzes are as Follows: ");
                        viewOrModify_Subject_Questions(email);
                    }
                    else if (choice == 4){
                        System.out.println("\n--------------- A D D   Q U E S T I O N S   F O R   E X A M I N A T I O N ---------------\n");
                        while (true){
                            System.out.println(">\tEnter 1 to add True or False Questions..\n" +
                                    ">\tEnter 2 to add Short Questions\n" +
                                    ">\tEnter 3 to add Long Questions\n" +
                                    ">\tEnter 0 to return to previous menu");
                            int in = 0 ;
                            while (true){
                                try {
                                    in = input.nextInt();
                                    if (in >= 0 && in<= 3)
                                        break;
                                    else
                                        System.out.println("Enter valid option between 0 and 3: ");
                                }catch (InputMismatchException e){
                                    System.out.println("Enter valid input: ");
                                    String blank = input.next();
                                }
                            }

                            if (in == 0)
                                break;
                            else if (in == 1){
                                System.out.println("\n--------------- A D D   T R U E    O R    F A L S E    Q U E S T I O N S ---------------\n");
                                add_TorF(email);
                            }
                            else if (in == 2){
                                System.out.println("\n--------------- A D D   S H O R T   Q U E S T I O N S ---------------\n");
                                add_Shorts(email);
                            }
                            else if (in == 3){
                                System.out.println("\n--------------- A D D   L O N G   Q U E S T I O N S ---------------\n");
                                add_Long(email);
                            }
                        }
                    }
                    else if (choice == 5){
                        System.out.println("--------------- V I E W   OR   M O D I F Y   Q U E S T I O N S   FOR   E X A M I N A T I O N ---------------");
                        while (true){
                            System.out.println(">\tEnter 1 to view or delete True or False Questions:\n" +
                                    ">\tEnter 2 to view or delete Short Questions: \n" +
                                    ">\tEnter 3 to view or delete Long Questions \n" +
                                    ">\tEnter 0 to quit to previous menu:");
                            int in ;
                            while (true) {
                                try {
                                    in = input.nextInt();
                                    if (in >= 0 && in <= 3)
                                        break;
                                    else
                                        System.out.println("Enter valid option between 0 and 3: ");
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter valid input: ");
                                    String blank = input.next();
                                }
                            }

                            if (in==1){
                                viewTorF(email);
                            }
                            else if (in==2){
                                viewShort(email);
                            }
                            else if (in==3){
                                viewLong(email);
                            }
                            else if (in==0)
                                break;
                        }
                    }
                    else if(choice == 6){
                        System.out.println("--------------- G E N E R A T E   T E R M I N A L   E X A M I N A T I O N ---------------");
                        generatePaper(email);
                    }

                }


            } else if (teacher == 1) {
                System.out.println("\nYou are not Designated as a TEACHER...\nTRY LOGGING IN AGAIN!!!\n");
            }
            else if(teacher == -1){
                System.out.println("Enter 1 to enter Email Again:\nOR\nEnter 0 to exit to previous menu:");
                int email_ReEnter ;
                while (true){
                    try {
                        email_ReEnter = input.nextInt();
                        if (email_ReEnter == 0 || email_ReEnter == 1)
                            break;
                        else
                            System.out.println("Enter valid option as 0 or 1 :");
                    }catch (InputMismatchException e){
                        System.out.println("Enter valid option: ");
                        String blank = input.next();
                    }
                }
                if (email_ReEnter == 0) {
                    break;
                }
            }
            else {
                System.out.println("\nQuitting to Main Menu.... \n");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            if (teacher == 0){
                break;
            }

        }

    }


    //   ----------------------- Create A Quiz (Can Only be Accessed by a teacher) -------------------------------------------

    public static void add_Quiz_Questions(String email) throws IOException {
        emails_Sc = new Scanner(emails);
        int index = 0;
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)){
                break;
            }
            index++;
        }

        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];

        File quizPath = new File("Quiz_Questions/");
        quizPath.mkdirs();
        File quiz = new File("Quiz_Questions/"+subject+".txt");
        quiz.createNewFile();

    // getting number of questions
        Scanner quiz_sc = new Scanner(quiz);
        index=0;
        while (quiz_sc.hasNextLine()){
            quiz_sc.nextLine();
            index++;
        }
        quiz_sc = new Scanner(quiz);

        String[] quiz_array = new String[index];
        //Getting old questions
        int i = 0;
        while (quiz_sc.hasNextLine()) {
            String question = quiz_sc.nextLine();
            quiz_array[i] = question;

            i++;
        }
        PrintWriter write = new PrintWriter(quiz);

        for (int k = 0; k < quiz_array.length; k++){
            write.println(quiz_array[k]);
        }

            String blank = input.nextLine();

        while (true){

            System.out.println("\n>\tEnter the Question: \n" +
                    ">\tEnter NO to end this Menu: ");
            String question = input.nextLine();

            if (question.equalsIgnoreCase("NO"))
                break;

            System.out.println("\nEnter option A: ");
            String option_A = input.nextLine();
            System.out.println("\nEnter option B: ");
            String option_B = input.nextLine();
            System.out.println("\nEnter option C: ");
            String option_C = input.nextLine();
            System.out.println("\nEnter option D: ");
            String option_D = input.nextLine();
            System.out.println("\nEnter correct option as A,B,C or D: ");
            String answer = "";
            while (true){
                answer = input.nextLine().toUpperCase();
                if (answer.equals("A") || answer.equals("B") ||answer.equals("C") || answer.equals("D")) {
                    break;
                }
                else
                    System.out.println("\nEnter valid Option as A,B,C or D: ");
            }

            write.println(question + "---" + option_A + "---" + option_B + "---" + option_C + "---" + option_D + "---" + answer);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }

        }
        write.close();
        System.out.println("........... The Questions Have Been Added Successfully ...........");
        System.out.println("--------------------------------------------------------------------------------------------------");


    }



    // -------------- View all Quizzes  modification & Deletion(Accessed by teacher) ------------------------

    public static void viewOrModify_Subject_Questions(String email) throws IOException {
        emails_Sc = new Scanner(emails);
        int index = 0;
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)){
                break;
            }
            index++;
        }

        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];
        System.out.println("--------------- "+subject.toUpperCase()+" ---------------");

        File[] file_list = quizzes_folder.listFiles();
        boolean found = false;
        for(int i=0;i<file_list.length;i++){
            if (file_list[i].getName().equals(subject+".txt"))
            {
                found=true;
                break;
            }
        }
        System.out.println("\nOpening file " + subject + ".txt in a moment....\n");
        // Adding time delay of 1s
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        if (found) {
            File open = new File(quizzes_folder + "/" + subject + ".txt");
            Scanner openSc = new Scanner(open);
            int i = 1;
            System.out.println("--------------------------------------------------------------------------------------");
            while (openSc.hasNextLine()) {
                String question = openSc.nextLine();
                String[] questionArray = question.split("---");
                System.out.println("Question No. " + i + ":: " + questionArray[0]);
                System.out.println("A:: " + questionArray[1]);
                System.out.println("B:: " + questionArray[2]);
                System.out.println("C:: " + questionArray[3]);
                System.out.println("D:: " + questionArray[4]);
                System.out.println("The correct option is::: " + questionArray[5].toUpperCase());
                System.out.println();
                i++;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }

            }
            System.out.println("--------------------------------------------------------------------------------------");
            // If modify::::
            while (true) {
                System.out.println("\n>\tEnter the question number if you want to modify or delete any question:  \n" +
                        ">\tEnter 0 otherwise to quit to previous menu: ");

                int num ;
                while (true){
                    try{
                        num = input.nextInt();
                        if (num>=0 && num <i){
                            break;
                        }
                        else
                            System.out.println("Enter valid Question number: ");
                    }catch (InputMismatchException e){
                        String blank = input.next();
                        System.out.println("Enter valid Question number to proceed: ");
                    }
                }

                if (num == 0)
                    break;

                else {
                    num = num - 1;

                    openSc = new Scanner(open);
                    int length = 0;
                    while (openSc.hasNextLine()) {
                        openSc.nextLine();
                        length++;
                    }
                    String[] allQuestions = new String[length];
                    openSc = new Scanner(open);
                    i = 0;
                    while (openSc.hasNextLine()){
                        allQuestions[i] = openSc.nextLine();
                        i++;
                    }


                    String[] question = allQuestions[num].split("---");

                    // Displaying that question

                    System.out.println("Question :: " + question[0]);
                    System.out.println("A:: " + question[1]);
                    System.out.println("B:: " + question[2]);
                    System.out.println("C:: " + question[3]);
                    System.out.println("D:: " + question[4]);
                    System.out.println("The correct option is::: " + question[5].toUpperCase());

                    System.out.println("\n>\tEnter Q to change Question as a whole: \n" +
                            ">\tEnter A to change option A \n" +
                            ">\tEnter B to change option B \n" +
                            ">\tEnter C to change option C \n" +
                            ">\tEnter D to change option D \n" +
                            ">\tEnter N to change Correct option \n" +
                            ">\tEnter DEL to delete the question\n" +
                            ">\tEnter anything else to return without alteration: ");
                    String option ;
                    while (true){
                        option = input.next().toUpperCase();
                        if (option.equals("Q") || option.equals("A") ||option.equals("B") || option.equals("C") ||option.equals("D") ||option.equals("N") ||option.equals("DEL") ||option.equals("0"))
                            break;
                        else
                            System.out.println("Enter valid input as Q,A,B,C,D,N,DEL or 0 to proceed: ");
                    }
                        String blank = input.nextLine();
                    if (option.equalsIgnoreCase("Q")) {
                        System.out.println("Enter the Question: ");
                        String question_change = input.nextLine();

                        System.out.println("Enter option A: ");
                        String option_A = input.nextLine();
                        System.out.println("Enter option B: ");
                        String option_B = input.nextLine();
                        System.out.println("Enter option C: ");
                        String option_C = input.nextLine();
                        System.out.println("Enter option D: ");
                        String option_D = input.nextLine();
                        System.out.println("Enter correct option as A,B,C or D: ");
                        String answer ;
                        while (true){
                            answer = input.next().toUpperCase();
                            if (answer.equals("A") || answer.equals("B") ||answer.equals("C") || answer.equals("D")) {
                                break;
                            }
                            else
                                System.out.println("Enter valid Option as A,B,C or D: ");
                        }

                        String final_question = question_change + "---" + option_A + "---" + option_B + "---" + option_C + "---" + option_D + "---" + answer;

                        allQuestions[num] = final_question;

                        PrintWriter write = new PrintWriter(open);

                        for (int j = 0; j < allQuestions.length; j++) {
                            write.println(allQuestions[j]);
                        }
                        write.close();
                        System.out.println("Question Modified Successfully...");
                    }

                    else if (option.equalsIgnoreCase("A")) {
                        System.out.println("Enter new option A: ");
                        String option_A = input.nextLine();
                        question[1] = option_A;
                        String modified = "";
                        for (int j = 0; j < question.length; j++) {
                            if (j == question.length - 1)
                                modified += question[j];
                            else
                                modified += question[j] + "---";
                        }
                        allQuestions[num] = modified;
                        PrintWriter write = new PrintWriter(open);

                        for (int j = 0; j < allQuestions.length; j++) {
                            write.println(allQuestions[j]);
                        }
                        write.close();
                        System.out.println("Question Modified Successfully...");
                    }

                    else if (option.equalsIgnoreCase("B")) {
                        System.out.println("Enter new option B: ");
                        String option_B = input.nextLine();
                        question[2] = option_B;
                        String modified = "";
                        for (int j = 0; j < question.length; j++) {
                            if (j == question.length - 1)
                                modified += question[j];
                            else
                                modified += question[j] + "---";
                        }
                        allQuestions[num] = modified;
                        PrintWriter write = new PrintWriter(open);

                        for (int j = 0; j < allQuestions.length; j++) {
                            write.println(allQuestions[j]);
                        }
                        write.close();
                        System.out.println("Question Modified Successfully...");

                    }


                    else if (option.equalsIgnoreCase("C")) {
                        System.out.println("Enter new option C: ");
                        String option_C = input.nextLine();
                        question[3] = option_C;
                        String modified = "";
                        for (int j = 0; j < question.length; j++) {
                            if (j == question.length - 1)
                                modified += question[j];
                            else
                                modified += question[j] + "---";

                        }
                        allQuestions[num] = modified;
                        PrintWriter write = new PrintWriter(open);

                        for (int j = 0; j < allQuestions.length; j++) {
                            write.println(allQuestions[j]);
                        }
                        write.close();
                        System.out.println("Question Modified Successfully...");

                    }

                    else if (option.equalsIgnoreCase("D")) {
                        System.out.println("Enter new option D: ");
                        String option_D = input.nextLine();
                        question[4] = option_D;
                        String modified = "";
                        for (int j = 0; j < question.length; j++) {
                            if (j == question.length - 1)
                                modified += question[j];
                            else
                                modified += question[j] + "---";
                        }
                        allQuestions[num] = modified;
                        PrintWriter write = new PrintWriter(open);

                        for (int j = 0; j < allQuestions.length; j++) {
                            write.println(allQuestions[j]);
                        }
                        write.close();
                        System.out.println("Question Modified Successfully...");

                    }
                    else if (option.equalsIgnoreCase("N")) {
                        System.out.println("Enter new correct Option as A or B or C or D: ");
                        String ans ;
                        while (true){
                            ans = input.next().toUpperCase();
                            if (ans.equals("A") || ans.equals("B") ||ans.equals("C") || ans.equals("D")) {
                                break;
                            }
                            else
                                System.out.println("Enter valid Option as A,B,C or D: ");
                        }
                        question[5] = ans;
                        String modified = "";

                        for (int j = 0; j < question.length; j++) {
                            if (j == question.length - 1)
                                modified += question[j];
                            else
                                modified += question[j] + "---";
                        }
                        allQuestions[num] = modified;
                        PrintWriter write = new PrintWriter(open);

                        for (int j = 0; j < allQuestions.length; j++) {
                            write.println(allQuestions[j]);
                        }
                        write.close();
                        System.out.println("Question Modified Successfully...");
                    }

                    else if(option.equalsIgnoreCase("DEL")){
                        System.out.println(">\tEnter 1 to delete the question from database\n" +
                                ">\tElse Enter 0 to quit this menu:");
                        int choice ;

                        while (true){
                            try {
                                choice = input.nextInt();
                                if (choice == 1 || choice == 0)
                                    break;
                                else
                                    System.out.println("Enter Valid Option as 1 or 0: ");
                            }catch (InputMismatchException e){
                                System.out.println("Enter Valid Option: ");
                                blank = input.next();
                            }
                        }

                        if (choice == 1){
                            String[] newAllQuestions = new String[allQuestions.length-1];
                            for (int in=0;in<newAllQuestions.length;in++){
                                if (in>=num){
                                    newAllQuestions[in] = allQuestions[in+1];
                                }
                                else{
                                    newAllQuestions[in] = allQuestions[in];
                                }
                            }
                            PrintWriter write = new PrintWriter(open);

                            for (int j = 0; j < newAllQuestions.length; j++) {
                                write.println(newAllQuestions[j]);
                            }
                            write.close();
                            System.out.println("Question Deleted Successfully...\n");

                        }
                    }
                    else
                        break;
                }
            }
        }
        else {
            System.out.println("The subject was not found in the database....\n");
        }

    }


    // -------------- Adding truth or false questions to database
    public static void add_TorF(String email) throws IOException{
        emails_Sc = new Scanner(emails);
        int index = 0;
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)){
                break;
            }
            index++;
        }

        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];
        System.out.println("--------------- "+subject.toUpperCase()+" ---------------");

        File TorF = new File(TorF_folder+"/"+subject+".txt");
        TorF.createNewFile();

        Scanner TorF_Sc = new Scanner(TorF);
        int count = 0;
        while (TorF_Sc.hasNextLine()){
            TorF_Sc.nextLine();
            count++;
        }
        TorF_Sc = new Scanner(TorF);
        String[] TorF_array = new String[count];
        index = 0;
        while (TorF_Sc.hasNextLine()){
            TorF_array[index] = TorF_Sc.nextLine();
            index++;
        }

        PrintWriter write = new PrintWriter(TorF);

        for (int i=0;i<TorF_array.length;i++){
            write.println(TorF_array[i]);
        }

        String blank = input.nextLine();
        while (true){
            System.out.println(">\tEnter Questions for Truth or False::\n" +
                    ">\tEnter NO to quit to previous menu");
            String question = input.nextLine();
            if (question.equalsIgnoreCase("NO"))
                break;
            else{
                write.println(question);
            }

        }
        write.close();

    }


    // -------------- Adding Short questions to database
    public static void add_Shorts(String email) throws IOException {
        emails_Sc = new Scanner(emails);
        int index = 0;
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)){
                break;
            }
            index++;
        }

        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];
        System.out.println("--------------- "+subject.toUpperCase()+" ---------------");

        File shortQ = new File(short_Questions_folder+"/"+subject+".txt");
        shortQ.createNewFile();

        Scanner short_Sc = new Scanner(shortQ);
        int count = 0;
        while (short_Sc.hasNextLine()){
            short_Sc.nextLine();
            count++;
        }
        String[] short_array = new String[count];
        short_Sc = new Scanner(shortQ);
        index = 0;
        while (short_Sc.hasNextLine()){
            short_array[index] = short_Sc.nextLine();
            index++;
        }
        PrintWriter write = new PrintWriter(shortQ);

        for (int i=0;i<short_array.length;i++){
            write.println(short_array[i]);
        }

        String blank = input.nextLine();
        while (true){
            System.out.println(">\tEnter Short Questions for Examination::\n" +
                    ">\tEnter NO to quit to previous menu: ");
            String question = input.nextLine();
            if (question.equalsIgnoreCase("NO"))
                break;
            else{
                write.println(question);
            }

        }
        write.close();

    }


    // -------------- Adding long questions to database
    public static void add_Long(String email)throws IOException {
        emails_Sc = new Scanner(emails);
        int index = 0;
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)){
                break;
            }
            index++;
        }

//        System.out.println("INDEX======" + index);
        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];
        System.out.println("--------------- "+subject.toUpperCase()+" ---------------");

        File longQ = new File(Long_Questions_folder+"/"+subject+".txt");
        longQ.createNewFile();

        Scanner long_Sc = new Scanner(longQ);
        int count = 0;
        while (long_Sc.hasNextLine()){
            long_Sc.nextLine();
            count++;
        }
        long_Sc = new Scanner(longQ);
        String[] long_array = new String[count];
        index = 0;
        while (long_Sc.hasNextLine()){
            long_array[index] = long_Sc.nextLine();
            index++;
        }
        PrintWriter write = new PrintWriter(longQ);

        for (int i=0;i<long_array.length;i++){
            write.println(long_array[i]);
        }

        String blank = input.nextLine();
        while (true){

            System.out.println(">\tEnter Long Questions for Examination::\n" +
                    ">\tEnter NO to quit to previous menu:");
            String question = input.nextLine();
            if (question.equalsIgnoreCase("NO"))
                break;
            else{
                write.println(question);
            }

        }
        write.close();

    }



    // --------------- VIew True or false
    public static void viewTorF(String email) throws IOException {
        int index = 0;
        emails_Sc = new Scanner(emails);
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)) {
                break;
            }
            index++;
        }

        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];
        System.out.println("--------------- " + subject.toUpperCase() + " ---------------");

        File[] file_list = TorF_folder.listFiles();
        boolean found = false;
        for (int i = 0; i < file_list.length; i++) {
            if (file_list[i].getName().equals(subject + ".txt")) {
                found = true;
                break;
            }
        }
        System.out.println("\nOpening file " + subject + ".txt in a moment....\n");
        // Adding time delay of 1s
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        if (found) {
            File open = new File(TorF_folder + "/" + subject + ".txt");
            Scanner openSc = new Scanner(open);
            int i = 1;
            while (openSc.hasNextLine()) {
                String question = openSc.nextLine();
//                String[] questionArray = question.split("---");
                System.out.println("Question No. " + i + ":: " + question);
                i++;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
            }

            while (true) {
                System.out.println("\n>\tEnter the question number if you want to modify or delete any question:  \n" +
                        ">\tEnter 0 otherwise: ");

                int num ;

                while (true){
                    try {
                        num = input.nextInt();
                        if (num>=0 && num < i)
                            break;
                        else
                            System.out.println("Enter valid number as an option: ");
                    }catch (InputMismatchException e){
                        System.out.println("Enter valid Option");
                        String blank = input.next();
                    }
                }


                if (num == 0)
                    break;

                else {
                    num = num - 1;

                    openSc = new Scanner(open);
                    int length = 0;
                    while (openSc.hasNextLine()) {
                        openSc.nextLine();
                        length++;
                    }
                    String[] allQuestions = new String[length];
                    openSc = new Scanner(open);
                    i = 0;
                    while (openSc.hasNextLine()) {
                        allQuestions[i] = openSc.nextLine();
                        i++;
                    }


                    String[] question = allQuestions[num].split("---");

                    // Displaying that question

                    System.out.println("Question :: " + question[0]);

                        System.out.println(">\tEnter 1 to delete the question from database\n" +
                                ">\tElse 0 to quit this menu:");
                        int choice ;
                        while (true){
                            try {
                                choice = input.nextInt();
                                if (choice == 0 || choice == 1)
                                    break;
                                else
                                    System.out.println("Enter valid number as 0 or 1 to proceed: ");
                            }catch (InputMismatchException e){
                                System.out.println("Enter valid number as 0 or 1 to proceed.");
                                String blank = input.next();
                            }
                        }
                        if (choice == 1) {
                            String[] newAllQuestions = new String[allQuestions.length - 1];
                            for (int in = 0; in < newAllQuestions.length; in++) {
                                if (in >= num) {
                                    newAllQuestions[in] = allQuestions[in + 1];
                                } else {
                                    newAllQuestions[in] = allQuestions[in];
                                }
                            }
                            PrintWriter write = new PrintWriter(open);

                            for (int j = 0; j < newAllQuestions.length; j++) {
                                write.println(newAllQuestions[j]);
                            }
                            write.close();
                            System.out.println("Question Deleted Successfully...\n");

                        }
                    }

                }
            }

        else {
            System.out.println("Subject not found in database...\n");
        }

    }

    // --------------- VIew SHort Questions

    public static void viewShort(String email) throws IOException{
        int index = 0;
        emails_Sc = new Scanner(emails);
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)) {
                break;
            }
            index++;
        }

        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];
        System.out.println("--------------- " + subject.toUpperCase() + " ---------------");

        File[] file_list = short_Questions_folder.listFiles();
        boolean found = false;
        for (int i = 0; i < file_list.length; i++) {
            if (file_list[i].getName().equals(subject + ".txt")) {
                found = true;
                break;
            }
        }
        System.out.println("\nOpening file " + subject + ".txt in a moment....\n");
        // Adding time delay of 1s
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (found) {
            File open = new File(short_Questions_folder + "/" + subject + ".txt");
            Scanner openSc = new Scanner(open);
            int i = 1;
            while (openSc.hasNextLine()) {
                String question = openSc.nextLine();
                System.out.println("Question No. " + i + ":: " + question);
                i++;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }

            }

            while (true) {
                System.out.println("\n>\tEnter the question number if you want to modify or delete any question:  \n" +
                        ">\tEnter 0 otherwise: ");

                int num ;

                while (true) {
                    try {
                        num = input.nextInt();
                        if (num >= 0 && num < i)
                            break;
                        else
                            System.out.println("Enter valid number as an option: ");
                    } catch (InputMismatchException e) {
                        System.out.println("Enter valid Option");
                        String blank = input.next();
                    }
                }

                if (num == 0)
                    break;

                else {
                    num = num - 1;

                    openSc = new Scanner(open);
                    int length = 0;
                    while (openSc.hasNextLine()) {
                        openSc.nextLine();
                        length++;
                    }
                    String[] allQuestions = new String[length];
                    openSc = new Scanner(open);
                    i = 0;
                    while (openSc.hasNextLine()) {
                        allQuestions[i] = openSc.nextLine();
                        i++;
                    }


                    String[] question = allQuestions[num].split("---");

                    // Displaying that question

                    System.out.println("Question :: " + question[0]);

                    System.out.println(">\tEnter 1 to delete the question from database\n" +
                            ">\tElse enter 0 to quit this menu:");
                    int choice ;
                           while (true) {
                                try {
                                    choice =input.nextInt();
                                    if (choice == 1 || choice == 0)
                                        break;
                                    else
                                        System.out.println("Enter Valid Input as 0 or 1: ");
                                }catch (InputMismatchException e){
                                    System.out.println("Enter valid input as 0 or 1: ");
                                    String blank = input.next();
                                }
                           }
                    if (choice == 1) {
                        String[] newAllQuestions = new String[allQuestions.length - 1];
                        for (int in = 0; in < newAllQuestions.length; in++) {
                            if (in >= num) {
                                newAllQuestions[in] = allQuestions[in + 1];
                            } else {
                                newAllQuestions[in] = allQuestions[in];
                            }
                        }
                        PrintWriter write = new PrintWriter(open);

                        for (String newAllQuestion : newAllQuestions) {
                            write.println(newAllQuestion);
                        }
                        write.close();
                        System.out.println("Question Deleted Successfully...\n");

                    }
                }

            }
        }

        else {
            System.out.println("Subject not found in database...\n" +
                    "Add Questions to view as there are no questions for this subject in Database\n");
        }

    }


    // --------------- View Long Questions
    public static void viewLong(String email) throws IOException {
        int index = 0;
        emails_Sc = new Scanner(emails);
        while (emails_Sc.hasNextLine()) {
            if (emails_Sc.nextLine().equals(email)) {
                break;
            }
            index++;
        }

        String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);
        String[] desig_Array = desig.split("---");
        String subject = desig_Array[1];
        System.out.println("--------------- " + subject.toUpperCase() + " ---------------");

        File[] file_list = Long_Questions_folder.listFiles();
        boolean found = false;
        for (int i = 0; i < file_list.length; i++) {
            if (file_list[i].getName().equals(subject + ".txt")) {
                found = true;
                break;
            }
        }
        System.out.println("\nOpening file " + subject + ".txt in a moment....\n");
        // Adding time delay of 1s
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (found) {
            File open = new File(Long_Questions_folder + "/" + subject + ".txt");
            Scanner openSc = new Scanner(open);
            int i = 1;
            while (openSc.hasNextLine()) {
                String question = openSc.nextLine();
//                String[] questionArray = question.split("---");
                System.out.println("Question No. " + i + ":: " + question);
                i++;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
            }

            while (true) {
                System.out.println("\n>\tEnter the question number if you want to modify or delete any question:  \n" +
                        ">\tEnter 0 otherwise to quit to previous menu: ");

                int num ;

                while (true) {
                    try {
                        num = input.nextInt();
                        if (num >= 0 && num < i)
                            break;
                        else
                            System.out.println("Enter valid number as an option: ");
                    } catch (InputMismatchException e) {
                        System.out.println("Enter valid Option");
                        String blank = input.next();
                    }
                }


                if (num == 0)
                    break;

                else {
                    num = num - 1;

                    openSc = new Scanner(open);
                    int length = 0;
                    while (openSc.hasNextLine()) {
                        openSc.nextLine();
                        length++;
                    }
                    String[] allQuestions = new String[length];
                    openSc = new Scanner(open);
                    i = 0;
                    while (openSc.hasNextLine()) {
                        allQuestions[i] = openSc.nextLine();
                        i++;
                    }


                    String[] question = allQuestions[num].split("---");

                    // Displaying that question

                    System.out.println("Question :: " + question[0]);

                    System.out.println(">\tEnter 1 to delete the question from database\n" +
                            ">\tElse enter anything to quit this menu:");
                    int choice = input.nextInt();
                    if (choice == 1) {
                        String[] newAllQuestions = new String[allQuestions.length - 1];
                        for (int in = 0; in < newAllQuestions.length; in++) {
                            if (in >= num) {
                                newAllQuestions[in] = allQuestions[in + 1];
                            } else {
                                newAllQuestions[in] = allQuestions[in];
                            }
                        }
                        PrintWriter write = new PrintWriter(open);

                        for (String newAllQuestion : newAllQuestions) {
                            write.println(newAllQuestion);
                        }
                        write.close();
                        System.out.println("Question Deleted Successfully...\n");

                    }
                }

            }
        }

        else {
            System.out.println("Subject not found in database...\n" +
                    "Add Long questions for this menu as there are no long questions available\n");
        }

    }


    // -------------------------------- ADMIN CONTROL ---------------------

    public static void add_Teacher_or_Student_or_Admin() throws IOException{

        while (true){
            System.out.println("\n--------------- A D D   T E A C H E R   O R   S T U D E N T   R E C O R D ---------------\n");

            System.out.println(">\tEnter 1 to add Student records \n" +
                    ">\tEnter 2 to add Teachers Record \n" +
                    ">\tEnter 3 to add another Admin to System \n" +
                    ">\tEnter 0 to quit this menu : ");
            int choice ;

            while (true){
                try {
                    choice = input.nextInt();
                    if (choice >= 0 && choice <= 3)
                        break;
                    else
                        System.out.println("Enter valid option from 0 to 3: ");
                }catch (InputMismatchException e){
                    System.out.println("Enter valid option as 0,1,2 or 3 to proceed..:");
                    String blank = input.next();
                }
            }

            if (choice == 1){
                addRecord(1);
            }
            else if (choice == 2){
                addRecord(2);
            }
            else if (choice==3){
                addRecord(3);
            }
            else if( choice == 0){
                break;
            }

        }

    }


    public static void addRecord(int choice) throws IOException {

        information.createNewFile();
        boolean add = false;
        String blank = input.nextLine();
        do {
            // Taking Email Input
            System.out.println("\nEnter email : \n" +
                    "(Valid email syntax will be :  abc@gmail.com)");
            String email;
            while (true){
                email = input.nextLine();
                if (email.endsWith("@gmail.com")){
                    boolean valid = true;
                    emails_Sc = new Scanner(emails);
                    while (emails_Sc.hasNextLine()){

                        if (email.equals(emails_Sc.nextLine())) {
                            System.out.println("Email already present\n" +
                                    "Enter email again:");
                            valid = false;
                        }
                    }
                    if (valid){
                            System.out.println("Valid Email..");
                        break;
                    }
                }
                else {
                    System.out.println("Invalid Syntax for email \n" +
                            "Try Again.... \n" +
                            "Enter Email: ");
                }
            }
// Taking Password Input

            System.out.println("\nEnter Password: \n" +
                    "(The password must be 8 or more characters  and less than 20 characters) ");

            String password;
            while (true){
                password = input.nextLine();
                if (password.length()>=8 && password.length()<=20){
                    System.out.println("Valid Password!!!");
                    break;
                }
                else {
                    System.out.println("Invalid Password \n" +
                            "Enter your password again...");
                }
            }

// Adding Email to database
            emails_Sc = new Scanner(emails);
            int count = 0;
            while (emails_Sc.hasNextLine()){
                emails_Sc.nextLine();
                count++;
            }
            emails_Sc = new Scanner(emails);
            String[] email_array = new String[count];
            int index = 0;
            while (emails_Sc.hasNextLine()){
                email_array[index] = emails_Sc.nextLine();
                index++;
            }


            // Restore Previous Record
            PrintWriter write_email = new PrintWriter(emails);

            for (int i=0;i < email_array.length;i++){
                write_email.println(email_array[i]);
            }
            // Add new Record
            write_email.println(email);
            write_email.close();


            // Add Password  and Restore Previous Record ..................................................
            password_Sc = new Scanner(passwords);
            count = 0;
            while (password_Sc.hasNextLine()){
                password_Sc.nextLine();
                count++;
            }

            password_Sc = new Scanner(passwords);
            String[] password_array = new String[count];
            index = 0;
            while (password_Sc.hasNextLine()){
                password_array[index] = password_Sc.nextLine();
                index++;
            }


            // Restore Previous Record
            PrintWriter write_password = new PrintWriter(passwords);

            for (int i=0;i < password_array.length;i++){
                write_password.println(password_array[i]);
            }
            // Add new Record
            write_password.println(password);
            write_password.close();

        // Add Designation with PRevious Record.............................. .........

            designation_Sc = new Scanner(designation);
            count = 0;
            while (designation_Sc.hasNextLine()){
                designation_Sc.nextLine();
                count++;
            }

            designation_Sc = new Scanner(designation);
            String[] desig_array = new String[count];
            index = 0;
            while (designation_Sc.hasNextLine()){
                desig_array[index] = designation_Sc.nextLine();
                index++;
            }



            // Add new Record

            String desig = "";
            if (choice == 1){

                desig = "STUDENT";

            }
            else if (choice == 2){
                designation_Sc = new Scanner(designation);
                count=0;
                while (designation_Sc.hasNextLine()){
                    String line = designation_Sc.nextLine();
                    if (line.contains("TEACHER")){
                        count++;
                    }
                }
                String subject = "";
                if (count!=0) {
                    String[] subject_array = new String[count - 1];
                    designation_Sc = new Scanner(designation);
                    index = 0;
                    int replicates = 0;
                    while (designation_Sc.hasNextLine()) {
                        String des = designation_Sc.nextLine();
                        if (des.contains("TEACHER")) {
                            String[] des_array = des.split("---");
                            boolean replicate = false;

                            for (int i = 0; i < subject_array.length; i++) {
                                if (des_array[1].equals(subject_array[i])) {
                                    replicates++;
                                    replicate = true;
                                }

                            }
                            if (!replicate) {
                                subject_array[index] = des_array[1];
                                index++;
                            }
                        }
                    }
                    String[] sub_new = new String[subject_array.length - replicates + 1];
                    for (int i = 0; i < sub_new.length; i++) {
                        sub_new[i] = subject_array[i];
                    }


                    while (true) {

                        System.out.println(">\tEnter the serial number of Subject of Teacher from list below: \n" +
                                ">\tElse Enter 0 to add a new Subject: ");
                        for (int i = 0; i < sub_new.length; i++) {
                            System.out.println((i + 1) + "..  " + sub_new[i]);
                        }
                        System.out.println();
                        int sub ;

                        while (true){
                            try {
                                sub = input.nextInt();
                                if (sub >= 0 && sub <=sub_new.length){
                                    break;
                                }
                                else
                                    System.out.println("Enter valid serial Number from the above list: ");
                            }catch (InputMismatchException e){
                                System.out.println("Enter valid Serial Number from above List: ");
                                blank = input.next();
                            }
                        }

                        if (sub == 0) {
//                            blank = input.nextLine();
                            subject = input.nextLine().toUpperCase();
                            desig = "TEACHER---" + subject;

                            break;
                        } else if (sub <= sub_new.length) {
                            subject = sub_new[sub - 1];
                            desig = "TEACHER---" + subject;

                            break;
                        }

                    }
                }else if (count == 0){
                    System.out.println("\nEnter the subject of Teacher: ");
//                    blank = input.nextLine();
                    subject = input.nextLine().toUpperCase();
                    desig = "TEACHER---" + subject;
                }

            }
            else if (choice==3){

                desig = "ADMIN";

            }

            // Restore Previous Record
            PrintWriter write_desig = new PrintWriter(designation);

            for (int i=0;i < desig_array.length;i++){
                write_desig.println(desig_array[i]);
            }


            write_desig.println(desig);
            write_desig.close();

            //////////////// ADD Student Information
//            blank = input.nextLine();
            System.out.println("\nEnter name : ");
            String name ;
            while (true){
                name = input.nextLine();
                boolean valid = true;
                for (int i=0;i<name.length();i++){
                    if (!(Character.isLetter(name.charAt(i)) || name.charAt(i) == ' ')) {
                        valid = false;
                        break;
                    }
                }
                if (valid){
                    break;
                }
                else {
                    System.out.println("Invalid Name input \n" +
                            "Enter name again: ");
                }

            }

            System.out.println("\nEnter 13-digit CNIC number containing numbers only: " +
                    "(Example: XXXXXXXXXXXXX ): ");
            String cnic ;
            while (true){
                cnic = input.nextLine();
                boolean valid = true;
                if (cnic.length() == 13){
                    for (int i=0;i<cnic.length();i++){
                        if (!(Character.isDigit(cnic.charAt(i)))){
                            valid = false;
                            break;
                        }
                    }
                }
                else {
                    valid = false;
                }

                if (valid){
                    break;
                }
                else {
                    System.out.println("Invalid CNIC input \n" +
                            "Enter your CNIC again");
                }
            }

            System.out.println("\nEnter Your Phone Number: \n" +
                    "The format should be : +92xxxxxxxxxx");
            String phone = "";

            while (true){
                phone = input.next();
                if (phone.length()==13 && phone.startsWith("+92")){
                    System.out.println("Valid Phone Number..\n");
                    break;
                }
                else
                    System.out.println("Enter valid Phone Number again: ");
            }

            System.out.println("\nEnter age : ");
            int age ;

            while (true){
                try {
                    age = input.nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Enter valid age in digits: ");
                    blank = input.next();
                }
            }

            Scanner info_Sc = new Scanner(information);

            count = 0;
            while (info_Sc.hasNextLine()){
                info_Sc.nextLine();
                count++;
            }
            String[] info_array = new String[count];
            info_Sc = new Scanner(information);

            index = 0;
            while (info_Sc.hasNextLine()){
                info_array[index] = info_Sc.nextLine();
                index++;
            }

            PrintWriter write_Info = new PrintWriter(information);

            for (int i=0;i<info_array.length;i++){
                write_Info.println(info_array[i]);
            }

            String add_info = name  + "---" + cnic + "---" + phone+ "---"+age + "---" + email + "---" + desig;

            write_Info.println(add_info);
            write_Info.close();

            System.out.println("\n..........Data Added Successfully........\n");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }

            System.out.println(">\tEnter 1 to add another record: \n" +
                    ">\tElse enter 0 to quit :");
            int addition ;

            while (true) {
                try {
                    addition = input.nextInt();
                    if (addition == 1 || addition == 0)
                        break;
                    else
                        System.out.println("Enter either 1 or 0 as option: ");
                }catch (InputMismatchException e){
                    System.out.println("Enter either 1 or 0 as an option to proceed: ");
                    blank = input.next();
                }
            }

            if (addition==1)
                add = true;
        }while (add);
    }


    public static void viewStudentRecord() throws IOException {
       Scanner information_Sc = new Scanner(information);

       int count = 0;
       while (information_Sc.hasNextLine()) {
           if (information_Sc.nextLine().contains("STUDENT")) {
               count++;
           }
       }
       String[] student_info = new String[count];

       information_Sc = new Scanner(information);
       int index = 0;
       while (information_Sc.hasNextLine()) {
           String line = information_Sc.nextLine();
           if (line.contains("STUDENT")) {
               student_info[index] = line;
               index++;
           }
       }

       if (index == 0) {
           System.out.println(".....No Student Record Present....");
       } else {
           System.out.println(">\tEnter 1 to view all student records at once: \n" +
                   ">\tEnter 2 to view a specific record: ");
           int choice = input.nextInt();
           if (choice == 1) {
               System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
               System.out.printf("%-10s | %-20s | %-15s | %-15s | %-6s | %-30s | %-20s | %-15s | \n", "Sr. No.", "NAME", "CNIC", "Mobile Number", "AGE", "EMAIL ADDRESS", "QUIZZES ATTEMPTED", "AVERAGE %");
               for (int i = 0; i < student_info.length; i++) {
//           String[] entities = student_info[i].split("---");
                   String[] student_array = student_info[i].split("---");
                   int average = 0;
                   for (int j = 6; j < student_array.length; j++) {
                       average += Integer.parseInt(student_array[j]);
                   }

                   int quiz_attempted = student_array.length - 6;
                   if (quiz_attempted == 0)
                       average = 0;
                   else
                       average = (int) (((double) average / (quiz_attempted * 15)) * (100));

                   System.out.printf("%-10d | %-20s | %-15s | %-15s | %-6s | %-30s | %-20d | %-15d | \n", (i + 1), student_array[0], student_array[1], student_array[2], student_array[3], student_array[4], quiz_attempted, average);
               }
               System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

           } else if (choice == 2) {
               System.out.println("The List of Students is given as Follows: \n");
               for (int i = 0; i < student_info.length; i++) {
                   String[] entities = student_info[i].split("---");
                   System.out.println((i + 1) + ".  " + entities[0]);
               }
               while (true) {
                   System.out.println(">\tEnter the serial number of student you want to check Information for: \n" +
                           ">\tElse enter 0 to exit: ");
                   System.out.println("Enter your choice: ");
                    choice = 0;
                   while(true){
                       try{
                           choice = input.nextInt();
                           if (choice>=0 && choice<=student_info.length)
                               break;
                           else
                               System.out.println("Enter valid input :");
                       }catch (InputMismatchException e){
                           System.out.println("Enter valid input: ");
                       }
                   }

                   if (choice == 0)
                       break;

                   else if (choice <= student_info.length) {
                       String[] student_array = student_info[choice - 1].split("---");
                       System.out.println("The information of student is as follows: \n");
                       System.out.println("--------------------------------------------------------------------------");
                       System.out.println("Name: " + student_array[0] + "\n" +
                               "CNIC number: " + student_array[1] + "\n" +
                               "Phone Number" + student_array[2] + "\n" +
                               "Age: " + student_array[3] + "\n" +
                               "Email Adress: " + student_array[4]);

                       int average = 0;
                       for (int i = 6; i < student_array.length; i++) {
                           average += Integer.parseInt(student_array[i]);

                       }
                       int quiz_attempted = student_array.length - 6;

                       if (quiz_attempted == 0) {
                           average = 0;
                       } else {
                           average = (int) (((double) average / (quiz_attempted * 15)) * (100));
                       }
                       System.out.println("The number of total quizzes attempted are: " + (quiz_attempted));
                       System.out.println("The average percentage of students is: " + average);
                       System.out.println("--------------------------------------------------------------------------\n");

                   } else {
                       System.out.println("Enter valid serial number again: ");
                   }
               }
           }

       }
   }


    public static void viewTeacherRecord() throws IOException{
       designation_Sc = new Scanner(designation);
        Scanner information_Sc ;

       int count = 0;
       while (designation_Sc.hasNextLine()){
           if (designation_Sc.nextLine().contains("TEACHER")){
               count++;
           }
       }
       String[] teacher_Info = new String[count];

       information_Sc = new Scanner(information);
       int index = 0;
       while (information_Sc.hasNextLine()){
           String line = information_Sc.nextLine();
           if (line.contains("TEACHER")){
               teacher_Info[index] = line;
               index++;
           }
       }

       if (index == 0){
           System.out.println("There is no record of Teachers:: ");
       }

       else {
           System.out.println(">\tEnter 1 to view all Teacher records at once: \n" +
                   ">\tEnter 2 to view a specific record: \n" +
                   "Else Enter 0 to quit this Menu: ");
           int choice;

           while (true){
               try {
                   choice = input.nextInt();
                   if (choice>=0 && choice<= 2)
                       break;
                   else System.out.println("Enter valid choice as 0,1 or 2 to proceed: ");
               }catch (InputMismatchException e){
                   System.out.println("Enter valid choice as 0 , 1 or 2 to proceed: ");
                   String blank = input.next();
               }
           }

           if(choice==1) {

               //---------------\
               System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
               System.out.printf("%-10s | %-20s | %-15s | %-15s | %-6s | %-40s | %-20s | %-35s | \n", "Sr. No.", "NAME", "CNIC","Mobile Number", "AGE", "EMAIL ADDRESS", "DESIGNATION", "SUBJECT");
               for (int i = 0; i < teacher_Info.length; i++) {

                   String[] teacher_array = teacher_Info[i].split("---");

                   System.out.printf("%-10d | %-20s | %-15s | %-15s | %-6s | %-40s | %-20s | %-35s | \n", (i + 1), teacher_array[0], teacher_array[1], teacher_array[2], teacher_array[3], teacher_array[4], teacher_array[5],teacher_array[6]);
               }
               System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

           }
           //----------------

           else if (choice==2) {


               System.out.println(">\tEnter the serial number of student you want to check Information for: \n" +
                       ">\tElse enter 0 to exit: ");
               for (int i = 0; i < teacher_Info.length; i++) {
                   String[] entities = teacher_Info[i].split("---");
                   System.out.println((i + 1) + ".  " + entities[0]);
               }
               while (true) {
                   System.out.println(">\tEnter your choice: \n" +
                           ">\tElse Enter 0 to exit this Menu:");
                   int in = input.nextInt();

                   if (in == 0)
                       break;

                   else if (in <= teacher_Info.length) {
                       String[] teacher_Array = teacher_Info[in - 1].split("---");
                       System.out.println("The information of student is as follows: ");
                       System.out.println("--------------------------------------------------------------------------");
                       System.out.println("Name: " + teacher_Array[0] + "\n" +
                               "CNIC number: " + teacher_Array[1] + "\n" +
                               "Mobile Number: " + teacher_Array[2] + "\n" +
                               "Age: " + teacher_Array[3] + "\n" +
                               "Email Adress: " + teacher_Array[4] + "\n" +
                               "SUBJECT OF TEACHING: " + teacher_Array[6]);

                       System.out.println("--------------------------------------------------------------------------");

                   } else {
                       System.out.println("Enter valid serial number again: ");
                   }
               }
           }
       }
   }

    public static void editRecord() throws IOException{
        while (true){
            System.out.println(">\tEnter 1 to edit Student's Record\n" +
                    ">\tEnter 0 to Quit to previous Menu: ");
            int choice ;
            while (true){
                try{
                    choice = input.nextInt();
                    if (choice >=0 && choice <=1){
                        break;
                    }
                    else {
                        System.out.println("Enter correct option as 1 or 0:");
                    }
                }catch (InputMismatchException e){
                    String blank = input.next();
                    System.out.println("Enter a valid Input ..");
                }
            }
            if (choice == 1){
                editStudent();
            }

            else
                break;
        }
    }



    public static void editStudent() throws IOException {
       Scanner information_Sc = new Scanner(information);

       int index = 0;
       while (information_Sc.hasNextLine()){
           information_Sc.nextLine();
           index++;
       }
       String[] info_array = new String[index];
       information_Sc = new Scanner(information);
        index = 0;
        while (information_Sc.hasNextLine()){
            info_array[index] = information_Sc.nextLine();
            index++;
        }

//        for (String s: info_array)
//            System.out.println(s);


       information_Sc = new Scanner(information);
       int count = 0;
       while (information_Sc.hasNextLine()) {
           if (information_Sc.nextLine().contains("STUDENT")) {
               count++;
           }
       }
       String[] student_info = new String[count];

       information_Sc = new Scanner(information);
       index = 0;
       while (information_Sc.hasNextLine()) {
           String line = information_Sc.nextLine();
           if (line.contains("STUDENT")) {
               student_info[index] = line;
               index++;
           }
       }
       System.out.println(">\tEnter the serial number of student you want to check Information for: \n" +
               ">\tElse enter 0 to exit: \n");
       for (int i = 0; i < student_info.length; i++) {
           String[] entities = student_info[i].split("---");
           System.out.println((i + 1) + ".  " + entities[0]);
       }
       System.out.println();
       while (true) {
           System.out.println("Enter your choice: ");
           int choice ;

           while (true){
               try {
                   choice = input.nextInt();
                   if (choice >= 0 && choice <= student_info.length)
                       break;
                   else
                       System.out.println("Enter valid number to proceed.");
               }catch (InputMismatchException e){
                   System.out.println("Enter valid Number to Proceed: ");
                   String blank = input.next();
               }
           }

           if (choice == 0)
               break;

           else if (choice <= student_info.length) {
               String[] student_array = student_info[choice - 1].split("---");
               String s_email = student_array[4];
               String cnic = student_array[1];
               System.out.println("The information of student is as follows: ");
               System.out.println("--------------------------------------------------------------------------");
               System.out.println("Name: " + student_array[0] + "\n" +
                       "CNIC number: " + student_array[1] + "\n" +
                       "Phone Number" + student_array[2] + "\n" +
                       "Age: " + student_array[3] + "\n" +
                       "Email Adress: " + student_array[4]);


               System.out.println("--------------------------------------------------------------------------");

               System.out.println("\n>\tEnter 1 to change Name of Student \n" +
                       ">\tEnter 2 to edit email of student\n" +
                       ">\tEnter 3 to change it's Password\n" +
                       ">\tEnter 4 to change Phone Number: \n" +
                       ">\tEnter 0 to exit to previous menu without change: ");
               int in ;
               while (true){
                   try {
                       in=input.nextInt();
                       if (in >=0 && in<=4)
                           break;
                       else
                           System.out.println("Enter valid number : ");
                   }catch (InputMismatchException e ){
                       System.out.println("Enter a valid option from 0 to 4: ");
                       String str = input.next();
                   }
               }
               if (in == 0)
                   break;


               else if(in == 1){
                   System.out.println("Enter the name : ");
                   String name = "";
                   input.nextLine();
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
                   student_array[0] = name;
                   String stu = "";
                   for (int i=0;i<student_array.length;i++){
                       if (i==student_array.length-1){
                           stu += student_array[i];
                       }
                       else
                           stu += student_array[i] + "---";
                   }
                   System.out.println("stu>>" + stu);
                   information_Sc = new Scanner(information);
                   index = 0;
                   while (information_Sc.hasNextLine()){
                       if (information_Sc.nextLine().contains(s_email)){
                           break;
                       }
                       index++;
                   }

                   info_array[index] = stu;


                   PrintWriter write = new PrintWriter(information);
                   for (String s : info_array) {
                       write.println(s);
                   }
                   write.close();

                   System.out.println("\nName has been Updated...\n");
               }


               else if (in == 2){
                   System.out.println("Enter new email : ");
                   String email = "";
                   input.nextLine();
                   while (true){
                       email = input.nextLine();
                       boolean verify = true;
                       for (int i=0;i<email.length();i++){
                           if (!email.endsWith("@gmail.com")){
                               System.out.println("Enter valid email ending with @gmail.com : ");
                               verify = false;
                               break;
                           }
                       }
                       emails_Sc = new Scanner(emails);
                       while (emails_Sc.hasNextLine()){
                           if (emails_Sc.nextLine().equals(email)){
                               verify = false;
                               System.out.println("The email is already present..");
                               break;
                           }
                       }
                       if (verify) {

                           break;
                       }
                   }

                   emails_Sc = new Scanner(emails);
                   index = 0;
                   while (emails_Sc.hasNextLine()){
                       emails_Sc.nextLine();
                       index++;
                   }
                   String[] em_array = new String[index];
                   emails_Sc = new Scanner(emails);
                   index = 0;
                   while (emails_Sc.hasNextLine()){
                       em_array[index] = emails_Sc.nextLine();
                       index++;
                   }


                   student_array[4] = email;
                   String stu = "";
                   for (int i=0;i<student_array.length;i++){
                       if (i==student_array.length-1){
                           stu += student_array[i];
                       }
                       else
                           stu += student_array[i] + "---";
                   }
                   information_Sc = new Scanner(information);
                   index = 0;
                   while (information_Sc.hasNextLine()){
                       if (information_Sc.nextLine().contains(cnic)){
                           break;
                       }
                       index++;
                   }

                   info_array[index] = stu;
                   em_array[index] = email;

                   PrintWriter write = new PrintWriter(information);
                   for (String s : info_array) {
                       write.println(s);
                   }
                   write.close();

                   PrintWriter writer = new PrintWriter(emails);
                   for (String s : em_array){
                       writer.println(s);
                   }
                   writer.close();

                   System.out.println("\nEmail has been Updated...\n");
               }

               else if (in == 3){
                   emails_Sc = new Scanner(emails);
                   int loc = 0;
                   while (emails_Sc.hasNextLine()){
                       if (emails_Sc.nextLine().equals(s_email)){
                           break;
                       }
                       loc++;
                   }
                   System.out.println("Enter Password: \n" +
                           "(The password must be 8 characters or more but less than 20 characters..\n");

                   String password;
                   while (true){
                       password = input.nextLine();
                       if (password.length()>=8 && password.length() <= 20){
                           System.out.println("Valid Password!!!");
                           break;
                       }
                       else {
                           System.out.println("Invalid Password \n" +
                                   "Enter your password again...");
                       }
                   }
                   password_Sc = new Scanner(passwords);
                   count = 0;
                   while (password_Sc.hasNextLine()){
                       password_Sc.nextLine();
                       count++;
                   }
                   password_Sc = new Scanner(passwords);
                   String[] pass_arr = new String[count];
                   index = 0;
                   while (password_Sc.hasNextLine()){
                       pass_arr[index] = password_Sc.nextLine();
                       index++;
                   }
                   pass_arr[loc] = password;

                   PrintWriter write_pass = new PrintWriter(passwords);

                   for (String p : pass_arr){
                       write_pass.println(p);
                   }
                   write_pass.close();
                   System.out.println("Password Updated Successfully....\n");

               }


               else if (in==4){
                   System.out.println("Enter the Phone Number : \n" +
                           "(The format should be +92xxxxxxxxxx): ");
                   String phone = "";

                   while (true){
                       phone = input.next();
                       if (phone.length()==13 && phone.startsWith("+92")){
                           System.out.println("Valid Phone Number..\n");
                           break;
                       }
                       else
                           System.out.println("Enter valid Phone Number again: ");
                   }
                   student_array[2] = phone;
                   String stu = "";
                   for (int i=0;i<student_array.length;i++){
                       if (i==student_array.length-1){
                           stu += student_array[i];
                       }
                       else
                           stu += student_array[i] + "---";
                   }
                   information_Sc = new Scanner(information);
                   index = 0;
                   while (information_Sc.hasNextLine()){
                       if (information_Sc.nextLine().contains(s_email)){
                           break;
                       }
                       index++;
                   }

                   info_array[index] = stu;

                   PrintWriter write = new PrintWriter(information);
                   for (String s : info_array) {
                       write.println(s);
                   }
                   write.close();

                   System.out.println("\nPhone Number has been Updated...\n");
               }

           }



       }
   }


    public static void viewQuizzes() throws IOException {

       File[] quizzes_names = quizzes_folder.listFiles();

       while (true) {
           System.out.println("The Subjects for quizzes are given as: ");
            if (quizzes_names.length != 0) {
                for (int i = 0; i < quizzes_names.length; i++) {
                    System.out.println((i + 1) + ".\t" + quizzes_names[i].getName().substring(0, quizzes_names[i].getName().length() - 4));
                }
                System.out.println(">\tEnter the serial number of subject you want to view questions for quiz: \n" +
                        ">\tEnter 0 to quit to menu: ");
                int choice ;
                while (true) {
                    try {
                        choice = input.nextInt();
                        if (choice >= 0 && choice <= quizzes_names.length) {
                            break;
                        } else
                            System.out.println("Enter Valid Serial Number again: ");
                    } catch (InputMismatchException e) {
                        String wrong = input.next();
                        System.out.println("Enter valid input: ");
                    }
                }
                if (choice == 0)
                    break;

                File quiz = new File(quizzes_folder + "/" + quizzes_names[choice - 1].getName());
                Scanner quiz_scan = new Scanner(quiz);
                int count = 1;
                while (quiz_scan.hasNextLine()) {
                    String line = quiz_scan.nextLine();
                    String[] line_array = line.split("---");
                    System.out.println(count + ".\t" + line_array[0]);
                    count++;
                }

                System.out.println(">\tEnter the question number you wish to open \n" +
                        ">\tElse Enter 0 to quit to previous Menu: ");
                int in ;
                while (true){
                    try {
                        in = input.nextInt();
                        if (in >= 0 && in <= count - 1) {
                            break;
                        } else {
                            System.out.println("Enter valid Serial Number: ");
                        }


                    } catch (InputMismatchException e) {
                        String wrong = input.next();
                        System.out.println("Enter valid input: ");

                    }
                }

                if (in == 0) {
                    System.out.println("Quitting to Previous Menu \n");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println();
                    }
                    break;
                } else {
                    String question = Files.readAllLines(Paths.get(quizzes_folder + "/" + quizzes_names[choice - 1].getName())).get(in - 1);
                    String[] q_arr = question.split("---");
                    System.out.println("Question : \n" + q_arr[0] + "\n" +
                            "A: " + q_arr[1] + "\n" +
                            "B: " + q_arr[2] + "\n" +
                            "C: " + q_arr[3] + "\n" +
                            "D: " + q_arr[4] + "\n" +
                            "Answer: " + q_arr[5]);

                    System.out.println(">\tEnter 1 to view all questions again \n" +
                            ">\tOR \n" +
                            ">\tEnter 0 to quit to previous menu: ");
                    int inp = 0;
                    while (true) {
                        try {
                            inp = input.nextInt();
                            if (inp == 1 || inp == 0) {
                                break;
                            } else
                                System.out.println("Enter either 0 or 1: ");
                        } catch (InputMismatchException e) {
                            System.out.println("Enter valid Input: ");
                        }
                    }
                    if (inp == 0) {
                        break;
                    }
                    System.out.println();

                }
            }
            else {
                System.out.println("No Quiz Available to display ::(( \n");
                break;
            }

       }

   }


    public static void viewTerminal() throws IOException{
        File terminal = new File("Final_Exams/");
        if (terminal.exists()){
            File[] exams = terminal.listFiles();
            for (int i=0;i<exams.length;i++){
                System.out.println((i+1) + ".\t" + exams[i].getName().substring(0,exams[i].getName().length()-4));
            }
            System.out.println(">\tEnter serial number from above list to view exam: \n" +
                    ">\tEnter 0 to exit to previous menu: ");
            int serial = 0;
            while (true){
                try{
                    serial = input.nextInt();
                    if (serial>=0 && serial<=exams.length)
                        break;
                    else
                        System.out.println("Enter correct Serial number: ");
                }catch (InputMismatchException e){
                    String blank = input.next();
                    System.out.println("Enter valid serial number: ");
                }
            }

            if (serial != 0){
                File paper = new File(terminal+ "/" + exams[serial-1].getName());
                Scanner paper_sc = new Scanner(paper);

                while (paper_sc.hasNextLine()){
                    System.out.println(paper_sc.nextLine());
                    try {
                        Thread.sleep(75);
                    } catch (InterruptedException e) {

                    }
                }

            }
            System.out.println("\nReturning To Previous Menu.... ");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        else
            System.out.println("No terminal Examinations Have Been Added Yet To Database \n");
   }


    public static void viewExamQs() throws IOException{

        while (true){
            System.out.println("\n--------------- V I E W   A L L   E X A M I N A T I O N   Q U E S T I O N S ---------------\n");
            System.out.println(">\tEnter 1 to view True or False Questions \n" +
                    ">\tEnter 2 to view Short Questions \n" +
                    ">\tEnter 3 to view Long Questions \n" +
                    ">\tEnter 0 to return to previous menu: ");
            int choice ;
            while (true){
                try {
                    choice = input.nextInt();
                    if (choice >=0 && choice <= 3)
                        break;
                    else
                        System.out.println("Enter valid Option as 1 , 2 , 3 or 0: ");
                }catch (InputMismatchException e){
                    System.out.println("Enter valid Number: ");
                    String str = input.next();
                }
            }

            if (choice == 1){
                File terminal = new File(TorF_folder + "/");
                if (terminal.exists()){
                    File[] exams = terminal.listFiles();
                    for (int i=0;i<exams.length;i++){
                        System.out.println((i+1) + ".\t" + exams[i].getName().substring(0,exams[i].getName().length()-4));
                    }
                    System.out.println(">\tEnter serial number from above list to view T or F questions: \n" +
                            "\tOR \n" +
                            ">\tEnter 0 to exit to previous menu: ");
                    int serial ;
                    while (true){
                        try{
                            serial = input.nextInt();
                            if (serial>=0 && serial<=exams.length)
                                break;
                            else
                                System.out.println("Enter correct Serial number: ");
                        }catch (InputMismatchException e){
                            String str = input.next();
                            System.out.println("Enter valid serial number: ");
                        }
                    }

                    if (serial != 0){
                        File paper = new File(terminal+ "/" + exams[serial-1].getName());
                        Scanner paper_sc = new Scanner(paper);

                        int count = 0;
                        while (paper_sc.hasNextLine()){
                            System.out.println("Q: "+ (count+1) + "\t" + paper_sc.nextLine());
                            count++;
                        }

                    }
                    System.out.println("\nReturning To Previous Menu.... ");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                else
                    System.out.println("\n...No True or False Questions have been added yet...\n");
            }


            else if (choice == 2){
                File terminal = new File(short_Questions_folder + "/");
                if (terminal.exists()){
                    File[] exams = terminal.listFiles();
                    for (int i=0;i<exams.length;i++){
                        System.out.println((i+1) + ".\t" + exams[i].getName().substring(0,exams[i].getName().length()-4));
                    }
                    System.out.println(">\tEnter serial number from above list to view exam: \n" +
                            ">\tEnter 0 to exit to previous menu: ");
                    int serial = -1;
                    while (true){
                        try{
                            serial = input.nextInt();
                            if (serial>=0 && serial<=exams.length)
                                break;
                            else
                                System.out.println("Enter correct Serial number: ");
                        }catch (InputMismatchException e){
                            String str = input.next();
                            System.out.println("Enter valid serial number: ");
                        }
                    }

                    if (serial != 0){
                        File paper = new File(terminal+ "/" + exams[serial-1].getName());
                        Scanner paper_sc = new Scanner(paper);

                        int count = 0;
                        while (paper_sc.hasNextLine()){
                            System.out.println("Q: "+ (count+1) + "\t" + paper_sc.nextLine());
                            count++;
                        }

                    }
                    System.out.println("\nReturning To Previous Menu.... \n");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                else
                    System.out.println("\n...No Short Questions has have been added yet...\n");
            }


            else if (choice == 3){
                File terminal = new File(Long_Questions_folder + "/");
                if (terminal.exists()){
                    File[] exams = terminal.listFiles();
                    for (int i=0;i<exams.length;i++){
                        System.out.println((i+1) + ".\t" + exams[i].getName().substring(0,exams[i].getName().length()-4));
                    }
                    System.out.println(">\tEnter serial number from above list to view exam: \n" +
                            ">\tEnter 0 to exit to previous menu: ");
                    int serial ;
                    while (true){
                        try{
                            serial = input.nextInt();
                            if (serial>=0 && serial<=exams.length)
                                break;
                            else
                                System.out.println("Enter correct Serial number: ");
                        }catch (InputMismatchException e){
                            String str = input.next();
                            System.out.println("Enter valid serial number");
                        }
                    }

                    if (serial != 0){
                        File paper = new File(terminal+ "/" + exams[serial-1].getName());
                        Scanner paper_sc = new Scanner(paper);
                        int count = 0;
                        while (paper_sc.hasNextLine()){
                            System.out.println("Q: "+ (count+1) + "\t" + paper_sc.nextLine());
                            count++;
                        }

                    }
                    System.out.println("\nReturning To Previous Menu.... ");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                else
                    System.out.println("\n...No Long Questions have been added yet...\n");
            }

            else if (choice == 0)
                break;

        }

   }


   //Student
    public static void viewPersonalProfile(String email) throws IOException{
        Scanner info_sc = new Scanner(information);
        int index = 0;
        while (info_sc.hasNextLine()){
            String line = info_sc.nextLine();
            if (line.contains(email)){
                String[] info = line.split("---");
                System.out.println("-------------------------------------------------------");
                System.out.println("Name      :\t" + info[0] + "\n" +
                        "CNIC      :\t" + info[1] + "\n" +
                        "Phone No. :\t"  + info[2] + "\n" +
                        "Age       :\t" + info[3] + "\n" +
                        "Email Adress :\t" + info[4]);
                System.out.println("-------------------------------------------------------");
                break;
            }
            index ++;
        }



    }


    public static void generatePaper(String email) throws IOException {

       emails_Sc = new Scanner(emails);
       int index = 0;
       while (emails_Sc.hasNextLine()) {
           if (emails_Sc.nextLine().equals(email)) {
               break;
           }
           index++;


       }
       String desig = Files.readAllLines(Paths.get("Designations.txt")).get(index);

       String[] sub = desig.split("---");
       String subject = sub[1];

       String[] torf_array = new String[0];
       String[] short_array = new String[0];
       String[] long_array = new String[0];

       File torf = new File(TorF_folder + "/" + subject + ".txt");
       if (torf.exists()) {
           Scanner q1 = new Scanner(torf);
           index = 0;
           while (q1.hasNextLine()) {
               q1.nextLine();
               index++;

           }
            torf_array = new String[index];
           q1 = new Scanner(torf);
           int i = 0;
           while (q1.hasNextLine()) {
               torf_array[i] = q1.nextLine();
//            System.out.println(array[i]);
               i++;
           }
       }

       File shorts = new File(short_Questions_folder + "/" + subject + ".txt");
       if (shorts.exists()) {
           Scanner q2 = new Scanner(shorts);
           index = 0;
           while (q2.hasNextLine()) {
               q2.nextLine();
               index++;

           }
           short_array = new String[index];
           q2 = new Scanner(shorts);
           int i = 0;
           while (q2.hasNextLine()) {
               short_array[i] = q2.nextLine();
//           System.out.println(array2[i]);
               i++;
           }
       }
       File longs = new File(Long_Questions_folder + "/" + subject + ".txt");
       if(longs.exists()) {
           Scanner q3 = new Scanner(longs);
           index = 0;
           while (q3.hasNextLine()) {
               q3.nextLine();
               index++;

           }
           long_array = new String[index];
           q3 = new Scanner(longs);
           int i = 0;
           while (q3.hasNextLine()) {
               long_array[i] = q3.nextLine();
//           System.out.println(array3[i]);
               i++;
           }
       }

       System.out.println(">\tEnter Year (From 2000 to 2040) of Exam you wish to generate: ");
       int year ;

       while (true){
           try {
               year = input.nextInt();
               if (year>=2000 && year <=2040){
                   break;
               }
               else
                   System.out.println("Enter valid Year from 2000 to 2040: ");
           }catch (InputMismatchException e){
               String blank = input.next();
               System.out.println("Enter valid Year from 2000 to 2040: ");
           }
       }


       while (true) {
           File paper_path = new File("Final_Exams/");
           paper_path.mkdirs();

           File paper = new File(paper_path + "/" + subject + "-" + year + ".txt");
           paper.createNewFile();


           PrintWriter pw = new PrintWriter(paper);

           pw.println("____________________________________________________________________________________________________________________");
           pw.printf("%65s\n", "C O M S A T S  University Islamabad");
           pw.printf("%62s\n", subject.toUpperCase());
           pw.printf("%60s\n", "Terminal Examination ");
           pw.printf("%-10s %90s\n", " Total Marks - 100 ", " Year: 2020");
           pw.println();
           pw.println("Instructions to Students\n" +
                   "\n" +
                   ">\tWrite your Name, Registration Number, Campus, Date .\n" +
                   ">\tMobile phone, digital diaries or any other digital storage medium is not allowed.\n" +
                   ">\tType your answers on the same document provided as question paper.\n" +
                   ">\tSubmit the answer sheet via CU Online Console in the given time. No submissions are allowed via email.\n" +
                   ">\tAttempt all questions.");

           pw.println("____________________________________________________________________________________________________________________");
           int score = 0;

           pw.println("Question-1 :");
           pw.println("------------");
           pw.println();

           System.out.println(">\tEnter number of T/F questions you want to add: \n" +
                   ">\tEnter 0 to skip True false: ");
           int trueFalse ;

           while (true){
               try {
                   trueFalse =  input.nextInt();
                   if (trueFalse>=0 && trueFalse <=torf_array.length){
                       break;
                   }
                   else
                       System.out.println("Enter valid number for questions less than : " + torf_array.length +
                               "as only "+ torf_array.length  + " questions are available: \nEnter 0 if there are no questions to be added: ");
               }catch (InputMismatchException e) {
                    String blank = input.next();
                   System.out.println("Enter valid number for questions: ");
               }
           }

            if (trueFalse != 0) {
                System.out.println(">\tEnter the marks of each question:");
                int marks ;

                while (true) {
                    try {
                        marks = input.nextInt();
                        if (marks>0 &&marks < 100) {
                            break;
                        } else
                            System.out.println("Enter valid marks for questions less than 100: ");
                    } catch (InputMismatchException e) {
                        System.out.println("Enter valid marks for questions: ");
                        String blank = input.next();
                    }
                }
                score += marks * trueFalse;

                pw.printf("%s %90s \n", "True / False.", "Marks: "+marks * trueFalse);
            }

           if (torf.exists()){
               int count = 0;

               for (int i = 1; i <= trueFalse; i++) {

                   int random = (int) (Math.random() * torf_array.length);

                   String q = torf_array[random];
                   if(q.length() > 70){
                       pw.println(i + "\t" + q.substring(0,70) + "\n\t" + q.substring(70));
                   }
                   else
                        pw.println(i + "\t" + torf_array[random]);

               }
           }
           else {
               System.out.println("No True or False Questions have been added yet.");
           }


           pw.println();
           pw.println("____________________________________________________________________________________________________________________");

           pw.println("Question-2 :");
           pw.println("------------");
           pw.println();
           System.out.println(">\tEnter number of short questions you want to add: \n" +
                   ">\tEnter 0 if none: ");
           int shortQ ;
            while (true) {
                try {
                    shortQ = input.nextInt();
                    if (shortQ >= 0 && shortQ < short_array.length) {
                        break;
                    } else
                        System.out.println("Enter valid number for questions less than : " + short_array.length +
                                "as only "+ short_array.length  + " questions are available: \nEnter 0 if there are no questions to be added: ");
                } catch (InputMismatchException e) {
                    System.out.println("Enter valid number for questions: ");
                    String blank = input.next();
                }
            }

            if (shortQ!=0) {
                System.out.println(">\tEnter the marks of each question:");
                int marksS ;
                while (true) {
                    try {
                        marksS = input.nextInt();
                        if (marksS > 0 && marksS < 100) {
                            break;
                        } else
                            System.out.println("Enter valid marks for questions less than 100: ");
                    } catch (InputMismatchException e) {
                        System.out.println("Enter valid marks for questions: ");
                        String blank = input.next();
                    }
                }
                score += marksS * shortQ;

                pw.printf("%s %90s \n", "Short questions.", "Marks: " + marksS * shortQ);
                if (shorts.exists()) {
                    int random = (int) (Math.random() * short_array.length);

                    for (int i = 1; i <= shortQ; i++) {
                        String q = short_array[random];
                        if(q.length() > 70){
                            pw.println(i + "\t" + q.substring(0,70) + "\n\t" + q.substring(70));
                        }
                        else
                            pw.println(i + "\t" + q);




                    }
                }
                else {
                    System.out.println("NO shorts have been added yet: ");
                }
            }


           pw.println("____________________________________________________________________________________________________________________");
           pw.println("Question-3 :");
           pw.println("------------");
           System.out.println(">\tEnter number of long questions you want to add: ");
           int longQ ;
            while (true) {
                try {
                    longQ = input.nextInt();
                    if (longQ >= 0 && longQ <= long_array.length) {
                        break;
                    } else
                        System.out.println("Enter valid number for questions less than : " + long_array.length +
                                "as only "+ long_array.length  + " questions are available: \nEnter 0 if there are no questions to be added: ");
                } catch (InputMismatchException e) {
                    System.out.println("Enter valid number for questions: ");
                    String blank = input.next();
                }

            }
            if (longQ!=0) {
                System.out.println(">\tEnter the marks of each question:");
                int marksL ;
                while (true) {
                    try {
                        marksL = input.nextInt();
                        if (marksL > 0 && marksL <= 100) {
                            break;
                        } else
                            System.out.println("Enter valid marks below 100: ");
                    } catch (InputMismatchException e) {
                        System.out.println("Enter valid marks for questions: ");
                        String blank = input.next();
                    }
                }
                score += marksL * longQ;
                pw.printf("%s %90s \n", "Long questions.", "Marks: " + marksL * longQ);
                if (longs.exists()){
                    for (int i = 1; i <= longQ; i++) {


                        int random = (int) (Math.random() * long_array.length);
                        String q = long_array[random];
                        if(q.length() > 70){
                            pw.println(i + "\t" + q.substring(0,70) + "\n\t" + q.substring(70));
                        }
                        else
                            pw.println(i + "\t" + q);


                    }
                }
                else {
                    System.out.println("No Longs have been added in database yet: ");
                }
            }
           pw.println("--------------------------------------------------------------------------------------------------------------------");
           pw.println("--------------------------------------------- B E S T   O F   L U C K ----------------------------------------------");
           pw.println("--------------------------------------------------------------------------------------------------------------------");

           if (score > 100) {
               System.out.println("\nThe marks have exceeded 100.\nEnter 1 to Try Again\n" +
                       "Enter 0 to exit to previous menu: \n");
               int choice ;
               while (true){
                   try{
                       choice = input.nextInt();
                       if (choice==1 || choice == 0)
                           break;
                       else
                           System.out.println("Enter valid input: ");
                   }catch (InputMismatchException e){
                       System.out.println("Enter valid input: ");
                       String blank = input.next();
                   }
               }
               if (choice == 0){
                    paper.delete();
                   break;
               }
           } else if (score < 100) {
               System.out.println("\nThe marks are below 100.\nEnter 1 to Try Again\n" +
                       "Enter 0 to exit to previous menu: \n");
               int choice ;
               while (true){
                   try{
                       choice = input.nextInt();
                       if (choice==1 || choice == 0)
                           break;
                       else
                           System.out.println("Enter valid input: ");
                   }catch (InputMismatchException e){
                       System.out.println("Enter valid input: ");
                       String blank = input.next();
                   }
               }
               if (choice == 0){
                   paper.delete();
                   break;
               }

           } else if (score == 100) {
               System.out.println("\nPaper Generated successfully :) \n");
               pw.close();
               System.out.println("If you want to see the paper. Enter 1 \nElse Enter 0 to exit: ");
               int number ;

               while (true){
                   try{
                       number = input.nextInt();
                       if (number == 0 || number == 1)
                           break;
                       else
                           System.out.println("Enter 0 or 1 as valid input:");
                   }catch (InputMismatchException e){
                       System.out.println("Enter 0 or 1 as valid Input");
                       String blank = input.next();
                   }
               }

               if (number == 1) {
                   Scanner display = new Scanner(paper);
                   while (display.hasNextLine()) {
                       System.out.println(display.nextLine());
                   }
               }
               System.out.println("\n...........Quitting to menu...........\n");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               break;

           }
       }
   }


    public static void quizFormation (String email) throws IOException {

        designation_Sc = new Scanner(designation);
        int count = 0;
        while (designation_Sc.hasNextLine()){
            designation_Sc.nextLine();
            count++;
        }
        String[] sub = new String[count];
        count = 0;
        designation_Sc = new Scanner(designation);
        while (designation_Sc.hasNextLine()){
            String line = designation_Sc.nextLine();
            if (line.contains("TEACHER")){
                String[] arr = line.split("---");
                boolean present = false;
                for (int i=0;i<sub.length;i++){
                    if (arr[1].equals(sub[i])){
                        present = true;
                    }
                }
                if (!present){
                    sub[count] = arr[1];
                    count++;
                }
            }
        }

        count = 0;
        for (int i=0;i<sub.length;i++){
            if(! (sub[i] == null)){
                count++;
            }
        }
        String[] subjects = new String[count];
        int index = 0;
        for (int i=0;i<subjects.length;i++){
            subjects[i] = sub[i];
        }

        if (subjects.length != 0) {
            System.out.println("The subjects are given as: ");
            for (int i = 0; i < subjects.length; i++) {
                System.out.println((i + 1) + ".. " + subjects[i]);
            }


            System.out.println(">\tEnter the serial number from above list of the subject you want to attempt the quiz\n" +
                    ">\tEnter 0 to quit to previous menu: ");
            while (true) {
                int choice ;

                while (true){
                    try{
                        choice = input.nextInt();
                        if (choice>=0 && choice <=subjects.length)
                            break;
                        else
                            System.out.println("Enter valid option to proceed: ");
                    }catch (InputMismatchException e){
                        System.out.println("Enter valid option to proceed: ");
                        String blank = input.next();
                    }
                }

                if (choice > 0 && choice <= subjects.length) {
//
//                    String subject = "PROGRAMMING_FUNDAMENTALS";
                    String subject = subjects[choice-1];

                    File quiz = new File(quizzes_folder + "/" + subject + ".txt");

                    if (quiz.exists()) {

                        System.out.println("____________________________________________________________________________________________________________________");
                        System.out.printf("%65s\n", "C O M S A T S  University Islamabad");
                        System.out.printf("%62s\n", subject.toUpperCase());
                        System.out.printf("%60s\n", "Quiz");
                        System.out.printf("%-10s %90s\n", " Total Marks - 15 ", " Year: 2020");
                        System.out.println();
                        System.out.println("Instructions to Students :-\n" +
                                "\n" +
                                ">\tSubmit the answer sheet via CU Online Console in the given time. No submissions are allowed via email.\n" +
                                ">\tAttempt all questions.");

                        System.out.println("____________________________________________________________________________________________________________________");


                        Scanner q1 = new Scanner(quiz);
                        index = 0;
                        while (q1.hasNextLine()) {
                            q1.nextLine();
                            index++;

                        }
                        String[] array = new String[index];
                        q1 = new Scanner(quiz);
                        int i = 0;
                        while (q1.hasNextLine()) {
                            array[i] = q1.nextLine();

                            i++;
                        }
                        int marks = 0;
                        for (i = 1; i <= 15; i++) {
                            int random = (int) (Math.random() * array.length);
                            String[] q = array[random].split("---");
                            System.out.println("Question number:" + i);
                            System.out.println(q[0]);
                            System.out.println("A: " + q[1]);
                            System.out.println("B: " + q[2]);
                            System.out.println("C: " + q[3]);
                            System.out.println("D: " + q[4]);
                            System.out.println("Select any option:");
                            String option;

                            while (true) {
                                option = input.next().toUpperCase();
                                if (option.equals("A") || option.equals("B") || option.equals("C") || option.equals("D")) {
                                    break;
                                }
                                else {
                                    System.out.println("Enter valid option as A,B,C or D: ");
                                }
                            }

                            if (option.equals(q[5])) {
                                marks++;
                            }

                        }

                        System.out.printf("%-15s %20s \n","Quiz ended! ", "Your marks are:" + marks + "/15");
                        System.out.println("____________________________________________________________________________________________________________________");

                        emails_Sc = new Scanner(emails);

                        int em_index = 0;
                        while (emails_Sc.hasNextLine()){
                            if (emails_Sc.nextLine().equals(email)){
                                break;
                            }
                            em_index ++;
                        }

                        Scanner info_sc = new Scanner(information);
                        count =0 ;
                        while (info_sc.hasNextLine()){
                            info_sc.nextLine();
                            count++;
                        }
                        String[] info_array = new String[count];

                        info_sc = new Scanner(information);
                        index = 0;
                        while (info_sc.hasNextLine()){
                            String line = info_sc.nextLine();
//                            System.out.println(line);
                            info_array[index] = line;
                            index++;
                        }

                        info_array[em_index] = info_array[em_index] + "---" + marks;

                        PrintWriter add = new PrintWriter(information);

                        for (int j=0;j<info_array.length;j++){
                            add.println(info_array[j]);
                        }
                        add.close();


                        System.out.println("\n...Quitting to previous Menu...\n");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;

                    }else {
                        System.out.println(">\tThe Quiz of this Subject has not been added!!! \n" +
                                ">\tEnter your choice again or enter 0 to exit: ");
                    }
                } else if (choice == 0) {
                    break;
                } else
                    System.out.println("Enter valid choice again \n" +
                            "Or Enter 0 to Exit: ");
            }
        }
        else
            System.out.println("\t\tThere is no quiz available at the moment..! \n" +
                    "\t\t\tQuitting to Main Menu....");


       }


    //----------------- CHECK IF STUDENT OR TEACHER'S IS PRESENT --------------------
    public static int login_As(String email) throws IOException{

        boolean email_present = false;
        int present = 0;
        String line;

        while (!email_present) {

//                  Resetting Scaanner to read file from Start
            emails_Sc = new Scanner(emails);

//              The number of line in file at which the email is present
            int index = 0;

            // Set line equal to the line read + applying condition that it is not equal to null ... i.e having no value

            while (emails_Sc.hasNextLine()) {
                line = emails_Sc.nextLine();
                if (line.equals(email)) {
                    email_present = true;
                    break;
                }
                index++;
            }


            if (email_present) {
                while (true) {
                    String blank = input.nextLine();
// Check whether it is a student or a teacher
                    if (Files.readAllLines(Paths.get("Designations.txt")).get(index).equalsIgnoreCase("STUDENT")) {
                        System.out.println("Enter your password: ");
                        String password = input.nextLine();
                        boolean pass_match = false;

                        if (Files.readAllLines(Paths.get("Passwords.txt")).get(index).equals(password)) {
                            pass_match = true;
                        }

                        if (pass_match) {
                            present = 1;
                            break;
                        } else {
                            System.out.println("...Incorrect Password...");
                            System.out.println(">\tEnter 1 to enter password Again:\n" +
                                    ">\tOR\n" +
                                    ">\tEnter 0 to exit to previous menu:");
                            int pass_ReEnter ;
                            while (true){
                                try {
                                    pass_ReEnter = input.nextInt();
                                    if (pass_ReEnter == 0 || pass_ReEnter == 1)
                                        break;
                                    else System.out.println("Enter 1 or 0 as your choice to proceed: ");
                                }catch (InputMismatchException e){
                                    System.out.println("Enter 1 or 0 as your choice to proceed: ");
                                    blank = input.next();
                                }
                            }

                            if (pass_ReEnter == 0) {
                                break;
                            }
                        }
                    }

                    else if((Files.readAllLines(Paths.get("Designations.txt")).get(index)).contains("TEACHER")){
                        System.out.println("Enter your password: ");
                        String password = input.nextLine();
                        boolean pass_match = false;

                        if (Files.readAllLines(Paths.get("Passwords.txt")).get(index).equals(password)) {
                            pass_match = true;

                        }


                        if (pass_match) {
                            present = 2;
                            break;
                        } else {
                            System.out.println("...Incorrect Password...");
                            System.out.println(">\tEnter 1 to enter password Again:\n" +
                                    ">\tOR\n" +
                                    ">\tEnter 0 to exit to previous menu:");
                            int pass_ReEnter ;
                            while (true){
                                try {
                                    pass_ReEnter = input.nextInt();
                                    if (pass_ReEnter == 0 || pass_ReEnter == 1)
                                        break;
                                    else System.out.println("Enter 1 or 0 as your choice to proceed: ");
                                }catch (InputMismatchException e){
                                    System.out.println("Enter 1 or 0 as your choice to proceed: ");
                                    blank = input.next();
                                }
                            }
                            if (pass_ReEnter == 0) {
                                break;
                            }
                        }

                    }
                    else if(Files.readAllLines(Paths.get("Designations.txt")).get(index).equalsIgnoreCase("ADMIN")){
                        System.out.println("Enter your password: ");
                        String password = input.nextLine();
                        boolean pass_match = false;

                        if (Files.readAllLines(Paths.get("Passwords.txt")).get(index).equals(password)) {
                            pass_match = true;

                        }


                        if (pass_match) {
                            present = 3;
                            break;
                        } else {
                            System.out.println("...Incorrect Password...");
                            System.out.println(">\tEnter 1 to Enter Password Again:\n" +
                                    ">\tOR\n" +
                                    ">\tEnter 0 to exit to previous menu:");
                            int pass_ReEnter ;
                            while (true){
                                try {
                                    pass_ReEnter = input.nextInt();
                                    if (pass_ReEnter == 0 || pass_ReEnter == 1)
                                        break;
                                    else System.out.println("Enter 1 or 0 as your choice to proceed: ");
                                }catch (InputMismatchException e){
                                    System.out.println("Enter 1 or 0 as your choice to proceed: ");
                                    blank = input.next();
                                }
                            }
                            if (pass_ReEnter == 0) {
                                break;
                            }
                        }

                    }
                    else {
                        System.out.println("\nYour data is not Present in DataBase\nTry Again sometime...\n");
                        break;
                    }
                }
            }
            else {
                System.out.println("\n...Incorrect Email...\n");

                return -1;



            }


        }
        return present;

    }


}
//----------------------- THE END -----------------------