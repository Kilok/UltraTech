package common.cout970.UltraTech.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;

public class FortuneUpgrade extends UT_Item{

	public FortuneUpgrade(String name) {
		super(name);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{

		if(par3World.getTileEntity(x, y, z) instanceof MinerEntity){

			MinerEntity te = (MinerEntity) par3World.getTileEntity(x, y, z);
			if(te.fortuneUpgrades < 3){	
				te.hasFortuneUpgrades = true;
				te.fortuneUpgrades++;
				par1ItemStack.splitStack(1);
				return true;
			}
		}
		return false;
	}
}
