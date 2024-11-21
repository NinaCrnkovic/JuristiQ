package hr.algebra.juristiq.enums;


import lombok.Getter;


@Getter
public enum ActionType {


        SASTAVLJANJE_TUŽBE("Sastavljanje tužbe"),
        SASTAVLJANJE_ODGOVORA_NA_TUŽBU("Sastavljanje odgovora na tužbu"),
        SASTAVLJANJE_ŽALBE("Sastavljanje žalbe"),
        SASTAVLJANJE_PRIGOVORA("Sastavljanje prigovora"),
        SASTAVLJANJE_PODNESKA("Sastavljanje podneska"),
        PRISUSTVOVANJE_ROČIŠTU("Prisustvovanje ročištu"),
        SASTAVLJANJE_OVRŠNOG_PRIJEDLOGA("Sastavljanje ovršnog prijedloga"),
        ZASTUPANJE_PRED_SUDOM("Zastupanje pred sudom"),
        IZRADBA_STRATEGIJE("Izradba strategije za spor"),
        PREGOVARANJE("Pregovaranje s protustrankom"),
        PREDSTAVLJANJE_U_MEDIJACIJI("Predstavljanje u medijaciji");

        private final String displayName;

        ActionType(String displayName) {
                this.displayName = displayName;
        }

        public static ActionType fromString(String displayName) {
                for (ActionType type : ActionType.values()) {
                        if (type.displayName.equalsIgnoreCase(displayName)) {
                                return type;
                        }
                }
                throw new IllegalArgumentException("Unknown action type: " + displayName);
        }
}
