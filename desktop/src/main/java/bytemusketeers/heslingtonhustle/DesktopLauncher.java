package bytemusketeers.heslingtonhustle;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

/**
 * The interface to {@link HeslingtonHustle} for desktop users of LibGDX
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 * @see HeslingtonHustle
 * @apiNote On macOS, your application needs to be started with the {@code -XstartOnFirstThread} JVM argument.
 */
public class DesktopLauncher {
    /**
     * The entry point for desktop LibGDX runners
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        // get user's monitor resolution
        DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        long primaryMonitor = GLFW.glfwGetPrimaryMonitor();
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(primaryMonitor);

        // set windowed mode using the monitor's resolution, leaving space for the taskbar
        int windowX = 0;
        if (vidMode != null)
            windowX = vidMode.width();
        int windowY = displayMode.height - 90;
        config.setWindowedMode(windowX, windowY);

        // General window configuration
        config.setWindowPosition(0, 40);
        config.setResizable(true);
        config.setForegroundFPS(60);
        // config.setDecorated(false); //this can be used to remove the window

        // Set window icons for different platforms
        config.setWindowIcon(Files.FileType.Internal, "icon/icon_16.png"); // icon for windows
        config.setWindowIcon(Files.FileType.Internal, "icon/icon_32.png"); // icon for windows/linux
        config.setWindowIcon(Files.FileType.Internal, "icon/icon_128.png"); // icon for macOS

        config.setTitle("Heslington_Hustle");
        new Lwjgl3Application(new HeslingtonHustle(), config);
    }
}
