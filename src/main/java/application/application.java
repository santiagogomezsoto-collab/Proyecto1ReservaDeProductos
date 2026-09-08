package application;

import login.controller.loginController;
import login.model.loginModel;
import login.repository.UsuarioRepository;
import login.service.LoginService;
import login.view.loginView;

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
    }
}