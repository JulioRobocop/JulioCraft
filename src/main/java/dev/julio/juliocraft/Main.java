package dev.julio.juliocraft;

import dev.julio.juliocraft.core.Window;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFW.*;

public class Main {
    public static void main(String[] args) {
        double prevTime = 0.0;
        double crntTime = 0.0;
        double timeDiff;
        int counter = 0;
        System.out.println(Version.getVersion());
        Window window = new Window("Hello World", 1600, 900, false);
        window.init();
        while(!window.windowShouldClose()) {
            crntTime = GLFW.glfwGetTime();
            timeDiff = crntTime - prevTime;
            counter++;
            if (timeDiff >= 1.0) {
                double FPS =   counter / timeDiff;
                double ms =  (timeDiff / counter) * 1000.0;
                window.setTitle(String.format("JulioCraft - %.0f FPS / %.2f ms", FPS, ms));
                prevTime = crntTime;
                counter = 0;
            }
            window.update();

        }
        window.cleanup();
    }
}
