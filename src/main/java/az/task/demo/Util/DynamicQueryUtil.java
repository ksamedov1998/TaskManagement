package az.task.demo.Util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;

public class DynamicQueryUtil {
    private static final String UPDATE=" update ";
    private static final String WHERE=" where  id =";
    private static final String SET=" set ";
    private static String query="";

    public static  <T> String createQuery(int id,T object){
        query+=UPDATE +object.getClass().getName()+ SET;
        copyNonNullProperties(object);
        query+=WHERE+id;
        System.out.println(query);
        String tempQ=query;
        query="";
        return tempQ;
    }

    private  static <T> String  copyNonNullProperties(T src) {
        getNotNullPropertyNames(src);
        return query;
    }

    private static void getNotNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        int last=pds.length;

        PropertyDescriptor propertyDescriptor;
        boolean isFirst=true;
        for(int i=0;i<pds.length;i++){
            propertyDescriptor=pds[i];
            Object srcValue = src.getPropertyValue(propertyDescriptor.getDisplayName());
            if (srcValue != null && srcValue.getClass()!=Integer.class
                        && !propertyDescriptor.getName().equals("class"))
                if(isFirst){
                        query+=propertyDescriptor.getName()+"="+"'"+srcValue +"'";
                        isFirst=false;
                }else{
                    query+= " , "+propertyDescriptor.getName()+"="+"'"+srcValue +"'" ;
                }

        }
    }
}
