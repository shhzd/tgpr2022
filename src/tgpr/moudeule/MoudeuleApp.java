package tgpr.moudeule;

        import tgpr.moudeule.controller.LoginController;
        import tgpr.moudeule.model.User;
        import tgpr.moudeule.model.Model;
        import tgpr.moudeule.view.ErrorView;



public class MoudeuleApp {
    private static User loggedUser = null;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        MoudeuleApp.loggedUser = loggedUser;
    }

    public static boolean isLogged() {
        return loggedUser != null;
    }

    public static void logout() {
        setLoggedUser(null);
    }

    public static void main(String[] args) {
        if (!Model.checkDb())
            new ErrorView("Database is not available").close();
        else {
            new LoginController().run();
//            new TeacherMainMenuController().run();
        }

    }

}