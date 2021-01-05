// This main function is modified from the provided file of Main.java.
import Model.Lens_Manager;
import Text_UI.UI;

public class MyMain {
    public static void main(String[] args) {
        Lens_Manager manager = new Lens_Manager();
        UI ui = new UI(manager);
        ui.show();
    }
}
