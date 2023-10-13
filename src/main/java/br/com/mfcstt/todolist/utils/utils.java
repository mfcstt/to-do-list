package br.com.mfcstt.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class utils {

    // Método que copia propriedades não nulas da origem para o destino
    public static void copyNonNullProperties(Object source, Object target) {
        // Usando BeanUtils para copiar propriedades da origem para o destino
        // getNullPropertyNames obtém os nomes das propriedades nulas na origem
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Método que retorna os nomes das propriedades nulas em um objeto
    public static String[] getNullPropertyNames(Object source) {
        // BeanWrapper permite acessar propriedades de objetos
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Array de PropertyDescriptor para obter informações sobre as propriedades
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Conjunto para armazenar os nomes das propriedades nulas
        Set<String> emptyNames = new HashSet<>();

        // Iterando pelas propriedades
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            // Verifica se o valor da propriedade é nulo
            if (srcValue == null) {
                // Adiciona o nome da propriedade ao conjunto
                emptyNames.add(pd.getName());
            }
        }

        // Converte o conjunto para um array de Strings
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
