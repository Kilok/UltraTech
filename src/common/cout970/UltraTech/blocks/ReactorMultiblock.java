package common.cout970.UltraTech.blocks;

import java.util.List;
import java.util.Random;

import common.cout970.UltraTech.TileEntities.Tier3.ReactorControllerEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorTankEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorWallEntity;
import common.cout970.UltraTech.TileEntities.Tier3.SteamTurbineEntity;
import common.cout970.UltraTech.TileEntities.Tier3.WaterBlockEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.energy.api.EnergyUtils;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.misc.IReactorPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ReactorMultiblock extends BlockContainer{

	public Icon icons[][];
	public int numBlocks = 6;
	
	public ReactorMultiblock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundMetalFootstep);
		setResistance(50);
		setHardness(2.0f);
		setUnlocalizedName("UT_ReactorMultiblock");
	}
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if(entityplayer.isSneaking()){
			return true;
		}else{
			TileEntity tile = world.getBlockTileEntity(i, j, k);
			if(tile != null){ 
				if(tile instanceof IReactorPart){
					IReactorPart p = (IReactorPart) tile;	
					p.onNeighChange();
					if(p.isStructure() && p.getReactor() != null){
						entityplayer.openGui(UltraTech.instance, 13, world, p.getReactor().xCoord, p.getReactor().yCoord, p.getReactor().zCoord);
					}
					return true;
				}
			}
		}

		return false;
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te != null){
			if(te instanceof IReactorPart)
				((IReactorPart)te).onNeighChange();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister IR){
		icons = new Icon[numBlocks][];
		icons[0] = new Icon[1];
		icons[1] = new Icon[2];
		icons[2] = new Icon[1];
		icons[3] = new Icon[2];
		icons[4] = new Icon[1];
		icons[5] = new Icon[1];
		icons[0][0] = IR.registerIcon("ultratech:reactor");
		icons[1][0] = IR.registerIcon("ultratech:reactorwall");
		icons[1][1] = IR.registerIcon("ultratech:reactorwallmulti");
		icons[2][0] = IR.registerIcon("ultratech:reactortank");
		icons[3][0] = IR.registerIcon("ultratech:reactorcontrolleroff");
		icons[3][1] = IR.registerIcon("ultratech:reactorcontrolleron");
		icons[4][0] = IR.registerIcon("ultratech:waterblock");
		icons[5][0] = IR.registerIcon("ultratech:turbine");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
	    if(meta == 0){
	    	return icons[0][0];
	    }if(meta == 1){
	    	return icons[1][0];
	    }if(meta == 2){
	    	return icons[2][0];
	    }if(meta == 3){
	    	return icons[3][0];
	    }if(meta == 4){
	    	return icons[4][0];
	    }if(meta == 5){
	    	return icons[5][0];
	    }
	    return icons[0][0];
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess BA, int x, int y, int z, int side)
	{
		TileEntity t = BA.getBlockTileEntity(x, y, z);
		if(t instanceof ReactorWallEntity){
			if(((ReactorWallEntity) t).isStructure())return icons[1][1];
		}if(t instanceof ReactorControllerEntity){
			if(((ReactorControllerEntity) t).meta == 1){return icons[3][1];}else{return icons[3][0];}
		}
		return this.getIcon(side, BA.getBlockMetadata(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World w) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		for (int ix = 0; ix < numBlocks; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	@Override
	public void onBlockAdded(World worldObj, int xCoord, int yCoord, int zCoord)
	{
		super.onBlockAdded(worldObj, xCoord, yCoord, zCoord);
		TileEntity t = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
		if(t instanceof IReactorPart){
			((IReactorPart)t).onNeighChange();
		}
		if(t instanceof Machine){
			EnergyUtils.onBlockAdded(worldObj, xCoord, yCoord, zCoord);
		}
	}
	
	public void onBlockPreDestroy(World worldObj, int xCoord, int yCoord, int zCoord, int meta) {
		TileEntity t = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
		if(t instanceof IReactorPart)((IReactorPart) t).onNeighChange();
		if(t instanceof Machine)EnergyUtils.onBlockPreDestroy(worldObj, xCoord, yCoord, zCoord, meta);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if(metadata == 0)return new ReactorEntity();
		if(metadata == 1)return new ReactorWallEntity();
		if(metadata == 2)return new ReactorTankEntity();
		if(metadata == 3)return new ReactorControllerEntity();
		if(metadata == 4)return new WaterBlockEntity();
		if(metadata == 5)return new SteamTurbineEntity();
		return null;
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		dropItems(world, x, y, z);
		TileEntity t = world.getBlockTileEntity(x, y, z);
		if(t instanceof IReactorPart){	
			((IReactorPart)t).desactivateBlocks();
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	 private void dropItems(World world, int x, int y, int z){
		 Random rand = new Random();
		 TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		 if (!(tileEntity instanceof IInventory)) {
			 return;
		 }
		 IInventory inventory = (IInventory) tileEntity;
		 for (int i = 0; i < inventory.getSizeInventory(); i++) {
			 ItemStack item = inventory.getStackInSlot(i);
			 if (item != null && item.stackSize > 0) {
				 float rx = rand.nextFloat() * 0.8F + 0.1F;
				 float ry = rand.nextFloat() * 0.8F + 0.1F;
				 float rz = rand.nextFloat() * 0.8F + 0.1F;
				 EntityItem entityItem = new EntityItem(world,
                      x + rx, y + ry, z + rz,
                      new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
				 if (item.hasTagCompound()) {
					 entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				 }
				 float factor = 0.05F;
				 entityItem.motionX = rand.nextGaussian() * factor;
				 entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				 entityItem.motionZ = rand.nextGaussian() * factor;
				 world.spawnEntityInWorld(entityItem);
				 item.stackSize = 0;
			 }
		 }
	 }

	 public int getRenderBlockPass()
	 {
		 return 0;
	 }

	 public boolean isOpaqueCube()
	 {
		 return false;
	 }

	 public boolean renderAsNormalBlock()
	 {
		 return true;
	 }
}
