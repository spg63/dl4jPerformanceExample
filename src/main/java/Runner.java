import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Runner {
    static int numSerialRuns = 1;
    static int numParallel = 25;

    public static void main(String[] args) throws Exception {
        List<Long> serialTimes = serial();
        long parallelTime = parallel();

        long serialRunTotal = 0L;
        for(Long time : serialTimes)
            serialRunTotal += time;
        long serialAverage = serialRunTotal / numSerialRuns;

        for(int i = 0; i < numSerialRuns; ++i)
            System.out.println("Serial run #" + i + " took " + serialTimes.get(i) + " ms.");
        System.out.println("Serial runtime average: " + serialAverage + " ms.");
        System.out.println("Serial training took a total of " + serialRunTotal + " ms.");

        System.out.println("Training " + numSerialRuns + " classifiers in parallel took " + parallelTime + " ms.");

    }

    public static List<Long> serial() throws Exception {
        List<Long> runTimes = new ArrayList<>();

        for(int i = 0; i < numSerialRuns; ++i){
            CSVExample csvExample = new CSVExample();
            Stopwatch timer = Stopwatch.createStarted();
            csvExample.runClassifier();
            timer.stop();
            runTimes.add(timer.elapsed(TimeUnit.MILLISECONDS));
        }

        return runTimes;
    }

    public static long parallel() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < numParallel; ++i)
            threads.add(new Thread(new ParallelWrapper(new CSVExample())));

        Stopwatch timer = Stopwatch.createStarted();

        for(Thread t : threads)
            t.start();

        for(Thread t : threads)
            t.join();

        timer.stop();
        return timer.elapsed(TimeUnit.MILLISECONDS);

    }
}
