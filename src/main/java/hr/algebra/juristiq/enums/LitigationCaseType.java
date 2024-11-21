package hr.algebra.juristiq.enums;

import hr.algebra.juristiq.intefaces.CaseTypeInterface;
import lombok.Getter;

@Getter
public enum LitigationCaseType implements CaseTypeInterface {

    KAZNENO("Kazneno"),
    OBITELJSKO("Obiteljsko"),
    RADNO("Radno"),
    GRAĐANSKO("Građansko"),
    TRGOVAČKO("Trgovačko"),

    PREKRŠAJNO("Prekršajno"),
    NASLJEDNO("Nasljedno"),
    PARNIČNO("Parnično"),

    OVRSNO("Ovršno");

    private final String displayName;

    LitigationCaseType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static LitigationCaseType fromString(String displayName) {
        for (LitigationCaseType type : LitigationCaseType.values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name " + displayName);
    }
}
