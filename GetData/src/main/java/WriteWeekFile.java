import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WriteWeekFile {
    public void SaveWeeklyPrices(List<String> price, String[] stock) {

        PrintWriter out = null;
        Date date = new Date();
        String dateString = String.valueOf(date.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date1 = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        for (String fileName : stock) {
            SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd HH:mm");
            Date date2 = new Date();
            int x = 0;
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("GetData/src/SavedPrices/SavedPriceWeekly_" + fileName + "_KW_" + week + ".txt", true)));
                out.println(price.get(x) + "===" + df2.format(date2) + "===" + dateString);
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            x++;
        }

    }
}
