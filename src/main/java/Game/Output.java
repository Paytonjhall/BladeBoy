package Game;

public class Output {
    public void print(String s){
        System.out.println(s);
    }
    public void printRed(String s){
        System.out.print("\u001B[31m" + s + "\u001B[0m");
    }
    public void printYellow(String s){
        System.out.print("\u001B[33m" + s + "\u001B[0m");
    }
    public void printGreen(String s){
        System.out.print("\u001B[32m" + s + "\u001B[0m");
    }
    public void printBlue(String s){
        System.out.print("\u001B[34m" + s + "\u001B[0m");
    }
    public void printPurple(String s){
        System.out.print("\u001B[35m" + s + "\u001B[0m");
    }
    public void printCyan(String s){
        System.out.print("\u001B[36m" + s + "\u001B[0m");
    }
    public void printWhite(String s){
        System.out.print("\u001B[37m" + s + "\u001B[0m");
    }
    public void printBlack(String s){
        System.out.print("\u001B[30m" + s + "\u001B[0m");
    }
    public void printBold(String s){
        System.out.print("\u001B[1m" + s + "\u001B[0m");
    }
    public void printUnderline(String s){
        System.out.print("\u001B[4m" + s + "\u001B[0m");
    }
    public void printBlink(String s){
        System.out.print("\u001B[5m" + s + "\u001B[0m");
    }
    public void printReverse(String s){
        System.out.print("\u001B[7m" + s + "\u001B[0m");
    }
    public void printInvisible(String s){
        System.out.print("\u001B[8m" + s + "\u001B[0m");
    }
    public void printBackgroundRed(String s){
        System.out.print("\u001B[41m" + s + "\u001B[0m");
    }
    public void printBackgroundYellow(String s){
        System.out.print("\u001B[43m" + s + "\u001B[0m");
    }
    public void printBackgroundGreen(String s){
        System.out.print("\u001B[42m" + s + "\u001B[0m");
    }
    public void printBackgroundBlue(String s){
        System.out.print("\u001B[44m" + s + "\u001B[0m");
    }
    public void printBackgroundPurple(String s){
        System.out.print("\u001B[45m" + s + "\u001B[0m");
    }
    public void printBackgroundCyan(String s){
        System.out.print("\u001B[46m" + s + "\u001B[0m");
    }
    public void printBackgroundWhite(String s){
        System.out.print("\u001B[47m" + s + "\u001B[0m");
    }
    public void printBackgroundBlack(String s){
        System.out.print("\u001B[40m" + s + "\u001B[0m");
    }
}
