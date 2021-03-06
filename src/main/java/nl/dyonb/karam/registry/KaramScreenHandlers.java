package nl.dyonb.karam.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import nl.dyonb.karam.Karam;
import nl.dyonb.karam.common.screen.DevNullScreenHandler;

public class KaramScreenHandlers {

    public static final ScreenHandlerType<DevNullScreenHandler> DEV_NULL_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(Karam.identifier("dev_null"), DevNullScreenHandler::new);

    public static void initialize() {
        // Not used here
    }

}
