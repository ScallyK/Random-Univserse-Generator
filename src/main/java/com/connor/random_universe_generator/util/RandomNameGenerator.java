package com.connor.random_universe_generator.util;

public class RandomNameGenerator {

    public static String generateGalaxyName() {

        String[] prefix = {"Sombrero", "Whirlpool", "Pinwheel", "Centaurus A", "Messier 87", "Triangulum", "Cartwheel", "Black Eye", "Cigar", "Sculptor", "Tadpole", "Comet", "Sunflower", "Virgo A", "TFC", "LTD", "NPB", "NTB", "NGX", "NGA"};
        String[] suffix = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};

        return prefix[(int) (Math.random() * prefix.length)] + " " + suffix[(int) (Math.random() * suffix.length)];
    }

    public static String generateStarSystemName() {

        String[] prefixes = {"DGU", "WRE", "17", "40", "LTS", "PRO", "EQU"};
        String[] suffixes = {"Cygni", "Draconis", "Eldoni", "Aldeberan", "HV-D", "c0,", "XJ-9", "ZQ-4", "LMN-12", "QRS-7", "TUV-3", "WXY-8", "NOP-5", "GHI-6", "JKL-2", "STU-1", "VWX-0", "YZ-11", "FGH-10", "BCD-14"};

        String prefix = prefixes[(int) (Math.random() * prefixes.length)];
        String suffix = suffixes[(int) (Math.random() * suffixes.length)];

        return prefix + " " + suffix + " System";
    }

    public static String generateStarName() {

        String[] prefixes = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta", "Iota", "Kappa", "Lambda", "Mu", "Nu", "Xi", "Omicron", "Pi", "Rho", "Sigma", "Tau", "Upsilon"};
        String[] suffixes = {"Centauri", "Proxima", "Sirius", "Vega", "Altair", "Rigel", "Betelgeuse", "Polaris", "Aldebaran", "Spica", "Antares", "Fomalhaut", "Deneb", "Arcturus", "Capella", "Regulus", "Castor", "Pollux", "Mimosa", "Alnitak"};

        String prefix = prefixes[(int) (Math.random() * prefixes.length)];
        String suffix = suffixes[(int) (Math.random() * suffixes.length)];

        return prefix + " " + suffix;
    }

    public static String generatePlanetName() {

        // Set amount of prefixes and suffixes
        String[] prefixes = {"Aqua", "Terra", "Luna", "Stella", "Nova", "Zeta", "Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Omega", "Orion", "Vega", "Sirius", "Altair", "Rigel", "Procyon", "Deneb", "Antares"};
        String[] suffixes = {"Prime", "Secundus", "Tertius", "IV", "V", "VI", "VII", "VIII", "IX", "X", "Major", "Minor", "Centauri", "Nebula", "Cluster", "Void", "Haven", "Sanctuary", "Outpost", "Colony"};

        // Generates prefix and suffix by random selection
        String prefix = prefixes[(int) (Math.random() * prefixes.length)];
        String suffix = suffixes[(int) (Math.random() * suffixes.length)];

        return prefix + " " + suffix;
    }

    public static String generateMoonName() {

        String[] prefixes = {"Luna", "Selene", "Io", "Europa", "Ganymede", "Callisto", "Titan", "Rhea", "Iapetus", "Dione", "Tethys", "Enceladus", "Mimas", "Miranda", "Ariel", "Umbriel", "Oberon", "Titania", "Charon", "Nix"};
        String[] suffixes = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};

        String prefix = prefixes[(int) (Math.random() * prefixes.length)];
        String suffix = suffixes[(int) (Math.random() * suffixes.length)];

        return prefix + " " + suffix;
    }

    public static String generateLifeformName() {

        String[] syllables = {"zur", "gon", "zol", "na", "ga", "kyp", "ti", "nian", "can", "as", "gar", "ian", "mar", "tian", "tar", "gon", "phy", "lon", "dra", "ven", "tar", "quor", "zen", "mir", "sol", "lun", "ar"};

        StringBuilder name = new StringBuilder();

        int syllableCount = 2 + (int) (Math.random() * 3); // names with 2 to 4 syllables

        for (int i = 0; i < syllableCount; i++) {
            name.append(syllables[(int) (Math.random() * syllables.length)]);
        }

        // Capitalize the first letter
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    // Generates a random hex color code
    public static String randomHexColorGenerator(){
        String letters = "0123456789ABCDEF";
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += letters.charAt((int) Math.floor(Math.random() * 16));
        }
        return color;
    }

}
