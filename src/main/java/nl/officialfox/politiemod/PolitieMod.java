package nl.officialfox.politiemod;

import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Mod(PolitieMod.MODID)
public class PolitieMod {

    @Mod.Metadata(mcVersion = "1.19.4")
    public static final String MODID = "politiemod";
    private List<UUID> approvedPlayerID = Arrays.asList(
            UUID.fromString("12ef819e-1776-4e38-a355-f71bb0fe2e05")
    );

    public boolean isPlayerApproved(UUID playerUUID){
        return approvedPlayerID.contains(playerUUID);
    }

}
