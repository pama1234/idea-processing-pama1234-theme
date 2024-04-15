package pama1234.integrations;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.diagnostic.Logger;

import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import pama1234.ExtensionsKt;

public final class RestClient{
  private static final CloseableHttpClient httpClient;
  private static final Logger log;
  @NotNull
  public static final RestClient INSTANCE;

  @NotNull
  public final Optional performGet(@NotNull String url) {
    Intrinsics.checkNotNullParameter(url,"url");
    log.info("Attempting to fetch remote asset: "+url);
    HttpGet request=this.createGetRequest(url);

    Optional output;
    Optional tempOptional;
    try {
      CloseableHttpResponse response=httpClient.execute((HttpUriRequest)request);
      Intrinsics.checkNotNullExpressionValue(response,"response");
      StatusLine var17=response.getStatusLine();
      Intrinsics.checkNotNullExpressionValue(var17,"response.statusLine");
      int statusCode=var17.getStatusCode();
      log.info("Asset has responded for remote asset: "+url+" with status code "+statusCode);
      if(statusCode==200) {
        HttpEntity var18=response.getEntity();
        Intrinsics.checkNotNullExpressionValue(var18,"response.entity");
        Closeable var5=(Closeable)var18.getContent();
        Throwable var6=null;

        String var19;
        try {
          InputStream responseBody=(InputStream)var5;
          // int var8=false;
          Intrinsics.checkNotNullExpressionValue(responseBody,"responseBody");
          byte[] var9=ExtensionsKt.readAllTheBytes(responseBody);
          var19=new String(var9,Charsets.UTF_8);
        }catch(Throwable var13) {
          var6=var13;
          throw var13;
        }finally {
          CloseableKt.closeFinally(var5,var6);
        }

        tempOptional=ExtensionsKt.toOptional(var19);
      }else {
        tempOptional=Optional.empty();
        Intrinsics.checkNotNullExpressionValue(tempOptional,"Optional.empty()");
      }

      output=tempOptional;
    }catch(Exception var15) {
      log.warn("Unable to get remote asset: "+url+" for raisins",(Throwable)var15);
      tempOptional=Optional.empty();
      Intrinsics.checkNotNullExpressionValue(tempOptional,"Optional.empty<String>()");
      output=tempOptional;
    }

    return output;
  }

  private final HttpGet createGetRequest(String remoteUrl) {
    HttpGet remoteAssetRequest=new HttpGet(remoteUrl);
    remoteAssetRequest.addHeader("User-Agent","One-Dark");
    remoteAssetRequest.setConfig(RequestConfig.custom().setConnectTimeout((int)TimeUnit.MILLISECONDS.convert(5L,TimeUnit.SECONDS)).build());
    return remoteAssetRequest;
  }

  private RestClient() {}

  static {
    RestClient var0=new RestClient();
    INSTANCE=var0;
    httpClient=HttpClients.createMinimal();
    Logger var10000=Logger.getInstance(var0.getClass());
    Intrinsics.checkNotNullExpressionValue(var10000,"Logger.getInstance(this::class.java)");
    log=var10000;
  }
}
