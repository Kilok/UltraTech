package common.cout970.UltraTech.TileEntities.intermod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import api.cout970.UltraTech.Vpower.CableType;
import api.cout970.UltraTech.Vpower.Machine;
import api.cout970.UltraTech.network.Net_Utils;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.UT_Utils;

public class DynamoEntity extends Machine{

	public ForgeDirection facing = ForgeDirection.UP;
	protected EnergyStorage storage = new EnergyStorage(32000);
	public static final int RF = 80;//RF => MeV
	public boolean on = false;
	public IEnergyHandler recep = null;

	public DynamoEntity(){
		super(CostData.Dynamo,true);
	}

	public void updateEntity(){
		super.updateEntity();
		
		if(getEnergy() >= 1 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= RF){
			this.removeEnergy(1d);
			storage.receiveEnergy(RF, false);
			on = true;
		}else{
			on = false;
		}

		if(!worldObj.isRemote && recep != null){
			int b = ((IEnergyHandler) recep).receiveEnergy(facing.getOpposite(), 80, false);
			if(b > 0){
				this.extractEnergy(facing, b, false);
			}
		}
	}
	public void updateReceptor(){
		TileEntity a = UT_Utils.getRelative(this, facing);
		if(a instanceof IEnergyHandler)recep = (IEnergyHandler) a;
	}

	public CableType getConection(ForgeDirection side) {
		if(side == facing.getOpposite())return CableType.BLOCK;
		if(side == facing)return CableType.NOTHING;
		return CableType.RIBBON_BOTTOM;
	}

	public boolean isWorking() {
		return on;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return from == facing;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}
	
	//Save and Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	public void switchOrientation() {
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(UT_Utils.getRelative(this, d) instanceof IEnergyHandler){
				facing = d;
				Net_Utils.sendUpdate(this);
				return;
			}
		}	
	}
}
