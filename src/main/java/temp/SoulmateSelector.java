package temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

public class SoulmateSelector{

    static ArrayList<Player> list = new ArrayList<Player>();
    static ArrayList<Pair<Player, Player>> pairs = new ArrayList<Pair<Player, Player>>();

    void printPairs(){
        for (var p : pairs) {
            System.out.printf("%s(%s) & %s(%s)\n", 
            p.getLeft().getName(), 
                p.getLeft().getSide(), 
                p.getRight().getName(), 
                p.getRight().getSide());            
        }
    }

    private Player removeRandom(List<Player> list, Random rng) {
        int index = rng.nextInt(list.size());
        return list.remove(index);
    }

    void generatePairs(int pairCount, boolean GG_ENABLED, boolean GE_ENABLED, boolean EE_ENABLED){
        if (pairCount > list.size() / 2){
            int errorCount = pairCount;
            pairCount = list.size() / 2;
            System.out.println("""
                Invalid Pair Amount (%d)
                Total Players: %d
                Pair Amount switched automatically to maximum (%d)
                """
                .formatted(errorCount, list.size(), pairCount)
            );
            return;
        }
        
        Random rng = new Random();
        List<Player> goods = list.stream().filter(p -> p.getSide().equals("G")).toList();
        List<Player> evils = list.stream().filter(p -> !p.getSide().equals("G")).toList();
        
        for (int i = 0; i < pairCount; i++) {
            List<String> choicesEnabled = new ArrayList<>();

            if (GG_ENABLED && goods.size() >= 2) choicesEnabled.add("GG");
            if (GE_ENABLED && goods.size() > 0 && evils.size() > 0) choicesEnabled.add("GE");
            if (EE_ENABLED && evils.size() >= 2) choicesEnabled.add("EE");

            int pairTypeINT = rng.nextInt(choicesEnabled.size());
            String pairType = choicesEnabled.get(pairTypeINT); 

            Player p1 = null, p2 = null;
            switch (pairType) {
                case "GG" -> {
                    p1 = removeRandom(goods, rng);
                    p2 = removeRandom(goods, rng);
                }
                case "GE" -> {
                    p1 = removeRandom(goods, rng);
                    p2 = removeRandom(evils, rng);
                }
                case "EE" -> {
                    p1 = removeRandom(evils, rng);
                    p2 = removeRandom(evils, rng);
                }
            }

            list.remove(p1); 
            list.remove(p2); 
            pairs.add(Pair.of(p1, p2));
        }
    }

    public static void main(String[] args) throws Exception {

        //temporary list. i dont know how to connect it to ur code so i temporarily made one.
        list.add(new Player("Wayne","G"));
        list.add(new Player("PJ","G"));
        list.add(new Player("Xavier","G"));
        list.add(new Player("Pat","G"));

        list.add(new Player("Wilson","E")); 
        list.add(new Player("Ricia","E")); 
        list.add(new Player("Claire","E")); 
        list.add(new Player("Warren","E"));
        list.add(new Player("Ryan Goslink","E"));

        SoulmateSelector ss = new SoulmateSelector();
         
        ss.generatePairs(4, true, true , true);
                //so the parameters, (no. of pair, GG T/F, GE T/F, and EE T/f).
        ss.printPairs();
                //only prints right now but it works. change this nalang in the future?


                //man this was harder than i thought but it went well. learned a lot from ts highkenuinely.
                // what am i talm bout? yes.


                // ryan goslink: //Erm i have a question!, if the pair exceeds total amount of players, will the code still work?
                // literally me: //yup.
    }
}