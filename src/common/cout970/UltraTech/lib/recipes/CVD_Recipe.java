package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;
import common.cout970.UltraTech.machines.tileEntities.CVDentity;
import net.minecraft.item.ItemStack;

public class CVD_Recipe{

	public static List<CVD_Recipe> recipes = new ArrayList<CVD_Recipe>();
	private final ItemStack input1;
	private final ItemStack input2;
	private final ItemStack output;
	
	public CVD_Recipe(ItemStack input1,ItemStack input2,ItemStack output){
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
	}

	public static boolean matches(CVDentity inv) {
		if(inv == null)return false;
		for(CVD_Recipe a :recipes){
			if(inv.getStackInSlot(0) != null && inv.getStackInSlot(1) != null){
				if(a.getImput1().itemID == inv.getStackInSlot(0).itemID && a.getImput2().itemID == inv.getStackInSlot(1).itemID){
					if(inv.getStackInSlot(2) == null)	
					return true;
					if(inv.getStackInSlot(2).itemID == a.getOutput().itemID && inv.getInventoryStackLimit() >= inv.getStackInSlot(2).stackSize + a.getOutput().stackSize)
					return true;
				}
			}
		}
		return false;
	}


	public static ItemStack getCraftingResult(CVDentity inv) {
		if(inv == null)return null;
		for(CVD_Recipe a :recipes){
			if(inv.getStackInSlot(0) != null && inv.getStackInSlot(1) != null){
				if(a.getImput1().itemID == inv.getStackInSlot(0).itemID && a.getImput2().itemID == inv.getStackInSlot(1).itemID){
					return a.getOutput();
				}
			}
		}
		return null;
	}


	public ItemStack getOutput() {
		return output;
	}

	public ItemStack getImput2() {
		return input2;
	}

	public ItemStack getImput1() {
		return input1;
	}

	public static void addRecipe(CVD_Recipe a){
		if(a.getImput1().stackSize == 0)a.input1.stackSize = 1;
		if(a.getImput2().stackSize == 0)a.input2.stackSize = 1;
		if(a.getOutput().stackSize == 0)a.output.stackSize = 1;
		
		if(!recipes.contains(a))
		recipes.add(a);
		
		CVD_Recipe b = new CVD_Recipe(a.getImput2(), a.getImput1(), a.getOutput());
		boolean flag = true;
		for(CVD_Recipe c :recipes){
			if(isEqual(c,b))flag = false;
		}
		if(flag)recipes.add(b);
	}

	private static boolean isEqual(CVD_Recipe c, CVD_Recipe b) {
		if(c.getImput1().itemID == b.getImput1().itemID){
			if(c.getImput2().itemID == b.getImput2().itemID){
				if(c.getOutput().itemID == b.getOutput().itemID)
					return true;
			}
		}
		return false;
	}

	public static ItemStack getResult(ItemStack itemstack) {
		if(itemstack == null)return null;
		for(CVD_Recipe a:recipes){
			if(a.getImput1().itemID == itemstack.itemID || a.getImput2().itemID == itemstack.itemID)
				return a.getOutput();
		}
		return null;
	}

}