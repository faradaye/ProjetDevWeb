package utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {
    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> mapIdToUri = new HashMap<Integer, String>();
    private static final Map<String, Integer> mapUriToId = new HashMap<String, Integer>();

    public static int enregistreUriEtRetourneIdRedirect(String requestUri) {
        Integer id = mapUriToId.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;
            mapUriToId.put(requestUri, id);
            mapIdToUri.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String recupereUriRedirectDepuisIdRedirect(int redirectId) {
        return mapIdToUri.get(redirectId);
    }
}
