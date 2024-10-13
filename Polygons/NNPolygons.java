import java.io.*;
public class NNPolygons {

    public NNPolygons(){
        read("polygons.txt");
    }

    public void read(String fileName) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\|");
                boolean isAPolygon = isPolygon(arr[0].split(","), arr[1].split(","));
                System.out.print("The polygon " + line.trim() + " is ");
                if (isAPolygon){
                    System.out.println(" possibly a polygon.");
                }
                else{
                    System.out.println(" NOT a polygon.");
                }
            }
        } catch (IOException io) {
            System.err.println("File Error: " + io);
        }

    }

     
    public boolean isPolygon(String[] s, String[] a){        
        int[]sides = new int[s.length];
        int[]angles = new int[a.length];
        for (int i = 0; i < s.length; i++){
            sides[i] = Integer.parseInt(s[i]);
        }

        for (int i = 0; i < a.length; i++){
            angles[i] = Integer.parseInt(a[i]);
        }


        //1. at least 3 sides and angles
        //2. sides and angles equal
        if (sides.length < 3 || angles.length < 3 || sides.length != angles.length){
            return false;
        }

        //3. if angle sum = 180* # of sides - 2
        int angleSum = 0;
        for (int i = 0; i < angles.length; i++) {
            angleSum += angles[i];
        }
        if (angleSum != 180* (angles.length - 2))
        {
            return false;
        }

        //4. the largest side is less than the sum of the other sides
        int maxSideIndex = 0;
        int sideSum = 0;
        for (int i = 0; i < sides.length; i++){
            sideSum += sides[i];
            //System.out.println("Side: " + sides[i] + "   Sum " + sideSum);
            if (sides[i] > sides[maxSideIndex]){
                maxSideIndex = i;
            }
        }
        
        if (sides[maxSideIndex] > sideSum - sides[maxSideIndex]){
            return false;
        }

        return true;
    }
    
    
    public static void main(String[] args) {
        new NNPolygons();
    }

}
