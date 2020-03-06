package az.task.demo.Domains.Enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserType {
    ADMIN(0),USER(1);
    private int value;

    UserType(int value){
        this.value=value;
    }

    public static int getType(UserType userType){
     List<UserType> type= Arrays.stream(UserType.values()).filter(a->a==userType).collect(Collectors.toList());
        return type.get(0).value;
    }

    public static boolean checkType(int userTypeValue){
        return Arrays.stream(UserType.values()).anyMatch(a->a.getValue()==userTypeValue);
    }

    public int getValue() {
        return value;
    }
}
