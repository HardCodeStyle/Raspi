import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadCutString {
    String[] fileNames;
    boolean state = true;

    public boolean read_Price_From_Download(String[] stocks) {
        getFileNames();
        WriteWeekFile writePriceFile = new WriteWeekFile();
        List<String> pricesToSave = new ArrayList<>();
        for (String fileName : fileNames) {
            String lineOfPriceGoogle = read_Line_Of_Price_From_Download("GetData/src/DownLoadFiles/" + fileName);
            Double price = cut_Price_From_String(lineOfPriceGoogle);
            pricesToSave.add(String.valueOf(price));
        }
        writePriceFile.SaveWeeklyPrices(pricesToSave, stocks);
        return state;
    }

    private void getFileNames() {
        File f = new File("GetData/src/DownLoadFiles");
        fileNames = f.list();
    }

    private Double cut_Price_From_String(String lineOfPriceGoogle) {
        String[] result = lineOfPriceGoogle.split("data-last-price");
        String[] result1 = result[1].split(" ");
        String test = result1[0].replace("=", "");
        test = test.replace("\"", "");
        return Double.parseDouble(test);
    }

    public String read_Line_Of_Price_From_Download(String fileName) {
        String lineOfPriceGoogle = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (line.contains("data-last-price")) {
                    lineOfPriceGoogle = line;
                }
            }
            // line is not visible here.
        } catch (IOException e) {
            state = false;
            e.printStackTrace();
        }

        File file = new File(fileName);
        file.delete();

        return lineOfPriceGoogle;
    }
}
