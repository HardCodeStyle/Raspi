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
        if (countFalseTime == 0) {
            this.countFalseTime = 0;
        } else {
            this.countFalseTime = countFalseTime + 1;
        }

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

    public boolean isRaspiGoOn() {
        return raspiGoOn;
    }

    public void setRaspiGoOn(boolean raspiGoOn) {
        this.raspiGoOn = raspiGoOn;
    }

    public void run(List<String> hours, String[] stocks) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        DownloadData dataDownload = new DownloadData();
        DownloadCutString downloadCutString = new DownloadCutString();

        while (isRaspiGoOn()) {
            Date date = new Date();
            setCountFalseTime(0);
            setDoRun(true);
            System.out.println("Hallo 3");
            if (date.getDay() == 0 || date.getDay() == 6) {
                try {
                    Thread.sleep(3600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (hours.contains(formatter.format(date))) {
                while (getDoRun()) {
                    Date date1 = new Date();
                    if (date1.getDay() == 0 || date1.getDay() == 6) {
                        setDoRun(false);
                    } else if (hours.contains(formatter.format(date1)) && !isDoneDownload()) {

                        boolean stateDownload = dataDownload.download(stocks);

                        if (stateDownload) {
                            System.out.println("Download was Successful ...");
                            downloadCutString.read_Price_From_Download(stocks);
                        } else {
                            System.out.println("Download failed ...");
                        }

                        setDoneDownload(true);
                        try {
                            Thread.sleep(300000);
                            setDoneDownload(false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            Thread.sleep(300000);
                            setCountFalseTime(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    if (getCountFalseTime() >= 3) {
                        setDoRun(false);
                    }

                }
            } else {
                try {
                    Thread.sleep(3600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
