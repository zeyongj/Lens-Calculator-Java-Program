package Model;

public class Depth_of_Field_Calculator {
    // ----------------------------------------------------Private Attributes---------------------------------------------------------------
    private static Lens lens;
    private static double distance;
    private static double aperture;
    private static double circle_of_confusion = 0.029;

    // ----------------------------------------------------------Constructors---------------------------------------------------------------
    public Depth_of_Field_Calculator() {
        this.lens = null;
        this.distance = 0.0;
        this.aperture = 0.0;
    }

    // --------------------------------------------------Setters and Getters----------------------------------------------------------------
    public Depth_of_Field_Calculator(Lens lens, double distance, double aperture) {
        this.lens = lens;
        this.distance = distance;
        this.aperture = aperture;
    }

    public Lens getLens() {
        return lens;
    }

    public static void setLens(Lens lens) {
        if (lens == null)
            throw new IllegalArgumentException("Lens cannot be null!");
        else
            Depth_of_Field_Calculator.lens = lens;
    }

    public double getDistance() {
        return distance;
    }

    public static void setDistance(double distance) {
        if (distance < 0)
            throw new IllegalArgumentException("Distance cannot be negative!");
        else
            Depth_of_Field_Calculator.distance = distance;
    }

    public double getAperture() {
        return aperture;
    }

    public static void setAperture(double aperture) {
        if (aperture < lens.getMaximum_aperture() || aperture > 22.0)
            throw new IllegalArgumentException("Aperture cannot be less than len's maximum aperture!");
        else
            Depth_of_Field_Calculator.aperture = aperture;
    }

    public static double getCircle_of_confusion() {
        return circle_of_confusion;
    }

    public static void setCircle_of_confusion(double circle_of_confusion) {
        if (circle_of_confusion <= 0)
            throw new IllegalArgumentException("Cof cannot be less or equal than 0!");
        else
            Depth_of_Field_Calculator.circle_of_confusion = circle_of_confusion;
    }

    // ------------------------------------------------------Friend Functions---------------------------------------------------------------
    public double hyperfocalDistance() {
        return ((lens.getFocal_length() * lens.getFocal_length()) / (aperture * circle_of_confusion));
    }

    public double nearFocalPoint() {
        return ((hyperfocalDistance() * (distance * 1000)) / (hyperfocalDistance() + ((distance * 1000) - lens.getFocal_length())));
    }

    public double farFocalPoint() {
        if (distance > hyperfocalDistance())
            return Double.POSITIVE_INFINITY;
        else{
            double ans = (hyperfocalDistance() * (distance * 1000)) / (hyperfocalDistance() - ((distance * 1000) - lens.getFocal_length()));
            if (ans < 0)
                return Double.POSITIVE_INFINITY;
            else
                return ans;
        }
    }

    public double depthOfField() {
        return (farFocalPoint() - nearFocalPoint());
    }


}
