package Human;

import java.util.Random;

public enum Mood {
    SADNESS,
    LONGING,
    CALM;
    public static Mood randomMood() {
        int pick = new Random().nextInt(Mood.values().length);
        return Mood.values()[pick];
    }
}