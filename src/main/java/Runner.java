import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) throws Exception {
        int numRuns = 5;
        long totalMS = 0L;

        for(int i = 0; i < numRuns; ++i){
            CSVExample csvExample = new CSVExample();
            Stopwatch timer = Stopwatch.createStarted();
            csvExample.runClassifier();
            timer.stop();
            long runMS = timer.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("Run #" + i + " took " + runMS + " ms");
            totalMS += runMS;
        }

        System.out.println("Total runtime for " + numRuns + " tests: " + totalMS + " ms.");
        System.out.println("Average runtime: " + (totalMS / numRuns) + " ms.");

    }
}
