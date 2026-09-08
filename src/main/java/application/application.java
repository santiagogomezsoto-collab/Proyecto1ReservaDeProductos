package application;

import cambiarClave.controller.cambiarClaveController;
import cambiarClave.model.cambiarClaveModel;
import cambiarClave.view.cambiarClaveView;
import login.controller.loginController;
import login.model.loginModel;
import login.repository.UsuarioRepository;
import login.service.LoginService;
import login.view.loginView;
import model.Usuario;


public class application {

    public static void main(String[] args) {

        // Creamos el Model del Login.
        loginModel model = new loginModel();

        // Creamos la View.
        loginView view = new loginView();

        // Creamos el Repository que lee el XML.
        UsuarioRepository repository =
                new UsuarioRepository();

        // Creamos el Service y le damos el Repository.
        LoginService service =
                new LoginService(repository);

        // Creamos el Controller y conectamos todo.
        loginController controller =
                new loginController(model, view, service);

        // Mostramos la ventana.
        view.setVisible(true);


        Usuario usuario = model.getUsuarioAutenticado();



        if (usuario != null) {

            // Creamos el Model de esta nueva pantalla.
            cambiarClaveModel cambiarModel =
                    new cambiarClaveModel(usuario);

            // Creamos la View.
            cambiarClaveView cambiarView =
                    new cambiarClaveView();

            // Creamos el Controller y conectamos Model + View + Service.
            cambiarClaveController cambiarController =
                    new cambiarClaveController(
                            cambiarModel,
                            cambiarView,
                            service
                    );

            // Mostramos la ventana.
            cambiarView.setVisible(true);
        }

    }
}