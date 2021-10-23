package tgpr.moudeule.controller;

import tgpr.moudeule.model.Role;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.SignupView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;
import java.util.List;

public class SignupController extends Controller {

    private SignupView view = new SignupView();

    public String askPseudo(String actual) {
        String pseudo;
        String error;
        do {
            pseudo = view.askPseudo(actual);
            error = User.isValidAvailablePseudo(pseudo);
            if (error != null) view.error(error);
        } while (error != null);
        return pseudo;
    }

    public void run() {
        try {
            View.Action res;
            List<String> errors;
            var user = new User();
            do {
                view.displayHeader();

                String pseudo = askPseudo(user.getPseudo());
                String password = view.askPassword(user.getPassword());
                String passwordConfirm = view.askPassword(user.getPassword());
                String fullname = view.askFullname(user.getFullname());
                LocalDate birthDate = view.askBirthDate(user.getBirthdate());

                user.setPseudo(pseudo);
                user.setPassword(password);
                user.setFullname(fullname);
                user.setBirthdate(birthDate);
                user.setRole(Role.STUDENT);

                errors = user.validate(passwordConfirm);
                if (errors.size() > 0)
                    view.showErrors(errors);
            } while (errors.size() > 0);

            res = view.askForAction();
            switch (res.getAction()) {
                case 'V' :
                    user.setPassword(user.getPassword());
                    user.save();
                    break;
                case 'A' :
                    new StartMenuController();
                    break;
            }
        } catch (View.ActionInterruptedException e) {
            view.pausedWarning("inscription abandonnée");
        }
    }

}
