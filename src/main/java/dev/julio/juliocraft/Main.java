package dev.julio.juliocraft;

import dev.julio.juliocraft.core.Engine;
import dev.julio.juliocraft.core.IAppLogic;
import dev.julio.juliocraft.core.Window;
import dev.julio.juliocraft.core.graph.Render;
import dev.julio.juliocraft.core.scene.Scene;

public class Main implements IAppLogic {
    public static void main(String[] args) {
        Main main = new Main();
        Engine gameEng = new Engine("JulioCraft", new Window.WindowOptions(), main);
        gameEng.start();
    }

    @Override
    public void cleanup() {}

    @Override
    public void init(Window window, Scene scene, Render render) {}

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {}

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {}


}
