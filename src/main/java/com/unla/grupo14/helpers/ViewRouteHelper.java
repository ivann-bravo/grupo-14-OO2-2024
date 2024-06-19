package com.unla.grupo14.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//HOME
	public final static String INDEX = "home/index";
	public static final String INDEXADMIN = "home/indexAdmin";
    public static final String INDEXUSER = "home/indexUser";
	public final static String HELLO = "home/hello";
	
	//USER
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";
	
	public final static String USER_REGISTERFORM = "user/registerform";
	
    // PRODUCTO
    public final static String PRODUCTO_FORM = "producto/form";
    public final static String PRODUCTO_LIST = "producto/list";
    public final static String PRODUCTO_MODIFICAR = "producto/modificar";
    
    // LOTE
    public final static String LOTE_LIST = "lote/list";
    public final static String LOTE_FORM = "lote/form";
  
    // VENTA
    public final static String VENTA_FORM = "venta/form";
    
    /**** Redirects ****/
    public final static String ROUTE = "/index";
	
}
