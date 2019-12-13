package axon.tls.restaurant.config;

public class ApiConfig {
	public static final String PREFIX = "/api";
	
	
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	
	public static final String URI_USERS_ALL = "/users";
    public static final String URI_CURRENT_USER_GET = "/currentUser";
    public static final String URI_USERS_CREATE = "/users/create";
    public static final String URI_USERS_PARSETOKEN = "/users/parseToken";
    public static final String URI_USERS_UPDATE = "/users/update/{userName}";
    public static final String URI_USERS_DELETE = "/users/delete/{userId}/{postId}";
    
    public static final String URI_RESTAURANT_CREATE = "/restaurants/create";
    public static final String URI_RESTAURANT_GET_ALL = "auth/restaurants";
    public static final String URI_RESTAURANT_UPDATE = "restaurants/update/{restaurantId}";
    public static final String URI_RESTAURANT_DELETE_ONE = "/restaurants/delete/{restaurantId}";
    public static final String URI_RESTAURANT_GET_ONE = "/restaurants/{restaurantId}";
    public static final String URI_RESTAURANT_DISABLE_ONE = "/restaurants/disable";
    
    public static final String URI_FLOOR_CREATE = "/floors/create";
    public static final String URI_FLOOR_GET_ALL = "/floors";
    public static final String URI_FLOOR_UPDATE = "/floors/update/{floorId}";
    public static final String URI_FLOOR_DELETE_ONE = "/floors/delete/{floorId}";
    public static final String URI_FLOOR_GET_ONE = "/floors/{floorId}";
    public static final String URI_FLOOR_DISABLE_ONE = "/floors/disable";
    
    public static final String URI_DESK_CREATE = "/desks/create";
    public static final String URI_DESK_GET_ALL = "/desks";
    public static final String URI_DESK_UPDATE = "/desks/update/{deskId}";
    public static final String URI_DESK_DELETE_ONE = "/desks/delete/{deskId}";
    public static final String URI_DESK_GET_ONE = "/desks/{deskId}";
    public static final String URI_DESK_DISABLE_ONE = "/desks/disable";
    
}
