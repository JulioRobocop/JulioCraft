package dev.julio.juliocraft.core;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ShaderProgram {
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    public ShaderProgram(String vertexFile, String fragmentFile) {
        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            throw new RuntimeException("Link failed: " + GL20.glGetProgramInfoLog(programID, 1024));
        }
        GL20.glValidateProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            throw new RuntimeException("Link failed: " + GL20.glGetProgramInfoLog(programID, 1024));
        }
    }

    public void start() {
        GL20.glUseProgram(programID);
    }
    public void stop() {
        GL20.glUseProgram(0);
    }
    public void cleanup() {
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    private static int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try (InputStream in = ShaderProgram.class.getResourceAsStream(file)) {
            if (in == null) {
                throw new IllegalArgumentException("Can't find the shader on classpath: " + file);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line;
            while((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load the shader: " + file, e);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        int success = GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS);
        if (success == GL11.GL_FALSE) {
            throw new RuntimeException("Compiling error: " + file + ":\n" + GL20.glGetShaderInfoLog(shaderID, 1024));
        }
        return shaderID;
    }
}
