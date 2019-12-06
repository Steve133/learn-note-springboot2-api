package cn.center.common.constant;

import java.io.Serializable;

public final class Const  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1278400727437619557L;
	private Const() {}
	
	/** 后端登陆用户名 */
	public static String ADMIN_USER = "ADMIN_USER";//如若更改，前段取值也要一同修改
    /** 后端登陆redis键值 */
    public static final String ADMIN_LOGIN_KEY = "ADMIN_LOGIN_KEY:";
    /** 后端登陆cookie键值 */
    public static final String ADMIN_COOKIE_LOGIN_KEY = "ADMIN_COOKIE_LOGIN_KEY";
    /** 后端登陆图片验证码 redis键值 */
    public static final String ADMIN_VERIFYCODE = "ADMIN_VERIFYCODE:";
    /** 门户登陆redis键值 */
    public static final String PORTAL_LOGIN_KEY = "PORTAL_LOGIN_KEY:";
    /** 门户登陆cookie键值 */
    public static final String PORTAL_COOKIE_LOGIN_KEY = "PORTAL_COOKIE_LOGIN_KEY";
    /** 门户登陆验证码 redis键值 */
    public static final String PORTAL_VERIFYCODE = "PORTAL_VERIFYCODE:";
    /** 门户登陆短信验证码 redis键值 */
    public static final String PORTAL_MSG_VERIFYCODE = "PORTAL_MSG_VERIFYCODE:";
    /** 购物车 cookie键值 */
	public static final String CART_COOKIE_KEY = "_CART";
	/** 购物车 cookie键值 */
	public static final String CART_COOKIE_SESSION_KEY = "_CART_TIMESTAMP";
}
