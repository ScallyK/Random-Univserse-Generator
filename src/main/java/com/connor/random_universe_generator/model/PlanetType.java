package com.connor.random_universe_generator.model;

public enum PlanetType {
    CHTHONIAN("Chthonian planet"),
    CARBON("Carbon planet"),
    CORELESS("Coreless planet"),
    DESERT("Desert planet"),
    GAS_DWARF("Gas dwarf"),
    GAS_GIANT("Gas giant"),
    HELIUM("Helium planet"),
    HYCEAN("Hycean planet"),
    ICE_GIANT("Ice giant (also appears here)"),
    ICE("Ice planet"),
    IRON("Iron planet"),
    LAVA("Lava planet"),
    OCEAN("Ocean planet"),
    PROTOPLANET("Protoplanet"),
    PUFFY("Puffy planet"),
    SOOT("Soot planet"),
    STEAM_WORLD("Steam world"),
    SUPER_PUFF("Super-puff"),
    SILICATE("Silicate planet"),
    TERRESTRIAL("Terrestrial planet (rocky / telluric)"),
    CATASTROPHICALLY_EVAPORATING("Catastrophically evaporating planet"),
    EARTH_ANALOG("Earth analog (Super Habitable Planet)"),
    HYPOTHETICAL_PLANET("Hypothetical planet (Unconfirmed)"),;


    private final String displayName;

    PlanetType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
