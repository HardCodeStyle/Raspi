import java.util.ArrayList;
import java.util.List;

public class StartRun {

    public void go() {
        List<String> hours = putList();
        String[] stocks = {"DAX:INDEXDB",".DJI:INDEXDJX",".INX:INDEXSP","SXXP:INDEXSTOXX","HSI:INDEXHANGSENG"};
        Run run = new Run();
        run.setDoRun(true);
        run.setRaspiGoOn(true);
        run.run(hours,stocks);
    }

    private List<String> putList() {
        List<String> hours = new ArrayList<>();
        for (int x = 7; x < 22; x++) {
            if (x < 10) {
                hours.add("0" + x);
            } else {
                hours.add("" + x);
            }
        }
        return hours;
    }
}
