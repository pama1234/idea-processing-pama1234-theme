package pama1234.integrations;

import java.awt.Component;
import java.lang.management.ManagementFactory;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.ide.IdeBundle;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.util.Consumer;

import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.protocol.Message;
import io.sentry.protocol.User;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import pama1234.settings.ThemeSettings;
import pama1234.tools.ToolBoxKt;

public final class ErrorReporter extends ErrorReportSubmitter{
  @NotNull
  public String getReportActionText() {
    return "Report Anonymously";
  }

  public boolean submit(@NotNull final IdeaLoggingEvent[] events,@Nullable final String additionalInfo,@NotNull Component parentComponent,@NotNull Consumer consumer) {
    Intrinsics.checkNotNullParameter(events,"events");
    Intrinsics.checkNotNullParameter(parentComponent,"parentComponent");
    Intrinsics.checkNotNullParameter(consumer,"consumer");
    return (Boolean)ToolBoxKt.runSafelyWithResult((Function0<Boolean>)new Function0<Boolean>() {
      // $FF: synthetic method
      // $FF: bridge method
      public Boolean invoke() {
        return this.invokeInner();
      }

      public final boolean invokeInner() {
        ApplicationManager.getApplication().executeOnPooledThread((Runnable)new Runnable() {
          public final void run() {
            ToolBoxKt.runSafely((Function0<Object>)new Function0<Object>() {
              // $FF: synthetic method
              // $FF: bridge method
              public Object invoke() {
                this.invokeInner();
                return Unit.INSTANCE;
              }

              public final void invokeInner() {
                User var1=new User();
                // int var3=false;
                var1.setId(ThemeSettings.Companion.getInstance().getUserId());
                Sentry.setUser(var1);
                Sentry.init((Sentry.OptionsConfiguration<SentryOptions>)null);
                // IdeaLoggingEvent[] $this$forEach$iv=events;
                // int $i$f$forEach=false;
                int var4=events.length;

                for(IdeaLoggingEvent element$iv:events) {
                  // int var8=false;
                  ErrorReporter var10000=ErrorReporter.this;
                  SentryEvent var9=new SentryEvent();
                  // int var12=false;
                  var9.setLevel(SentryLevel.ERROR);
                  String var10002=additionalInfo;
                  if(var10002==null) {
                    var10002="None";
                  }

                  var9.setExtra("Additional Info",var10002);
                  Unit var13=Unit.INSTANCE;
                  var10000.addSystemInfo(var9);
                  // var12=false;
                  Message var14=new Message();
                  // int var17=false;
                  var14.setMessage(element$iv.getThrowableText());
                  Unit var18=Unit.INSTANCE;
                  var9.setMessage(var14);
                  Sentry.captureEvent(var9);
                }

              }
            },(Function1<Object,Object>)null);
          }
        });
        return true;
      }
    },(Function1<Throwable,Object>)null);
  }

  private SentryEvent addSystemInfo(SentryEvent event) {
    Properties properties=System.getProperties();
    // int var5=false;
    Intrinsics.checkNotNullExpressionValue(properties,"properties");
    event.setExtra("JRE",this.getJRE(properties));
    event.setExtra("VM",this.getVM(properties));
    event.setExtra("System Info",SystemInfo.getOsNameAndVersion());
    event.setExtra("GC",this.getGC());
    event.setExtra("Memory",Runtime.getRuntime().maxMemory()/(long)1048576);
    event.setExtra("Cores",Runtime.getRuntime().availableProcessors());
    event.setExtra("Pama1234 Theme Config",ThemeSettings.Companion.getInstance().asJson());
    return event;
  }

  private String getJRE(Properties properties) {
    String javaVersion=properties.getProperty("java.runtime.version",properties.getProperty("java.version","unknown"));
    String arch=properties.getProperty("os.arch","");
    String var10000=IdeBundle.message("about.box.jre",new Object[] {javaVersion,arch});
    Intrinsics.checkNotNullExpressionValue(var10000,"IdeBundle.message(\"about….jre\", javaVersion, arch)");
    return var10000;
  }

  private final String getVM(Properties properties) {
    String vmVersion=properties.getProperty("java.vm.name","unknown");
    String vmVendor=properties.getProperty("java.vendor","unknown");
    String var10000=IdeBundle.message("about.box.vm",new Object[] {vmVersion,vmVendor});
    Intrinsics.checkNotNullExpressionValue(var10000,"IdeBundle.message(\"about…vm\", vmVersion, vmVendor)");
    return var10000;
  }

  private final String getGC() {
    return (String)ManagementFactory.getGarbageCollectorMXBeans().stream().map((Function)null).collect(Collectors.joining((CharSequence)","));
  }
}
