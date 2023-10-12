package br.com.mfcstt.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class utils {

   
    //Atribuir propriedades nulas ao BeanUtils e converter para mesclar as informações

    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullProperyNames(source));
    }
    
    public static String[] getNullProperyNames(Object source){

        //interface que permite acessar propriedades de objetos 
        final BeanWrapper src = new BeanWrapperImpl(source);

        //propriedades com valores nulos
      PropertyDescriptor[] pds =  src.getPropertyDescriptors();
      Set<String> emptyNames = new HashSet<>();

      //obter valor da propriedade
      for(PropertyDescriptor pd:pds){
       Object srcValue = src.getPropertyValue(pd.getName());

       //verificar se é nula
       //colocar no emptyname
       if (srcValue == null) {
            emptyNames.add(pd.getName());
       }
      }
      String[] result = new String[emptyNames.size()];
      return emptyNames.toArray(result);
    }
}
