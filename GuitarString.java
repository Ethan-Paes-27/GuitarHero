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
    // YOUR OTHER INSTANCE VARIABLES HERE

    // create a guitar string of the given frequency
    public GuitarString(double frequency) {
        // YOUR CODE HERE
    }

    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {
        // YOUR CODE HERE
    }

    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
        // YOUR CODE HERE
    }

    // advance the simulation one time step
    public void tic() {
        // YOUR CODE HERE
    }

    // return the current sample
    public double sample() {
        // YOUR CODE HERE

        return 0.0; // dummy return statement so the code compiles
    }

    // return number of times tic was called
    public int time() {
        // YOUR CODE HERE

        return 0; // dummy return statement so the code compiles
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
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
