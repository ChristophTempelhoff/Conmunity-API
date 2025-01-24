package at.conmunity.API.Enums;

public enum EMemberType {
    TEMPORAL_MEMBER("Temporäres Mitglied"),
    ASSOCIATE_MEMBER("Außerordentliches Mitglied"),
    FULL_MEMBER("Ordentliches Mitglied");

    public final String label;

    EMemberType(String label) {
        this.label = label;
    }
}
