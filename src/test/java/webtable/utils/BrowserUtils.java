package webtable.utils;

public class BrowserUtils {
    public static void sleep(int seconds) {
        /**
         * this method is used to pause the code for given seconds
         */
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Exception happened in sleep method");
        }
    }
}
