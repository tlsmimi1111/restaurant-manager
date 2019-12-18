package axon.tls.restaurant.config;

public class ApiConfig {
	public static final String PREFIX = "/api";
	
	
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	
	public static final String URI_USERS_ALL = "/users";
    public static final String URI_CURRENT_USER_GET = "/currentUser";
    public static final String URI_USERS_CREATE = "/users/create";
    public static final String URI_USERS_PARSETOKEN = "/users/parseToken";
    public static final String URI_USERS_UPDATE = "/users/update";
    public static final String URI_USERS_DELETE = "/users/delete/{userId}";
    
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
    public static final String URI_FLOOR_GET_BY_RESTAURANT_ID = "/floorsBy/{restaurantId}";
    
    public static final String URI_DESK_CREATE = "/desks/create";
    public static final String URI_DESK_GET_ALL = "/desks";
    public static final String URI_DESK_UPDATE = "/desks/update/{deskId}";
    public static final String URI_DESK_DELETE_ONE = "/desks/delete/{deskId}";
    public static final String URI_DESK_GET_ONE = "/desks/{deskId}";
    public static final String URI_DESK_DISABLE_ONE = "/desks/disable";
    public static final String URI_GET_DESK_BY_RESTAURANT_ID  = "/desksByRestaurant";
    
    public static final String URI_ITEM_CREATE = "/items/create";
    public static final String URI_ITEM_GET_ALL = "/items";
    public static final String URI_ITEM_UPDATE = "/items/update/{itemId}";
    public static final String URI_ITEM_GET_ONE = "/items/{itemId}";
    public static final String URI_ITEM_DISABLE_ONE = "/items/disable";
    public static final String URI_GET_ITEM_BY_RESTAURANT_ID  = "/itemsByRestaurant";
    
    public static final String URI_ROW_ITEM_CREATE = "/rowItems/create";
    public static final String URI_ROW_ITEM_GET_ALL = "/rowItems";
    public static final String URI_ROW_ITEM_UPDATE = "/rowItems/update/{rowItemId}";
    public static final String URI_ROW_ITEM_GET_ONE = "/rowItems/{rowItemId}";
    public static final String URI_ROW_ITEM_DISABLE_ONE = "/rowItems/disable";
    public static final String URI_GET_ROW_ITEM_BY_BILL_ID  = "/rowItemsByBill";
    
    public static final String URI_BILL_CREATE = "/bills/create";
    public static final String URI_BILL_GET_ALL = "/bills";
    public static final String URI_BILL_UPDATE = "/bills/update/{billId}";
    public static final String URI_BILL_GET_ONE = "/bills/{billId}";
    public static final String URI_BILL_DISABLE_ONE = "/bills/disable";
    public static final String URI_GET_BILL_BY_DESK_ID  = "/billsByDesk";
    
}
