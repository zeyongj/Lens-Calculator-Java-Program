package Text_UI;
// This class is modified from the provided file of CameraTextUI.java.
import Model.Depth_of_Field_Calculator;
import Model.Lens;
import Model.Lens_Manager;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.zip.Inflater;

public class UI {
    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private Lens_Manager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard


    public UI(Lens_Manager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
        manager.add(new Lens("ElCheepo", 12, 24));
        manager.add(new Lens("Leica", 5.6, 1600));
        manager.add(new Lens("TheWide", 1.0, 16));
        manager.add(new Lens("IWish", 1.0, 200));
    }

    public void show() {
        // BEGIN SAMPLE USING SCREEN AND KEYBOARD:
        while (true) {
            System.out.println("Lenses to pick from:");
            for (int i = 0; i < 8; i++) {
                System.out.println(i + ". " + manager.getLensAtIndex(i).getMake()
                        + " " + manager.getLensAtIndex(i).getFocal_length() + "mm F"
                        + manager.getLensAtIndex(i).getMaximum_aperture());
            }
            int index;
            System.out.println("(-1 to exit): ");
            index = in.nextInt();
            if (index == -1)
                break;
            else if (index < -1) {
                System.out.println("PROBLEM: Invalid lens index; please try again.");
                System.out.println("\n");
            }
            else if (index >= 8) {
                System.out.println("PROBLEM: Invalid lens index; please try again.");
                System.out.println("\n");
            }
            else {
                Lens myLens = manager.getLensAtIndex(index);
                System.out.println("Enter aperture (the F number) [" + manager.getLensAtIndex(index).getMaximum_aperture() + " - 22] :");
                double myAperture = in.nextDouble();
                while (myAperture < myLens.getMaximum_aperture()){
                    System.out.println("PROBLEM: This aperture is not possible with this lens; please re-enter.");
                    System.out.println("Enter aperture (the F number) [" + manager.getLensAtIndex(index).getMaximum_aperture() + " - 22] :");
                    myAperture = in.nextDouble();
                }
                while (myAperture > 22){
                    System.out.println("PROBLEM: This aperture is not possible with this lens; please re-enter.");
                    System.out.println("Enter aperture (the F number) [" + manager.getLensAtIndex(index).getMaximum_aperture() + " - 22] :");
                    myAperture = in.nextDouble();
                }
                System.out.println("Distance to subject (in m) [0 to more]: ");
                double myDistance = in.nextDouble();
                while (myDistance < 0.0){
                    System.out.println("PROBLEM: The distance must be 0 or more; please re-enter.");
                    System.out.println("Distance to subject (in m) [0 to more]: ");
                    myDistance = in.nextDouble();
                }
                Depth_of_Field_Calculator myCalculator = new Depth_of_Field_Calculator(myLens, myDistance, myAperture);
                System.out.println("Using a " + manager.getLensAtIndex(index).getMake()
                        + " " + manager.getLensAtIndex(index).getFocal_length() + "mm F"
                        + manager.getLensAtIndex(index).getMaximum_aperture() + " at F"
                        + myAperture + ", " + myDistance + "m:");
                System.out.println("  In focus: " + formatM(myCalculator.nearFocalPoint() / 1000) + "m to "
                        + formatM(myCalculator.farFocalPoint() / 1000) + "m [Dof = " +
                        formatM(myCalculator.depthOfField() / 1000) + "m]");
                System.out.println("  Hyperfocal point: " + formatM(myCalculator.hyperfocalDistance() / 1000) + "m");
                System.out.println("\n");
                }

            }


        }

        // DO NOT HARD-CODE YOUR INTERFACE TO THE SPECIFIC LENSES LISTED IN THE CONSTRUCTOR!


    private String formatM(double distanceInM) {
        if (distanceInM == Double.POSITIVE_INFINITY)
            return "INF";
        else if (distanceInM == Double.NEGATIVE_INFINITY)
            return "-INF";
        else {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(distanceInM);
        }
    }
}