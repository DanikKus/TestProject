package kz.nish.springapp.nishApp.util;

import kz.nish.springapp.nishApp.models.Student;

public class StudentNotCreatedException extends RuntimeException{
    public StudentNotCreatedException(String msg){
        super(msg);
    }
}
