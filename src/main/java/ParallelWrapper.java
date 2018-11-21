public class ParallelWrapper implements Runnable{
    private CSVExample clsasifier;

    public ParallelWrapper(CSVExample classifier){
        this.clsasifier = classifier;
    }

    public void run() {
        try {
            this.clsasifier.runClassifier();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
