package game.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleDistribution {

    private Map<PrimaryRole, Integer> roleDistribution = new HashMap<>();

    public static final RoleDistribution DEFAULT_DISTRIBUTION = new RoleDistribution() {{
        setRoleDistribution(PrimaryRole.DETECTIVE, 2);
        setRoleDistribution(PrimaryRole.DOCTOR, 2);
        setRoleDistribution(PrimaryRole.PSYCHO, 2);

        setRoleDistribution(PrimaryRole.GODFATHER, 1);
        setRoleDistribution(PrimaryRole.KILLER, 1);
        setRoleDistribution(PrimaryRole.MURDERER, 1);
    }};
            
    public RoleDistribution setRoleDistribution(PrimaryRole role, int distribution) {
        roleDistribution.put(role, distribution);
        return this;
    }

    public List<PrimaryRole> getRolesAsList(int totalPlayers) {
        List<PrimaryRole> roles = new ArrayList<>();
    
        addToList(roles, roleDistribution, PrimaryRole.DETECTIVE);
        addToList(roles, roleDistribution, PrimaryRole.DOCTOR);
        addToList(roles, roleDistribution, PrimaryRole.PSYCHO);

        addToList(roles, roleDistribution, PrimaryRole.GODFATHER);
        addToList(roles, roleDistribution, PrimaryRole.KILLER);
        addToList(roles, roleDistribution, PrimaryRole.MURDERER);

        var civilianArray = new PrimaryRole[totalPlayers - roles.size()];
        Arrays.fill(civilianArray, PrimaryRole.CIVILIAN);

        roles.addAll(List.of(civilianArray));

        return roles;
    }

    private <K, V> void addToList(List<K> list, Map<K, Integer> map, K key) {
        for (int i = 0; i < map.get(key); i++) {
            list.add(key);
        }
    }
}
