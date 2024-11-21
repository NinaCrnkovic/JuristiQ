package hr.algebra.juristiq.enums;


import lombok.Getter;

@Getter
public enum NonLitigationActionType {

    SASTAVLJANJE_OPORUKE("Sastavljanje oporuke"),
    SASTAVLJANJE_UGOVORA("Sastavljanje ugovora"),
    UREĐENJE_VLASNIŠTVA("Uređenje vlasništva"),
    KONZULTACIJE("Konzultacije"),
    MEDIJACIJA("Medijacija"),
    PISANJE_OPOMENE("Pisanje opomene"),
    PODNOŠENJE_PRITUŽBE("Podnošenje pritužbe"),
    SAVJETOVANJE("Savjetovanje"),
    IZRADBA_DOKUMENTACIJE("Izradba dokumentacije"),
    OVJERA_DOKUMENATA("Ovjera dokumenata"),
    REVIZIJA_UGOVORA("Revizija ugovora"),
    PRIPREMA_DOKUMENATA("Priprema dokumenata za postupak"),
    DRUGO("Drugo");

    private final String displayName;

    NonLitigationActionType(String displayName) {
        this.displayName = displayName;
    }

    public static NonLitigationActionType fromString(String displayName) {
        for (NonLitigationActionType type : NonLitigationActionType.values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown non-litigation action type: " + displayName);
    }
}