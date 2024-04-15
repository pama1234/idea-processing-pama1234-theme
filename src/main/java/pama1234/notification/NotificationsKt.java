package pama1234.notification;

import javax.swing.*;

import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.notification.*;
import com.intellij.notification.NotificationListener.UrlOpeningListener;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;

import pama1234.Pama1234ThemeManager;

public final class NotificationsKt{
  @Language("HTML")
  @NotNull
  private static final String UPDATE_MESSAGE="""
    What's New?<br>
    <ul>
      <li>2024.1 Build Support</li>
      </ul>
      <br>Please see the <a href='https://github.com/pama1234/idea-processing-pama1234-theme/blob/master/CHANGELOG.md'>Changelog</a> for more details.
      <br>
      Thank you for choosing Processing Pama1234 Theme!<br>
    """;
  @NotNull
  public static String getUPDATE_MESSAGE() {
    return UPDATE_MESSAGE;
  }

  // Notifications.java
  public static final class Notifications{
    private static final Icon NOTIFICATION_ICON;
    private static final NotificationGroup notificationGroup;
    @NotNull
    public static final Notifications INSTANCE;

    public final void displayUpdateNotification(@NotNull String versionNumber) {
      String pluginName;
      label11:
      {
        //        Intrinsics.checkNotNullParameter(versionNumber,"versionNumber");
        IdeaPluginDescriptor pluginDescriptor=PluginManagerCore.getPlugin(PluginId.getId(Pama1234ThemeManager.PLUGIN_ID));
        if(pluginDescriptor!=null) {
          pluginName=pluginDescriptor.getName();
          if(pluginName!=null) {
            break label11;
          }
        }

        pluginName="Processing Pama1234 Theme";
      }

      UrlOpeningListener listener=new NotificationListener.UrlOpeningListener(false);
      @NotNull
      Notification notification=notificationGroup.createNotification(NotificationsKt.getUPDATE_MESSAGE(),NotificationType.INFORMATION);
      notification.setListener(listener);
      notification
        .setTitle(pluginName+" updated to v"+versionNumber)
        .setIcon(NOTIFICATION_ICON)
        .notify((Project)null);
    }

    private Notifications() {}

    static {
      INSTANCE=new Notifications();
      @NotNull
      Icon out=IconLoader.getIcon("/icons/logo.svg",Notifications.class);
      NOTIFICATION_ICON=out;
      notificationGroup=NotificationGroupManager.getInstance().getNotificationGroup("Processing Pama1234 Theme");
    }
  }

}
