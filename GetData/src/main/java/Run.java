import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Run {

    private boolean doRun = false;
    private boolean doneDownload = false;
    private int countFalseTime = 0;
    private boolean raspiGoOn = false;

    public int getCountFalseTime() {
        return countFalseTime;
    }

    public void setCountFalseTime(int countFalseTime) {
        this.countFalseTime = countFalseTime + 1;
    }

    public boolean isDoneDownload() {
        return doneDownload;
    }

    public void setDoneDownload(boolean doneDownload) {
        this.doneDownload = doneDownload;
    }

    public boolean getDoRun() {
        return doRun;
    }

    public void setDoRun(Boolean doRun) {
        this.doRun = doRun;
    }

    public void run(List<String> hours) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Date date = new Date();

        while (getDoRun()) {

            if (getCountFalseTime() >= 3) {
                setDoRun(false);
                break;
            }

            System.out.println("Hallo 1");
            if (hours.contains(formatter.format(date)) && !isDoneDownload()) {
                System.out.println("Hallo 2");
                setDoneDownload(true);
                try {
                    Thread.sleep(1800000);
                    setDoneDownload(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(1800000);
                    setCountFalseTime(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
