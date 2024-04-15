package pama1234.settings;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@State(name="Pama1234ThemeConfig",storages= {@Storage("pama1234_theme_config.xml")})
public final class ThemeSettings implements PersistentStateComponent<ThemeSettings>,Cloneable{
  @NotNull
  private String version="0.0.0";
  @NotNull
  private String userId="";
  @NotNull
  public static final Companion Companion=new Companion((DefaultConstructorMarker)null);

  @NotNull
  public final String getVersion() {
    return this.version;
  }

  public final void setVersion(@NotNull String var1) {
    Intrinsics.checkNotNullParameter(var1,"<set-?>");
    this.version=var1;
  }

  @NotNull
  public final String getUserId() {
    return this.userId;
  }

  public final void setUserId(@NotNull String var1) {
    Intrinsics.checkNotNullParameter(var1,"<set-?>");
    this.userId=var1;
  }

  @Nullable
  public ThemeSettings getState() {
    return (ThemeSettings)XmlSerializerUtil.createCopy(this);
  }

  public void loadState(@NotNull ThemeSettings state) {
    Intrinsics.checkNotNullParameter(state,"state");
    XmlSerializerUtil.copyBean(state,this);
  }

  @NotNull
  public Map<String,String> asJson() {
    return MapsKt.mapOf(TuplesKt.to("version",this.version));
  }

  @NotNull
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public static final class Companion{
    @NotNull
    public final ThemeSettings getInstance() {
      Object out=ApplicationManager.getApplication().getService(ThemeSettings.class);
      Intrinsics.checkNotNullExpressionValue(out,"ServiceManager.getServic…hemeSettings::class.java)");
      return (ThemeSettings)out;
    }

    private Companion() {}

    // $FF: synthetic method
    public Companion(DefaultConstructorMarker $constructor_marker) {
      this();
    }
  }
}
