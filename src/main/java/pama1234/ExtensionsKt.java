package pama1234;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class ExtensionsKt{

  @NotNull
  public static <T> Optional<T> toOptional(@Nullable T in) {
    Optional<T> out=Optional.ofNullable(in);
    Intrinsics.checkNotNullExpressionValue(out,"Optional.ofNullable(this)");
    return out;
  }

  public static void doOrElse(@NotNull Optional<Object> $this$doOrElse,@NotNull final Function1 present,@NotNull final Function0 notThere) {
    Intrinsics.checkNotNullParameter($this$doOrElse,"$this$doOrElse");
    Intrinsics.checkNotNullParameter(present,"present");
    Intrinsics.checkNotNullParameter(notThere,"notThere");
    Consumer<Pair<Object,Boolean>> action=new Consumer<Pair<Object,Boolean>>() {
      // $FF: synthetic method
      // $FF: bridge method
      public void accept(Pair<Object,Boolean> it) {
        Intrinsics.checkNotNullParameter(it,"it");
        if((Boolean)it.getSecond()) {
          present.invoke(it.getFirst());
        }else {
          notThere.invoke();
        }
      }
    };
    Optional<Pair<Object,Boolean>> optional=(Optional<Pair<Object,Boolean>>)$this$doOrElse.map((Function)null).map((Function)null).orElseGet((Supplier)null);
    optional.ifPresent(action);
  }

  public static byte @NotNull [] readAllTheBytes(@NotNull InputStream $this$readAllTheBytes) throws IOException {
    Intrinsics.checkNotNullParameter($this$readAllTheBytes,"$this$readAllTheBytes");
    byte[] var10000=IOUtils.toByteArray($this$readAllTheBytes);
    Intrinsics.checkNotNullExpressionValue(var10000,"IOUtils.toByteArray(this)");
    return var10000;
  }
}
