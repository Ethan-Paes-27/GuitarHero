import java.util.ArrayList;

public class GuitarHeroVisualizer {
    private GuitarString[] strings;
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public GuitarHeroVisualizer() {
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

    public static void main(String[] args) throws InterruptedException {
        GuitarHeroVisualizer guitarHeroVisualizer = new GuitarHeroVisualizer();
        int index = -1;
        ArrayList<Double> lastSamples = new ArrayList<>();
        boolean isKeyPressed = false;
        int timer = 0;

        // the main input loop
        while (true) {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                index = keyboard.indexOf(key);

                if (index != -1) {
                    isKeyPressed = true;
                    guitarHeroVisualizer.play(index);
                }
            }

            // compute the superposition of the samples
            double sample = guitarHeroVisualizer.allSamples(index);
            // send the result to standard audio
            StdAudio.play(sample);

            if (isKeyPressed || timer++ == 5000) {
                timer = 0;
                isKeyPressed = false;
                StdDraw.clear();

                if (lastSamples.size() > 25) {
                    lastSamples.remove(0);
                }

                lastSamples.add(sample);

                double y = lastSamples.get(0);

                for (int i = 1; i < lastSamples.size()-2; i++) {
                    double newY = lastSamples.get(i);

                    StdDraw.line(0.04 * i, y + 0.5, 0.04*(i+1), newY + 0.5);
                    y = newY;
                }
            }

            // tic all strings
            guitarHeroVisualizer.ticAll();
        }
    }
}
