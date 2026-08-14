package dev.julio.juliocraft.core;

import dev.julio.juliocraft.core.graph.Render;
import dev.julio.juliocraft.core.scene.Scene;

public interface IAppLogic {
    void cleanup();
    void init(Window window, Scene scene, Render render);
    void input(Window window, Scene scene, long diffTimeMillis);
    void update(Window window, Scene scene, long diffTimeMillis);
}
