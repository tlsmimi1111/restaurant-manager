package axon.tls.restaurant.config;

public class Constants {
	public static final String FRONTEND_HOST = "http://localhost:4200";

	public static final Integer POST_STATUS_REQUESTING = 0;
	public static final Integer POST_STATUS_PROCESSING = 1;
	public static final Integer POST_STATUS_AVAILABLE = 2;
	public static final Integer POST_STATUS_COMPLETED = 3;

	public static final Integer ORDER_STATUS_REQUESTING = 0;
	public static final Integer ORDER_STATUS_COMPLETED = 1;
	public static final String RESTAURANT_FILTER = "restaurantfilter";
	public static final String USER_FILTER = "userFilter";
	public static final String FLOOR_FILTER = "floorFilter";
	public static final String DESK_FILTER = "deskFilter";

	public static final Integer NOT_DELETED = 0;
	public static final String SECRET = "burningTeamSecret";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final Integer ACTIVE = 1;

	public static String GOOGLE_CLIENT_ID = "378077348336-n3r898lp9sjpb5ph9sbbrfqq7idoajvb.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "yhrDHD2TOJLGGwFetBiYyIzB";
	public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/login";
	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token/";
	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
