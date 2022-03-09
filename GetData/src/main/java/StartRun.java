import java.util.ArrayList;
import java.util.List;

public class StartRun {

    public void go() {
        List<String> hours = putList();
        Run run = new Run();
        run.setDoRun(true);
        run.setRaspiGoOn(true);
        run.run(hours);
    }

    private List<String> putList() {
        List<String> hours = new ArrayList<>();
        for (int x = 7; x < 23; x++) {
            if (x < 10) {
                hours.add("0" + x);
            } else {
                hours.add("" + x);
            }
        }
        return hours;
    }
}
