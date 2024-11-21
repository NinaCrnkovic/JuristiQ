package hr.algebra.juristiq.enums;

import lombok.Getter;

@Getter
public enum Court {
    OPĆINSKI_SUD_U_ZAGREBU("Općinski sud u Zagrebu"),
    OPĆINSKI_SUD_U_SPLITU("Općinski sud u Splitu"),
    OPĆINSKI_SUD_U_RIJECI("Općinski sud u Rijeci"),
    OPĆINSKI_SUD_U_OSIJEKU("Općinski sud u Osijeku"),
    OPĆINSKI_SUD_U_ZADRU("Općinski sud u Zadru"),
    OPĆINSKI_SUD_U_PULI("Općinski sud u Puli"),
    OPĆINSKI_SUD_U_VARAŽDINU("Općinski sud u Varaždinu"),
    OPĆINSKI_SUD_U_KARLOVCU("Općinski sud u Karlovcu"),
    OPĆINSKI_SUD_U_DUBROVNIKU("Općinski sud u Dubrovniku"),
    OPĆINSKI_SUD_U_BJELOVARU("Općinski sud u Bjelovaru"),
    OPĆINSKI_SUD_U_POŽEGI("Općinski sud u Požegi"),
    OPĆINSKI_SUD_U_VUKOVARU("Općinski sud u Vukovaru"),
    ŽUPANIJSKI_SUD_U_ZAGREBU("Županijski sud u Zagrebu"),
    ŽUPANIJSKI_SUD_U_SPLITU("Županijski sud u Splitu"),
    ŽUPANIJSKI_SUD_U_RIJEKI("Županijski sud u Rijeci"),
    ŽUPANIJSKI_SUD_U_OSIJEKU("Županijski sud u Osijeku"),
    ŽUPANIJSKI_SUD_U_ZADRU("Županijski sud u Zadru"),
    ŽUPANIJSKI_SUD_U_PULI("Županijski sud u Puli"),
    ŽUPANIJSKI_SUD_U_VARAŽDINU("Županijski sud u Varaždinu"),
    ŽUPANIJSKI_SUD_U_KARLOVCU("Županijski sud u Karlovcu"),
    ŽUPANIJSKI_SUD_U_DUBROVNIKU("Županijski sud u Dubrovniku"),
    TRGOVAČKI_SUD_U_ZAGREBU("Trgovački sud u Zagrebu"),
    TRGOVAČKI_SUD_U_SPLITU("Trgovački sud u Splitu"),
    TRGOVAČKI_SUD_U_RIJEKI("Trgovački sud u Rijeci"),
    TRGOVAČKI_SUD_U_OSIJEKU("Trgovački sud u Osijeku"),
    TRGOVAČKI_SUD_U_VARAŽDINU("Trgovački sud u Varaždinu"),
    TRGOVAČKI_SUD_U_BJELOVARU("Trgovački sud u Bjelovaru"),
    VISOKI_TRGOVAČKI_SUD("Visoki trgovački sud"),
    VISOKI_PREKRŠAJNI_SUD("Visoki prekršajni sud"),
    USTAVNI_SUD("Ustavni sud"),
    VRHOVNI_SUD("Vrhovni sud"),
    UPRAVNI_SUD_U_ZAGREBU("Upravni sud u Zagrebu"),
    UPRAVNI_SUD_U_SPLITU("Upravni sud u Splitu"),
    UPRAVNI_SUD_U_RIJEKI("Upravni sud u Rijeci"),
    UPRAVNI_SUD_U_OSIJEKU("Upravni sud u Osijeku"),
    VISOKI_UPRAVNI_SUD("Visoki upravni sud");

    private final String displayName;

    Court(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Court fromString(String displayName) {
        for (Court court : Court.values()) {
            if (court.displayName.equals(displayName)) {
                return court;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name " + displayName);
    }
}
