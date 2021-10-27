package tgpr.moudeule.controller;

import tgpr.moudeule.model.User;
import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.controller.Controller;
import tgpr.moudeule.view.LoginView;
import tgpr.moudeule.view.View;

public class LoginController extends Controller {

    private final LoginView view = new LoginView();

    private User askPseudo() {
        String pseudo;
        User user;
        do {
            pseudo = view.askPseudo();
            user = User.getByPseudo(pseudo);
            if (user == null)
                view.error("unknown user");
        } while (user == null);
        return user;
    }

    private String askPassword(User user) {
        String password = null;
        do {
            password = view.askPassword();
            if (!password.equals(user.getPassword()))
                view.error("bad password");
        } while (!password.equals(user.getPassword()));
        return password;
    }

    public void run() {
        view.displayHeader();
        try {
            var user = askPseudo();
            askPassword(user);
            MoudeuleApp.setLoggedUser(user);

            if(user.role.getRoleId() == 1) {
                new TeacherMainMenuController().run();
            } else {
                /**
                 * pending completion of the use case
                 */
//                new StudentMainMenuController().run();
            }

        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("aborted login");
        }
    }
}