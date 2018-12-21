import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            foo();

        } catch (IOException e) {
            System.out.println("Path is incorrect");
        }
    }

    public static void foo() throws IOException {
        String stringFromFile = "";
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter path to file: ");
        String s = reader.nextLine();
        reader.close();
        stringFromFile = FileUtils.readFileToString(new File(s), "UTF-8");
        String exp = Calculator.ExpressionToRPN(stringFromFile).replaceAll("\\s+", "");
        File file = new File("output.txt");
        FileUtils.writeStringToFile(file, exp);
        System.out.println("Output file is: " + file.getAbsolutePath());
    }
}
//5 * a + (b - 4) / c
