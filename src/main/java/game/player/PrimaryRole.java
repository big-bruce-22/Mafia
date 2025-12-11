package game.player;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
public enum PrimaryRole {

    CIVILIAN    ("Civilian", "Stay alive and vote out the Evils", Side.GOOD, "Goodluck...", null, "/images/tokens_role/Civilian.png"),

    DETECTIVE   ("Detective", "Find the Evils and save the day", Side.GOOD, "Investigate someone every night", null, "/images/tokens_role/Detective.png"),
    DOCTOR      ("Doctor", "Protect the Good", Side.GOOD, "Heal someone every night", null, "/images/tokens_role/Doctor.png"),
    PSYCHO      ("Psycho", "Bring someone else down with you", Side.GOOD, "You're insane...", null, "/images/tokens_role/Psycho.png"),
    OFFICER     ("Officer", "Shoot First, Ask Later", Side.GOOD, "Find the Evils and bring Justice", null, "/images/tokens_role/Officer.png"),

    GODFATHER   ("Godfather", "You're the Boss", Side.EVIL, "Investiaget or Kill very night", null, "/images/tokens_role/Godfather.png"),
    KILLER      ("Killer", "Eliminate everyone and don't get caught", Side.EVIL, "Kill someone every night", null, "/images/tokens_role/Killer.png"),
    MURDERER    ("Murderer", "No bounds of killing", Side.EVIL, "Can perform a Daytime kill", null, "/images/tokens_role/Murderer.png");

    @NonNull @Getter
    private final String roleName, roleDescription;

    @NonNull @Getter
    private final Side side;

    @Getter
    private final String action, imagePath, tokenPath;

    public static List<PrimaryRole> getAll(Predicate<PrimaryRole> filter) {
        return Arrays.asList(values()).stream().filter(filter).toList();
    }
}
