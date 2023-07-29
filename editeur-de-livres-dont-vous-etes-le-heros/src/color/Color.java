package color;

public enum Color{
    BLUE_COLOR("\u001B[44m"), 
    RED_COLOR("\u001B[41m"),
    BLACK_COLOR("\u001B[40m"),
    BLACK_SCRIPTURE_COLOR("\u001B[30m"),
    GREEN_COLOR("\u001B[42m"),
    PURPLE_COLOR("\u001B[45m"),
    ORANGE_COLOR("\033[48:5:208m"),
    YELLOW_COLOR("\u001B[43m"),
    WHITE_COLOR("\u001B[47m"),
    RESET_COLOR("\u001B[0m");

    private final String colorCode;

    /**
        * Le constructeur permet d'instancier un objet Color
        * @param colorCode correspond à une String
    */
    Color(String colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString(){
        return this.colorCode;
    }
}