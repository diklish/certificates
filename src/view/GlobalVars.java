package view;

public class GlobalVars {
	public static int value = 0;
	//public static orm.Users userC;
	public static orm.Users userC=null;
	
	public static void setUser(orm.Users user) {
		userC = user;		
	}
	public static orm.Users getUser(){
		return userC;
	}
}
