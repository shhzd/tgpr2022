package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Role;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.SignupView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;
import java.util.List;

public class SignupController extends Controller {

    @Override
    public void run() {
        try {
            var view = new SignupView();
            List<String> errors;
            var user = new User();
            View.Action res;
            do {
                view.displayHeader();
                view.displayMenu();

                String pseudo;
                do {
                    pseudo = view.askPseudo(user.getPseudo());
                    String error = User.isValidAvailablePseudo(pseudo);
                    if (error != null)
                        view.showError(error);
                } while (User.isValidAvailablePseudo(pseudo) != null);

                String password;
                do {
                    password = view.askPassword(user.getPassword());
                    String error = User.isValidPassword(password);
                    if (error != null)
                        view.showError(error);
                }while (User.isValidPassword(password) != null);

                String passwordConfirm;
                do {
                    passwordConfirm = view.askPasswordConfirm(user.getPassword());
                    String error = User.isValidPasswordConfirm(passwordConfirm, password);
                    if (error != null)
                        view.showError(error);
                } while (User.isValidPasswordConfirm(passwordConfirm, password) != null);

                String fullname = view.askFullname(user.getFullname());

                LocalDate birthDate;
                do {
                    birthDate = view.askBirthDate(user.getBirthdate());
                    String error = User.isValidBirthdate(birthDate);
                    if (error != null)
                        view.showError(error);
                } while (User.isValidBirthdate(birthDate) == "impossible d'être né dans le futur" || User.isValidBirthdate(birthDate) == "avoir minimum 18 ans accomplis");

                user.setPseudo(pseudo);
                user.setPassword(password);
                user.setFullname(fullname);
                user.setBirthdate(birthDate);
                user.setRole(Role.STUDENT);

                errors = user.validate(passwordConfirm);
            } while (errors.size() > 0);

            res = view.askForAction();
            switch (res.getAction()) {
                case 'V' :
                    user.setPassword(user.getPassword());
                    user.save();
                    MoudeuleApp.setLoggedUser(user);
                    new StudentMainMenuController().run();
                    break;
                case 'A' :
                    new StartMenuController().run();
                    break;
            }
        } catch (View.ActionInterruptedException e) {

        }
    }

}
