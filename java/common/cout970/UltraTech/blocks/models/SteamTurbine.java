package common.cout970.UltraTech.blocks.models;

import api.cout970.UltraTech.Wpower.BlockConductor;
import buildcraft.api.tools.IToolWrench;
import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SteamTurbine extends BlockConductor{

	public SteamTurbine(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("SteamTurbine");
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
	{
		if(p.isSneaking())return true;
		SteamTurbineEntity e = (SteamTurbineEntity) w.getTileEntity(x, y, z);
		if(e != null){
			if(p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() instanceof IToolWrench){
				e.switchOrientation();
				return true;
			}
			p.openGui(UltraTech.instance, 13, w, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world,int a) {
		return new SteamTurbineEntity();
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:chasis1");
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.turbineRenderPass;
	}

}
