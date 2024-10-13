import java.io.*;
import java.util.ArrayList;
import java.math.BigInteger;

public class NNLucasTask {

    ArrayList<Integer> nList = new ArrayList<Integer>();
    ArrayList<BigInteger> lucasList = new ArrayList<BigInteger>();

    public NNLucasTask() {
        read("lucas.txt");
        lucasList.add(BigInteger.valueOf(2)); 
        lucasList.add(BigInteger.valueOf(1)); 

        for (int i : nList) {
            System.out.println("The " + i + "th Lucas number is " + calcLucasNum(i));
        }
    }

    public void read(String fileName) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                nList.add(Integer.valueOf(line.trim()));
            }
        } catch (IOException io) {
            System.err.println("File Error: " + io);
        }
    }

    public BigInteger calcLucasNum(int nNum) {
        while (lucasList.size() <= nNum) {
            int lastIndex = lucasList.size();
            lucasList.add(lucasList.get(lastIndex - 1).add(lucasList.get(lastIndex - 2)));
        }
        return lucasList.get(nNum);
    }

    public static void main(String[] args) {
        NNLucasTask n = new NNLucasTask();
    }

}
