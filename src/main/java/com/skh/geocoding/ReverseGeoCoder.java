package com.skh.geocoding;

import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class ReverseGeoCoder {
    private Boolean isWrongData;
    private Boolean writeToFile = Boolean.TRUE;

    public ReverseGeoCoder(Boolean isWrongData) {
      this.isWrongData = isWrongData;
    }

  // latutude - 12.915860
  // lingitude - 77.691750
  // https://developers.google.com/maps/documentation/geocoding/requests-reverse-geocoding

  public String reverseGeoCode() throws Exception  {

    StringBuilder builder = new StringBuilder();
    builder.append("http://api.positionstack.com/v1/reverse?");
    builder.append("access_key=").append("59e567b181dd9a6e0fd1ee4ae5317afb")  ;
    builder.append("&query=12.915860,77.691750");
    ;
    if(isWrongData) {
      throw new RuntimeException("its wrong data, throwing exception");
    }

    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(builder.toString()))
        .GET()
        .build();

    HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(20))
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    return response.body();

  }

  public static void main(String[] args) throws Exception {
    ReverseGeoCoder rgc = new ReverseGeoCoder(args.length > 0 ? Boolean.TRUE : Boolean.FALSE);
    System.out.println(rgc.reverseGeoCode());
    if(rgc.writeToFile) {
      FileWriter writer = new FileWriter("/mydata/op");
      writer.write(rgc.reverseGeoCode() + "\n");
    }
  }
}
