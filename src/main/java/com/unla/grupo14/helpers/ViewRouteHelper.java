package com.unla.grupo14.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//HOME
	public static final String INDEXADMIN = "home/indexAdmin";
    public static final String INDEXUSER = "home/indexUser";
	
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
    public static final String VENTA_LISTA_USUARIO = "venta/listUser";
    public static final String VENTA_LISTA_ADMIN = "venta/listAdmin";
    
    //PEDIDO
    public final static String PEDIDO_LIST = "pedidos/lista";
    public final static String PEDIDO_FORM = "pedidos/registrar";
  
	/**** Redirects ****/
	public final static String ROUTE = "/index";
	public final static String LOGIN = "/login";
	public final static String LOTE = "/lotes";
	public final static String PEDIDO = "/pedidos";
	public final static String PRODUCTO = "/productos";
	public final static String VENTA = "/ventas";
	public final static String VENTA_USUARIO = "/ventas/usuario";
	public final static String VENTA_ADMIN = "/ventas/admin";
	
}
