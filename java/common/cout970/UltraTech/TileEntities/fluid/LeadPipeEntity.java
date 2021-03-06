package common.cout970.UltraTech.TileEntities.fluid;

import java.util.HashMap;
import java.util.Map;

import api.cout970.UltraTech.fluids.FluidUtils;
import api.cout970.UltraTech.fluids.Pipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class LeadPipeEntity extends Pipe implements IFluidHandler{

	public Map<ForgeDirection,IFluidHandler> tanks;
	
	public void updateEntity(){
		super.updateEntity();
		if(tanks == null){
			tanks = new HashMap<ForgeDirection,IFluidHandler>();
			for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
				TileEntity te = FluidUtils.getRelative(this, d);
				if(te instanceof IFluidHandler){
					tanks.put(d,(IFluidHandler) te);
				}
			}
		}

		if(getNetwork() != null)for(ForgeDirection f : ForgeDirection.VALID_DIRECTIONS){
			if(tanks.containsKey(f) && tanks.get(f) != null){
				for(IFluidHandler h : getNetwork().getTanks()){
					if(!tanks.containsValue(h)){
						FluidStack drained = tanks.get(f).drain(f.getOpposite(), 100, false);
						if(drained != null){
							int Do = 0;
							if((Do=h.fill(null, drained, false)) > 0){
								drained = tanks.get(f).drain(f.getOpposite(), Do, true);
								h.fill(null, drained, true);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onNetworkUpdate() {
		tanks = null;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{};
	}
}
