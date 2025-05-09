/*************************************************************************
 * Name:
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 *****************************************************************************/

public class GuitarString {

    private RingBuffer buffer; // ring buffer
    private double frequency;
    private int tics = 0;
    // YOUR OTHER INSTANCE VARIABLES HERE

    // create a guitar string of the given frequency
    public GuitarString(double frequency) {
        this.frequency = frequency; //NEEDS REVISIONS
        buffer = new RingBuffer((int)frequency);
    }

    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {
        this.frequency = 44100 / init.length; // NEEDS REVISIONS
        buffer = new RingBuffer(init.length);
        for (double value : init) {
            buffer.enqueue(value);
        }
    }

    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        for (int i = 0; i < frequency; i++) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    // advance the simulation one time step
    public void tic() {
        tics++;

        double first = buffer.dequeue();
        double second = buffer.peek();
        double newSample = 0.996 * 0.5 * (first + second);
        buffer.enqueue(newSample);
    }

    // return the current sample
    public double sample() {
        return buffer.peek();
    }

    // return number of times tic was called
    public int time() {
        return tics;
    }

    public static void main(String[] args) {
        int N = 10;
        double[] samples = {.2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3};
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N * 2; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }

}
