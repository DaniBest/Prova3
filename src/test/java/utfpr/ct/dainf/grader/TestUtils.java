package utfpr.ct.dainf.grader;

/**
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class TestUtils {

    public static int random(int min, int max) {
        return min + (int)Math.round((max-min) * Math.random());
    }
    
    public static double random(double min, double max) {
        return min + (max-min) * Math.random();
    }
    
    public static float random(float min, float max) {
        return min + (max-min) * (float)Math.random();
    }
    
    public static boolean aproximate(double x, double y, double precision) {
        return Math.abs(x-y) < precision;
    }
}
