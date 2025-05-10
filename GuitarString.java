/*************************************************************************
 * Name:
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least comment out the ones whose return type is non-void).
 *
 *****************************************************************************/

public class GuitarString {

    private RingBuffer buffer; // ring buffer
    private int ticCount; // number of times tic has been called

    // create a guitar string of the given frequency
    public GuitarString(double frequency) {
        buffer = new RingBuffer((int)Math.ceil(44100/frequency));
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }

    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {
        buffer = new RingBuffer(init.length);

        for (double d : init) {
            buffer.enqueue(d);
        }
    }

    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
        int size = buffer.size();

        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }

        for (int i = 0; i < size; i++) {
            buffer.enqueue(0.5 - Math.random());
        }
    }

    // advance the simulation one time step
    public void tic() {
        double first = buffer.dequeue();
        double second = buffer.peek();

        buffer.enqueue(0.994 * ((first + second) / 2.0));
        ticCount++;
    }

    // return the current sample
    public double sample() {
        return buffer.peek();
    }

    // return number of times tic was called
    public int time() {
        return ticCount;
    }

    public static void main(String[] args) {
        int N = 20;
        double[] samples = {.2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3};
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }

}
