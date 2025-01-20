package at.conmunity.API.Enums;

public enum EGender{
    MALE("Männlich"),
    FEMALE("Weiblich"),
    DIVERSE("Divers");

    public final String label;

    EGender(String label) {
        this.label = label;
    }
}
