package net.petersil98.utilcraft.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class EmptyEntity extends Entity {

    private static final EntityDataAccessor<Integer> DATA_AGE = SynchedEntityData.defineId(EmptyEntity.class, EntityDataSerializers.INT);
    protected int age;

    public EmptyEntity(EntityType<? extends EmptyEntity> type, Level world) {
        super(type, world);
    }

    public EmptyEntity(Level world, double x, double y, double z) {
        this(UtilcraftEntities.EMPTY_ENTITY.get(), world);
        this.setPos(x, y, z);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_AGE, 0);
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("Age", this.getAge());
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setAge(compoundTag.getInt("Age"));
    }

    @Override
    @Nonnull
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        this.age++;
        if(this.age > 200) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public boolean isCurrentlyGlowing() {
        return true;
    }

    public void setAge(int age) {
        this.age = age;
        this.entityData.set(DATA_AGE, age);
    }

    public int getAge() {
        return this.age;
    }
}
