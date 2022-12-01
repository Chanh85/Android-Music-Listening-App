package tdtu.finalterm520h0335_519h0189.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tdtu.finalterm520h0335_519h0189.Model.Album;
import tdtu.finalterm520h0335_519h0189.Model.Baihatyeuthich;
import tdtu.finalterm520h0335_519h0189.Model.ChuDe;
import tdtu.finalterm520h0335_519h0189.Model.Playlist;
import tdtu.finalterm520h0335_519h0189.Model.Quangcao;
import tdtu.finalterm520h0335_519h0189.Model.TheLoai;
import tdtu.finalterm520h0335_519h0189.Model.TheLoaiTrongNgay;

public interface DataService {
    @GET("Server/songbanner.php")
    Call<List<Quangcao>> getDataBanner();

    @GET("Server/playlist_currentday.php")
    Call<List<Playlist>> getDataPlaylist();

    @GET("Server/chude_theloai.php")
    Call<TheLoaiTrongNgay> getCategoryMusic();

    @GET("Server/album.php")
    Call<List<Album>> getAlbum();

    @GET("Server/baihatyeuthich.php")
    Call<List<Baihatyeuthich>> getFavSongs();

    @FormUrlEncoded
    @POST("Server/dsbaihat.php")
    Call<List<Baihatyeuthich>> GetDsBaiHatTheoBanner(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("Server/dsbaihat.php")
    Call<List<Baihatyeuthich>> GetDsBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);

    @GET("Server/dsplaylist.php")
    Call<List<Playlist>> getAllPlaylists();

    @FormUrlEncoded
    @POST("Server/dsbaihat.php")
    Call<List<Baihatyeuthich>> GetDsBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("Server/dschude.php")
    Call<List<ChuDe>> getAllChuDe();

    @FormUrlEncoded
    @POST("Server/theloaitheochude.php")
    Call<List<TheLoai>> GetDstheloaitheochude(@Field("idchude") String idchude);

    @GET("Server/DSalbum.php")
    Call<List<Album>> getAllAlbum();

    @FormUrlEncoded
    @POST("Server/dsbaihat.php")
    Call<List<Baihatyeuthich>> GetDsbaihattheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("Server/updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("Server/searchbaihat.php")
    Call<List<Baihatyeuthich>> dataDSBaihat(@Field("tukhoa") String tukhoa);
}
