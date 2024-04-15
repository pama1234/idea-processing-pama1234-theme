package pama1234;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;

import kotlin.jvm.internal.Intrinsics;

public final class Pama1234Theme implements StartupActivity,DumbAware{
  public void runActivity(@NotNull Project project) {
    Intrinsics.checkNotNullParameter(project,"project");
    Pama1234ThemeManager.INSTANCE.registerStartup(project);
  }
}
