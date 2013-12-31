package common.cout970.UltraTech.machines.tileEntities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import common.cout970.UltraTech.misc.SyncObject;
import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class ReactorTankEntity extends ReactorWallEntity implements IFluidHandler,IFluidTank{

	public ReactorTankEntity(){
		super();
		this.capacity = 16000;
	}
	
	@Override
	public void updateEntity(){
		if(update){
			update = false;
			sendNetworkUpdate();
		}
		if(liquid != null){
			if(liquid.amount != last){
				sendNetworkUpdate();
				last = liquid.amount;
			}
		}
	}
	
//	//FLUIDS
	public FluidStack liquid;
	private final int capacity;
	private int last = 69;
	private boolean update = true;
	
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		
		if (resource == null) {
			return 0;
		}
		if(resource.getFluid().getBlockID() != Block.waterStill.blockID)return 0;
		if (!doFill)
		{
			if (liquid == null)
			{
				return Math.min(capacity, resource.amount);
			}

			if (!liquid.isFluidEqual(resource))
			{
				return 0;
			}

			return Math.min(capacity - liquid.amount, resource.amount);
		}

		if (liquid == null)
		{
			
			liquid = new FluidStack(resource, Math.min(capacity, resource.amount));


			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));

			return liquid.amount;
		}

		if (!liquid.isFluidEqual(resource))
		{
			return 0;
		}
		int filled = capacity - liquid.amount;
		if (resource.amount < filled)
		{
			liquid.amount += resource.amount;
			filled = resource.amount;
		}
		else
		{
			liquid.amount = capacity;
		}


		FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));

		return filled;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if (liquid == null)
        {
            return null;
        }

        int drained = maxDrain;
        if (liquid.amount < drained)
        {
            drained = liquid.amount;
        }

        FluidStack stack = new FluidStack(liquid, drained);
        if (doDrain)
        {
            liquid.amount -= drained;
            if (liquid.amount <= 0)
            {
                liquid = null;
            }
                FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));
        }
        return stack;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
			return null;
		if (!resource.isFluidEqual(liquid))
			return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection direction) {
		
		return new FluidTankInfo[]{new FluidTankInfo(liquid, capacity)};
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	public int getFluidLightLevel() {
		FluidStack tankFluid = liquid;
		return tankFluid == null ? 0 : tankFluid.getFluid().getLuminosity(tankFluid);
	}

	public FluidStack getFluid() {
		return liquid;
	}

	public int getCapacity() {
		return capacity;
	}

	public SyncObject getAmount() {
		if(liquid == null)return null;
		SyncObject obj = new SyncObject();
		obj.setVar1((int)(((float)liquid.amount)*100/((float)capacity)));
		obj.setVar2(liquid.getFluid().getBlockID());
		return obj;
	}

	@Override
	public int getFluidAmount() {
		if(liquid == null)return 0;
		return liquid.amount;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(liquid, capacity);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return this.fill(null, resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return this.drain(null, maxDrain, doDrain);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);
		
		NBTTagList tagList = nbtTagCompound.getTagList("Fuid_UT");
		NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(0);
		int amount = tagCompound.getInteger("Water");
		if(amount != 0){
			liquid = new FluidStack(tagCompound.getInteger("ID"),amount);
		}
		sendNetworkUpdate();
	}
	

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound = new NBTTagCompound();
		if(liquid == null){
			tagCompound.setInteger("Water", 0);
			tagCompound.setInteger("ID", 0);
		tagList.appendTag(tagCompound);
		}else{
			tagCompound.setInteger("Water", liquid.amount);
			tagCompound.setInteger("ID", liquid.fluidID);
			tagList.appendTag(tagCompound);
		}
		nbtTagCompound.setTag("Fuid_UT", tagList);
	}
	
	
	
	public void sendNetworkUpdate(){
		PacketDispatcher.sendPacketToAllPlayers(getPacket());
	}

	private Packet getPacket() {
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		try {
			data.writeInt(this.xCoord);
			data.writeInt(this.yCoord);
			data.writeInt(this.zCoord);
			if(liquid != null){
				data.writeInt(liquid.amount);
				data.writeInt(liquid.fluidID);
			}else{
				data.writeInt(0);
				data.writeInt(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "UltraTech2";
		packet.data = bytes.toByteArray();
		packet.length = packet.data.length;
		return packet;
	}
}