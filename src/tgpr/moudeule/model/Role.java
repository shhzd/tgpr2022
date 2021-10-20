package tgpr.moudeule.model;

public enum Role {
    ADMIN("Admin", 0),
    TEACHER("Teacher", 1),
    STUDENT("Student", 2);

    private String label;
    private int roleId;

    Role(String label, int roleId) {
        this.label = label;
        this.roleId = roleId;
    }

    Role(int roleId) {
        this.roleId = roleId;
        this.label = getLabel(roleId);
    }

    public static String getLabel(int i) {
        String result = "";
        for(Role role : Role.values()) {
            if(role.roleId == i);
                result = role.getLabel();
        }
        return result;
    }

    public static Role getRole(int i) {
        return Role.values()[i];
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
