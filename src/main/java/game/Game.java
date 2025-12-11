package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import game.player.Player;
import game.player.RoleDistribution;

public class Game {

    private final Scanner scn = new Scanner(System.in);
    private final Random rand = new Random();

    public static void main(String[] args) {
        var game = new Game();
        var players = game.registerPlayer();
        game.start(players);
    }

    private List<Player> registerPlayer() {
        boolean debug = true;

        if (debug) {
            return new ArrayList<>(){{
                add(new Player("Wayne"));
                add(new Player("Pat"));
                add(new Player("Xav"));
                add(new Player("Aez"));
                add(new Player("Mark"));
                add(new Player("Ricia"));
                add(new Player("Ruth"));
                add(new Player("Pj"));
                add(new Player("Claire"));
                add(new Player("Arki"));
                add(new Player("Wilson"));
                add(new Player("Warren"));
                add(new Player("Jeremy"));
                add(new Player("Angelique"));
            }};
        }

        try {
            System.out.print("Enter how many players: ");
            int totalPlayers = scn.nextInt();
            
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < totalPlayers; i++) {
                System.out.printf("Enter player name: ");
                try {
                    String s = scn.nextLine();
                    if (s.isEmpty()) {
                        throw new Exception();
                    }
                    players.add(new Player(s));
                } catch (Exception _) {
                    System.out.println("Error in entered name, try again");
                    i--;
                    continue;
                }
            }

            System.out.println("Total Players: " + players.size());        
            return players;
        } catch (Exception e) {
            System.err.println("Exception occured: " + e.getMessage());
            return List.of();
        }
    }

    private void setRoles(List<Player> players) {
        var roleDistribution = RoleDistribution.DEFAULT_DISTRIBUTION.getRolesAsList(players.size());
        Collections.shuffle(players);
        Collections.shuffle(roleDistribution);
        for (var player : players) {
            player.setRole(roleDistribution.remove(rand.nextInt(0, roleDistribution.size())));
        }
    }

    private void start(List<Player> players) {
        for (int i = 0; i <= 100; i++) {
            System.out.print("\rLoading: " + i + "%");
            try {
                Thread.sleep(10);
            } catch (InterruptedException _) {

            }
        }
        System.out.println();
        setRoles(players);
        players.forEach(System.out::println);
    }
}

// Wayne
// Pat
// Xav
// Aez
// Mark
// Ricia
// Ruth
// Pj
// Claire
// Arki
// Wilson
// Warren
// Jeremy
// Angelique