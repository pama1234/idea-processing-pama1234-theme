package pama1234.tools;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class ToolBoxKt{
  public static <A,C> Object runSafelyWithResult(@NotNull Function0<A> runner,@NotNull Function1<Throwable,C> onError) {
    Intrinsics.checkNotNullParameter(runner,"runner");
    Intrinsics.checkNotNullParameter(onError,"onError");

    Object out;
    try {
      out=runner.invoke();
    }catch(Throwable throwable) {
      out=onError.invoke(throwable);
    }

    return out;
  }

  public static void runSafely(@NotNull Function0<Object> runner,@NotNull Function1<Object,Object> onError) {
    Intrinsics.checkNotNullParameter(runner,"runner");
    Intrinsics.checkNotNullParameter(onError,"onError");

    try {
      runner.invoke();
    }catch(Throwable var3) {
      onError.invoke(var3);
    }

  }
}
