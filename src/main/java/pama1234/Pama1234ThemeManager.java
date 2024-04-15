package pama1234;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.util.messages.MessageBusConnection;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import pama1234.notification.NotificationsKt.Notifications;
import pama1234.settings.ThemeSettings;

public final class Pama1234ThemeManager{
  public enum Pama1234Themes{
    Dark,
    Light;
    //  VIVID,
    //  VIVID_ITALIC;
  }

  @Metadata(mv= {1,7,0},k=3)
  final class RegisterStartup extends MutablePropertyReference0Impl{
    RegisterStartup(Pama1234ThemeManager var1) {
      super(var1,Pama1234ThemeManager.class,"messageBus","getMessageBus()Lcom/intellij/util/messages/MessageBusConnection;",0);
    }

    @Nullable
    public Object get() {
      return Pama1234ThemeManager.access$getMessageBus$p((Pama1234ThemeManager)this.receiver);
    }

    public void set(@Nullable Object value) {
      Pama1234ThemeManager.access$setMessageBus$p((Pama1234ThemeManager)this.receiver,(MessageBusConnection)value);
    }
  }

  private static MessageBusConnection messageBus;
  @NotNull
  private static final Map<String,Pama1234Themes> THEMES;
  @NotNull
  public static final String PLUGIN_ID="pama1234.processing-pama1234-theme";
  @NotNull
  public static final Pama1234ThemeManager INSTANCE;

  @NotNull
  public final Map<String,Pama1234Themes> getTHEMES() {
    return THEMES;
  }

  public final void registerStartup(@NotNull Project project) {
    Intrinsics.checkNotNullParameter(project,"project");
    if(messageBus==null) {
      this.registerUser();
      //      this.attemptToDisplayUpdates(project);
      this.subscribeToEvents();
    }

  }

  private final void registerUser() {
    CharSequence var1=(CharSequence)ThemeSettings.Companion.getInstance().getUserId();
    if(var1.isEmpty()) {
      ThemeSettings var10000=ThemeSettings.Companion.getInstance();
      String var10001=UUID.randomUUID().toString();
      Intrinsics.checkNotNullExpressionValue(var10001,"UUID.randomUUID().toString()");
      var10000.setUserId(var10001);
    }

  }

  //  private final void attemptToDisplayUpdates(final Project project) {
  //    Consumer<String> consumer=(Consumer<String>)(new Consumer<String>() {
  //
  //      public final void accept(@NotNull final String currentVersion) {
  //        //        Intrinsics.checkNotNullParameter(currentVersion,"currentVersion");
  //        boolean debug=false;
  //
  //        if(debug||!Intrinsics.areEqual(ThemeSettings.Companion.getInstance().getVersion(),currentVersion)) {
  //          ThemeSettings.Companion.getInstance().setVersion(currentVersion);
  //          Runnable runnable=(Runnable)(new Runnable() {
  //            public final void run() {
  //              Notifications.INSTANCE.displayUpdateNotification(currentVersion);
  //            }
  //          });
  //          StartupManager.getInstance(project).runWhenProjectIsInitialized(runnable);
  //
  //        }
  //
  //      }
  //    });
  //    //    consumer.accept(getVersion());
  //  }

  public static class Pama1234BackgroundPostStartupActivity implements ProjectActivity{

    @Nullable
    @Override
    public Object execute(@NotNull Project project,@NotNull Continuation<? super Unit> continuation) {
      var currentVersion=getVersion();

      boolean debug=false;

      if(debug||!Intrinsics.areEqual(ThemeSettings.Companion.getInstance().getVersion(),currentVersion)) {
        ThemeSettings.Companion.getInstance().setVersion(currentVersion);
        Runnable runnable=(Runnable)(new Runnable() {
          public final void run() {
            Notifications.INSTANCE.displayUpdateNotification(currentVersion);
          }
        });
        StartupManager.getInstance(project).runWhenProjectIsInitialized(runnable);

      }
      return null;
    }
  }

  private static final String getVersion() {
    @Nullable
    IdeaPluginDescriptor plugin=PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID));
    @NotNull
    Optional<String> output=ExtensionsKt.toOptional(plugin.getVersion());
    return plugin.getVersion();
  }

  private final void subscribeToEvents() {
    @NotNull
    Application application=ApplicationManager.getApplication();
    @NotNull
    MessageBusConnection out=application.getMessageBus().connect();
    messageBus=out;
  }

  private Pama1234ThemeManager() {}

  static {
    INSTANCE=new Pama1234ThemeManager();
    Pair[] rawPairs=new Pair[] {
      TuplesKt.to("f92a0fa7-1a98-47cd-b5cb-78ff67e6f4f4",Pama1234Themes.Dark),
      TuplesKt.to("1a92aa6f-c2f1-4994-ae01-6a78e43eeb25",Pama1234Themes.Light),
      // TuplesKt.to("4b6007f7-b596-4ee2-96f9-968d3d3eb392",OneDarkThemes.VIVID),
      // TuplesKt.to("4f556d32-83cb-4b8b-9932-c4eccc4ce3af",OneDarkThemes.VIVID_ITALIC)
    };
    THEMES=MapsKt.mapOf((Pair<String,Pama1234Themes>[])rawPairs);
  }

  // $FF: synthetic method
  public static MessageBusConnection access$getMessageBus$p(Pama1234ThemeManager $this) {
    MessageBusConnection var10001=messageBus;
    if(var10001==null) {
      Intrinsics.throwUninitializedPropertyAccessException("messageBus");
    }

    return var10001;
  }

  // $FF: synthetic method
  public static final void access$setMessageBus$p(Pama1234ThemeManager $this,MessageBusConnection var1) {
    messageBus=var1;
  }
}
