package kingsheep;

class KingSheep {
    public static void main(String[] args) {
        if (args.length != 3)
            System.err.println("usage: KingSheep map ai1 ai2");
        else
            new Simulator(args[0], args[1], args[2]);
    }
}
