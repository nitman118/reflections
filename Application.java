
//ANYTHING IN BLUE IS A RESERVED KEYWORD IN JAVA.......

import javax.swing.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Application {

    private static final String Empty = "";

    public static String getFile(String str) {
        String path = Empty;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setApproveButtonText(str);
        int reply = chooser.showOpenDialog(null);

        if (reply == JFileChooser.APPROVE_OPTION) { // if input is valid
            path = chooser.getSelectedFile().getAbsolutePath(); // returns path of selected file
        }

        System.out.println("Entered path:" + path);
        return path; // returns full path for processing
    }

    public static double[][] readData(String filepath, int row, int column, int rownum, int columnnum)
            throws IOException {

        double[][] filearray = new double[row][column];

        File inFile = new File(filepath);
        FileInputStream in = new FileInputStream(inFile);
        // read the workbook from disk

        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i < row; i++) {
            Row row1 = sheet.getRow(i + rownum);

            for (int j = 0; j < column; j++)

            {
                Cell cell = row1.getCell(j + columnnum);
                filearray[i][j] = cell.getNumericCellValue();

            }
        }

        workbook.close();
        in.close();

        return filearray;

    }

    public static void main(String[] args) {
        try {
            double[][] distance;
            String filepath = getFile("Upload file");

            distance = readData(filepath, 58, 58, 3, 3);
            // System.out.println(distance[15][2]);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
