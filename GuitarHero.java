public class GuitarHero {
    private GuitarString[] strings;
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public GuitarHero() {
        strings = new GuitarString[37];
        
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24) / 12.0)); // 440 × 2^((i - 24) / 12)
        }
    }

    public void play(int index) {
        strings[index].pluck();
    }

    public double allSamples(int index) {
        double sample = 0.0;

        for (GuitarString string : strings) {
            sample += string.sample();
        }

        return sample;
    }

    public void ticAll() {
        for (GuitarString string : strings) {
            string.tic();
        }
    }

    public static void main(String[] args) {
        GuitarHero guitarHero = new GuitarHero();
        int index = -1;

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                index = keyboard.indexOf(key);
                
                if (index == -1) {
                    continue;
                }

                guitarHero.play(index);
            }

            // compute the superposition of the samples
            double sample = guitarHero.allSamples(index);
            // send the result to standard audio
            StdAudio.play(sample);

            // tic all strings
            guitarHero.ticAll();
        }
    }
}
