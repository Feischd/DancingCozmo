package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Artist;

import java.io.IOException;
import java.util.List;

public class ArtistsRequest extends AbstractRequest {

  protected ArtistsRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<List<Artist>> getAsync() {
    SettableFuture<List<Artist>> artistsFuture = SettableFuture.create();

    try {
      artistsFuture.set(JsonUtil.createArtists(getJson()));
    } catch (Exception e) {
      artistsFuture.setException(e);
    }

    return artistsFuture;
  }

  public List<Artist> get() throws IOException, WebApiException {
    return JsonUtil.createArtists(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(List<String> ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids);
      path("/v1/artists");
      return parameter("ids", idsParameter);
    }

    public ArtistsRequest build() {
      return new ArtistsRequest(this);
    }

  }
}
