package game.player;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
public enum SecondaryRole {
    SOULMATE("Soulmate", "", "", "", "/images/tokens_role/Soulmate.png");

    @NonNull @Getter
    private final String roleName, roleDescription;

    @Getter
    private final String action, imagePath, tokenPath;
}
