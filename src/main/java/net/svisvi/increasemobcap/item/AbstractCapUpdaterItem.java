package net.svisvi.increasemobcap.item;

import net.svisvi.increasemobcap.IncreaseMobCap;
import net.svisvi.increasemobcap.network.ModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.LevelAccessor;

public class AbstractCapUpdaterItem extends Item {
    public AbstractCapUpdaterItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }
    public int getVar(LevelAccessor world){
        return ModVariables.MapVariables.get(world).Monster_cap;
    }
    public void setVar(LevelAccessor world, int n){
        ModVariables.MapVariables.get(world).Monster_cap = n;
        ModVariables.MapVariables.get(world).syncData(world);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (entity == null)
            return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
        IncreaseMobCap.LOGGER.info((entity instanceof ServerPlayer _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getDisplayName().getString());
        int n = (int) new Object() {
            double convert(String s) {
                s = s.substring(1, s.length() - 1);
                try {
                    return Double.parseDouble(s.trim());
                } catch (Exception e) {
                }
                return 0;
            }
        }.convert((entity instanceof ServerPlayer _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getDisplayName().getString());
        //System.out.println("Interaction: " + n);
        this.setVar(world, n);

        return ar;
    }
}

