package kz.nish.springapp.nishApp.util;

public class OrganizationNotCreatedException extends RuntimeException {
    public OrganizationNotCreatedException(String msg){
        super(msg);
    }
}
