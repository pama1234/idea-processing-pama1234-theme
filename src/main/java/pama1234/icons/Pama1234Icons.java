package pama1234.icons;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.util.IconLoader;

public final class Pama1234Icons{
  @NotNull
  public static final Icon LOGO;
  @NotNull
  public static final Pama1234Icons INSTANCE;

  private Pama1234Icons() {}

  static {
    INSTANCE=new Pama1234Icons();
    @NotNull
    Icon out=IconLoader.getIcon("/icons/icon.png",INSTANCE.getClass());
    LOGO=out;
  }
}
