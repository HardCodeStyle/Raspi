import java.io.*;
import java.net.URL;
import java.util.Date;

public class DownloadData {

    boolean state = true;

    public boolean download(String[] stocks) {

        for (int i = 0; i <= stocks.length - 1; i++) {
            String webpage = "https://www.google.com/finance/quote/" + stocks[i];
            try {

                // Create URL object
                URL url = new URL(webpage);
                BufferedReader readr =
                        new BufferedReader(new InputStreamReader(url.openStream()));
                Date date = new Date();
                String dateString = String.valueOf(date.getTime());
                // Enter filename in which you want to download
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter("GetData/src/DownLoadFiles/Download_" + i + "_" + dateString + "_" + ".txt"));

                // read each line from stream till end
                String line;
                while ((line = readr.readLine()) != null) {
                    writer.write(line);
                }

                readr.close();
                writer.close();

            }
            // Exceptions
            catch (IOException e) {
                state = false;
            }
        }
        return state;
    }
}
