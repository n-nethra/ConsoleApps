import java.io.*;

public class NNGPATask {

    /*
    
        The big O is n in this case.
        This is because the first for loop runs n times to calculate and populate the GPAs array,
        and the second loop also runs n times to calculate the mode from the GPAs frequency array. 

     */

    int[] GPAs = new int[41];

    public NNGPATask() {
        read("student_letter_grades.csv");
        calcMode(GPAs);
    }

    public void read(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "";
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] letters = line.split(",");
                calcGPA(letters);
            }
            
            br.close();
        } catch (IOException io) {
            System.err.println("File Error: " + io);
        }
    }


    public void calcGPA(String[] arr){
        double gpa = 0;
        for (int i = 0; i < arr.length; i++){
            gpa += letterToGrade(arr[i]);
        }
        gpa /= 8.0;
        gpa = Math.round(gpa * 10.0) / 10.0;
        
        int index = (int)(gpa * 10);
        if (index >= 0 && index < GPAs.length) {
            GPAs[index] += 1;
        }
    }



    public double letterToGrade(String s) {
        switch(s){
                case "A":
                    return 4.0;
                case "A-":
                    return 3.7;
                case "B+":
                    return 3.3;
                case "B":
                    return 3.0;
                case "B-":
                    return 2.7;
                case "C+":
                    return 2.3;
                case "C":
                    return 2.0;
                case "C-":
                    return 1.7;
                case "D":
                    return 1.0;
                case "F":
                    return 0.0;
                default:
                    return 0.0;
            }
        
    }

    public void calcMode(int[] numbers) {
        int modeCount = 0;
        double mode = 0;
        for (int i = 0; i < GPAs.length; i++)
        {
            if (GPAs[i] > modeCount)
            {
                modeCount = GPAs[i];
                mode = i/10.0;
            }
        }
        System.out.println("Mode of GPAs: " + mode + " occurred " + modeCount + " times.");
    }

    public static void main(String[] args) {
        new NNGPATask();
    }
}
