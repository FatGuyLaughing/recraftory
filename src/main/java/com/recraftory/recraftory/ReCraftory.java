package com.recraftory.recraftory;

import com.mojang.logging.LogUtils;
import com.recraftory.recraftory.setup.ClientSetup;
import com.recraftory.recraftory.setup.ModSetup;
import com.recraftory.recraftory.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ReCraftory.MODID)
public class ReCraftory {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // Create a MODID variable that can be used everywhere.
    public static final String MODID = "recraftory";

    public ReCraftory() {
        // Register the deferred registry.
        Registration.init();

        // Register the setup method for mod loading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
    }

}
