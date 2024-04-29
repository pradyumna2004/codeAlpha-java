import java.util.*;

class std {
    std(int fm)
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();

        int totalMarks=0;
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks for subject " + i + ": ");
            int marks = sc.nextInt();

            if (marks < 0 || marks > fm) {
                System.out.println("Marks should be between 0 and "+fm);
                return;
            }

            totalMarks += marks;
        }

        double avgPer= (double) totalMarks / (numSubjects * fm) * 100;

        char grade;
        if (avgPer>= 90) {
            grade = 'O';
        } else if (avgPer>= 80) {
            grade = 'E';
        } else if (avgPer>= 70) {
            grade = 'A';
        } else if (avgPer>= 60) {
            grade = 'B';
        } else if (avgPer>= 40) {
            grade = 'C';
        } else {
            grade = 'F';
        }

        System.out.println("\n \n \tTotal Marks: " + totalMarks);
        System.out.printf("\tAverage Percentage: %.2f%%\n", avgPer);
        System.out.println("\tGrade: " + grade);
    }
}

class stdgrade
{
    public static void main(String[] args) 
    {
            System.out.print("enter the full mark of each subject->");
             Scanner sc = new Scanner(System.in);
            int m=sc.nextInt();
            std s=new std(m);
    }
}
