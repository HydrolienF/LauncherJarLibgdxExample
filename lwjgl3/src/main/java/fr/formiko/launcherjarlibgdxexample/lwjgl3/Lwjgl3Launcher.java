package fr.formiko.launcherjarlibgdxexample.lwjgl3;

import fr.formiko.launcherjarlibgdxexample.Game;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        // return version
        if (args.length > 0 && args[0].replace("-", "").equalsIgnoreCase("version")) {
            try {
                InputStream is = Lwjgl3Launcher.class.getClassLoader().getResourceAsStream("version.md");
                String version = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                        .collect(Collectors.joining("\n")).strip();
                System.out.println(version);
                System.exit(0);
            } catch (Exception e) {
                System.out.println("Fail to get version in DesktopLauncher.");
            }
        }
        
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Game(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("LauncherJarLibgdxExample");
        configuration.useVsync(true);
        //// Limits FPS to the refresh rate of the currently active monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
        configuration.setWindowedMode(640, 480);
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
