package edu.uiowa.torchani;

import com.sun.jna.Platform;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TorchANIUtils {
  private static boolean init = false;
  private static String JNA_LIBRARY_PATH = "";
  private static String TORCHANI_LIB_DIR = "";

  public TorchANIUtils() {}

  public static synchronized void init() {
    if (!init) {
      init = true;

      try {
        JarFile jarFile = jarForClass(edu.uiowa.torchani.TorchANILibrary.class, null);
        String os;
        if (Platform.isMac()) {
          os = "darwin";
        } else if (Platform.isLinux()) {
          os = "linux_x64";
        } else {
          os = "win_x64";
        }

        String directory = "ani/lib/" + os;
        Path path = Files.createTempDirectory("torchani");
        File toDir = path.toFile();
        String var10000 = toDir.getAbsolutePath();
        TORCHANI_LIB_DIR = var10000 + "/" + os;
        copyResourcesToDirectory(jarFile, directory, TORCHANI_LIB_DIR);
        JNA_LIBRARY_PATH = System.getProperty("jna.library.path", "");
        if (!JNA_LIBRARY_PATH.equalsIgnoreCase("")) {
          JNA_LIBRARY_PATH = TORCHANI_LIB_DIR + File.pathSeparator + JNA_LIBRARY_PATH;
        } else {
          JNA_LIBRARY_PATH = TORCHANI_LIB_DIR;
        }

        System.setProperty("jna.library.path", JNA_LIBRARY_PATH);
      } catch (Exception var5) {
        System.err.println(" Exception configuring TorchANI: " + var5.toString());
      }
    }
  }

  public static String getLibDirectory() {
    return TORCHANI_LIB_DIR;
  }

  private static JarFile jarForClass(Class<?> clazz, JarFile defaultJar) {
    String var10000 = clazz.getName();
    String path = "/" + var10000.replace('.', '/') + ".class";
    URL jarUrl = clazz.getResource(path);
    if (jarUrl == null) {
      return defaultJar;
    } else {
      String url = jarUrl.toString();
      int bang = url.indexOf("!");
      String JAR_URI_PREFIX = "jar:file:";
      if (url.startsWith(JAR_URI_PREFIX) && bang != -1) {
        try {
          return new JarFile(url.substring(JAR_URI_PREFIX.length(), bang));
        } catch (IOException var8) {
          throw new IllegalStateException("Error loading jar file.", var8);
        }
      } else {
        return defaultJar;
      }
    }
  }

  private static void copyResourcesToDirectory(JarFile fromJar, String jarDir, String destDir)
      throws IOException {
    Enumeration<JarEntry> entries = fromJar.entries();

    while (true) {
      JarEntry entry;
      do {
        do {
          if (!entries.hasMoreElements()) {
            return;
          }
          entry = (JarEntry) entries.nextElement();
        } while (!entry.getName().startsWith(jarDir + "/"));
      } while (entry.isDirectory());

      File dest = new File(destDir + "/" + entry.getName().substring(jarDir.length() + 1));
      File parent = dest.getParentFile();
      if (parent != null) {
        parent.mkdirs();
      }

      FileOutputStream out = new FileOutputStream(dest);
      InputStream in = fromJar.getInputStream(entry);

      try {
        byte[] buffer = new byte[8192];

        int s;
        while ((s = in.read(buffer)) > 0) {
          out.write(buffer, 0, s);
        }
      } catch (IOException var21) {
        throw new IOException("Could not copy asset from jar file", var21);
      } finally {
        try {
          in.close();
        } catch (IOException var20) {
        }

        try {
          out.close();
        } catch (IOException var19) {
        }
      }
    }
  }
}
