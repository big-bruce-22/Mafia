package game.player;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Player {

    @NonNull @Getter
    private final String name;

    @NonNull @Setter @Getter
    private PrimaryRole role;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " is a " + role;
    }
}
