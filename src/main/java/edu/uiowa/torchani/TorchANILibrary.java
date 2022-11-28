package edu.uiowa.torchani;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;

/**
 * JNA Wrapper for library <b>TorchANI</b><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a
 * href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource
 * projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a
 * href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class TorchANILibrary implements Library {
  public static final String JNA_LIBRARY_NAME = "TorchANI";
  public static final NativeLibrary JNA_NATIVE_LIB =
      NativeLibrary.getInstance(TorchANILibrary.JNA_LIBRARY_NAME);

  static {
    Native.register(TorchANILibrary.class, TorchANILibrary.JNA_NATIVE_LIB);
  }
  /**
   * Original signature : <code>
   * double ANIEnergyAndGradient(const char*, long, const int[], const double[], double[])</code>
   */
  public static native double ANIEnergyAndGradient(
      String pathToTorch, NativeLong n, int species[], double coordinates[], double gradient[]);
}
