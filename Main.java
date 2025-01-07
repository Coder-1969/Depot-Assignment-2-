package View;


public class Main {
    public static void main(String[] args) {
        // Initialize the ManagerView (GUI)
        ManagerView view = new ManagerView();

        // Initialize the ManagerController and pass the view to it
        ManagerController controller = new ManagerController(view);  // Pass view to the controller
    }
}
