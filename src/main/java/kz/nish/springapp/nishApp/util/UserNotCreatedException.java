package kz.nish.springapp.nishApp.util;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(String msg){
        super(msg);
    }
}
