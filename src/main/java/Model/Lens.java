package Model;

public class Lens {
    // ----------------------------------------------------Private Attributes---------------------------------------------------------------
    private String make;
    private double maximum_aperture;
    private int focal_length;

    // ----------------------------------------------------------Constructors---------------------------------------------------------------
    public Lens() {
        this.make = "";
        this.maximum_aperture = 0.0;
        this.focal_length = 0;
    }

    public Lens(String make, double maximum_aperture, int focal_length) {
        this.make = make;
        this.maximum_aperture = maximum_aperture;
        this.focal_length = focal_length;
    }

    // --------------------------------------------------------------Getters----------------------------------------------------------------
    public String getMake() {
        return make;
    }

    public double getMaximum_aperture() {
        return maximum_aperture;
    }

    public int getFocal_length() {
        return focal_length;
    }

    // --------------------------------------------------------------Setters----------------------------------------------------------------
    public void setMake(String make) {
        if (make == null || make.length() == 0)
            throw new IllegalArgumentException("Lens cannot be null!");
        else
            this.make = make;
    }

    public void setMaximum_aperture(double maximum_aperture) {
        if (maximum_aperture <= 0)
            throw new IllegalArgumentException("Maximum aperture must be larger than 0!");
        else
            this.maximum_aperture = maximum_aperture;
    }

    public void setFocal_length(int focal_length) {
        if (focal_length <= 0)
            throw new IllegalArgumentException("Focal Length must be larger than 0!");
        else
            this.focal_length = focal_length;
    }

}
