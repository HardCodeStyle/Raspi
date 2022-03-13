import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadCutString {
    String[] fileNames;
    boolean state = true;

    public void read_Price_From_Download(String[] stocks) {
        getFileNames();
        WriteWeekFile writePriceFile = new WriteWeekFile();
        List<String> pricesToSave = new ArrayList<>();
        for (String fileName : fileNames) {
            String lineOfPriceGoogle = read_Line_Of_Price_From_Download("GetData/src/DownLoadFiles/" + fileName);
            Double price = cut_Price_From_String(lineOfPriceGoogle);
            pricesToSave.add(String.valueOf(price));
        }
        writePriceFile.SaveWeeklyPrices(pricesToSave, stocks);
    }

    private void getFileNames() {
        File file = new File("GetData/src/DownLoadFiles");
        fileNames = file.list();
    }

    private Double cut_Price_From_String(String lineOfPriceGoogle) {
        String[] resultFirstSplit = lineOfPriceGoogle.split("data-last-price");
        String[] resultSecondSplit = resultFirstSplit[1].split(" ");
        String splitPrice = resultSecondSplit[0].replace("=", "");
        splitPrice = splitPrice.replace("\"", "");
        return Double.parseDouble(splitPrice);
    }

    public String read_Line_Of_Price_From_Download(String fileName) {
        String lineOfPriceGoogle = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
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
