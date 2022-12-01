package tdtu.finalterm520h0335_519h0189.Service;

public class APIService {

    private static String base_url = "https://musicplayer0805.000webhostapp.com/";

    public static DataService getService()
    {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}
