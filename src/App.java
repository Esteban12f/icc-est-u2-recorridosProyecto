
import controllers.ControllerView;
import views.ViewMain;

public class App {
    public static void main(String[] args) throws Exception {

        ViewMain vistaPrincipal = new ViewMain();
        ControllerView controlVista = new ControllerView(vistaPrincipal);
        vistaPrincipal.setVisible(true);
        
    }
    
}
