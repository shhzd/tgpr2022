package tgpr.moudeule.controller;

import tgpr.moudeule.MoudeuleApp;
import tgpr.moudeule.model.Role;
import tgpr.moudeule.model.User;
import tgpr.moudeule.view.SignupView;
import tgpr.moudeule.view.View;

import java.time.LocalDate;
import java.util.List;

import static tgpr.moudeule.model.User.makeNewStudent;

public class SignupController extends Controller {

    private final SignupView view = new SignupView();
    private boolean okToSave = false;

    @Override
    public void run() {
        try {
            List<String> errors;
            User user = makeNewStudent();
            View.Action res;
            view.displayHeader();
            view.displayMenu();
            try {
                askPseudo(user);
                askPassword(user);
                askFullName(user);
                askBirthDate(user);
            } catch (View.ActionInterruptedException e) {
            }
            if (okToSave) {
                res = view.askForConfirmation();
                switch (res.getAction()) {
                    case 'O':
                        user.save();
                        MoudeuleApp.setLoggedUser(user);
                        new StudentMainMenuController().run();
                }
            }
        } catch (View.ActionInterruptedException e) {
        }
    }

    private void askPseudo(User user) {
        String pseudo = "";
        do {
            pseudo = view.askPseudo(user.getPseudo());
            String error = User.isValidAvailablePseudo(pseudo);
            if (error != null)
                view.warning(error);
        } while (User.isValidAvailablePseudo(pseudo) != null);
        user.setPseudo(pseudo);
    }

    private void askPassword(User user) {
        String password = "";
        String passwordConfirm = "";
        do {
            password = view.askPassword(user.getPassword());
            String error = User.isValidPassword(password);
            if (error != null) {
                view.warning(error);
            }
        } while (User.isValidPassword(password) != null);
        do {
            passwordConfirm = view.askPasswordConfirm(user.getPassword());
            String error = User.isValidPasswordConfirm(passwordConfirm, password);
            if (error != null)
                view.warning(error);
        } while (User.isValidPasswordConfirm(passwordConfirm, password) != null);
        user.setPassword(password);
    }

    private void askFullName(User user) {
        String fullname = "";
        do {
            fullname = view.askFullname(user.getFullname());
            if (fullname.length() == 0)
                view.warning("Entrez votre nom complet");
        } while (fullname.length() == 0);
        user.setFullname(fullname);
    }

    private LocalDate askBirthDate(User user) {
        LocalDate birthDate;
        do {
            birthDate = view.askBirthDate(user.getBirthdate());
            String error = User.isValidBirthdate(birthDate);
            if (error != null)
                view.warning(error);
        } while (User.isValidBirthdate(birthDate).length() > 0);
        okToSave = true;
        return birthDate;
    }
}