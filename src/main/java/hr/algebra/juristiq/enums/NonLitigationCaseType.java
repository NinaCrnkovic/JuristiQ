package hr.algebra.juristiq.enums;

import hr.algebra.juristiq.intefaces.CaseTypeInterface;

public enum NonLitigationCaseType implements CaseTypeInterface {
    UGOVORI("Ugovori"),
    OPORUKA("Oporuka"),
    KONZULTACIJE ("Konzultacije"),
    MEDIJACIJA("Medijacija"),

    DRUGO("Drugo");

    private final String displayName;

    NonLitigationCaseType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public static NonLitigationCaseType fromString(String displayName) {
        for (NonLitigationCaseType type : NonLitigationCaseType.values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown display name: " + displayName);
    }
}