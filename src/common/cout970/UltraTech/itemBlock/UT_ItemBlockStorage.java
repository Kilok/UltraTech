package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockStorage extends UT_ItemBlock{

	public UT_ItemBlockStorage(int par1) {
		super(par1);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "storage"+itemstack.getItemDamage();
	}

}
