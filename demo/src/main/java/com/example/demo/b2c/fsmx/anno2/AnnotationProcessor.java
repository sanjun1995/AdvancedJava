package com.example.demo.b2c.fsmx.anno2;

/**
 * @author caozhixin
 * @date 2024/9/20 17:07
 */
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class AnnotationProcessor {

    private String[] getValue(Annotation metaAnnotation, Annotation methodAnnotation) {
        String[] value = (String[]) AnnotationUtils.getValue(metaAnnotation, "value");

        if (value != null && value.length > 0) {
            return value;
        }

        if (metaAnnotation == methodAnnotation) {
            throw new IllegalArgumentException("@" + metaAnnotation.annotationType().getSimpleName() + ".value is empty !");
        }

        Object[] rewriteValue = (Object[]) AnnotationUtils.getValue(methodAnnotation, "value");

        if (rewriteValue == null || rewriteValue.length == 0) {
            throw new IllegalArgumentException("@" + metaAnnotation.annotationType().getSimpleName() + ".value is empty !");
        }

        if (rewriteValue[0] instanceof String) {
            return (String[]) rewriteValue;
        }

        value = new String[rewriteValue.length];

        for (int i = 0; i < rewriteValue.length; ++i) {
            Object item = rewriteValue[i];
            String itemValue = item.toString();
            if (!itemValue.contains(item.getClass().getSimpleName())) {
                itemValue = item.getClass().getSimpleName() + "." + itemValue;
            }
            value[i] = itemValue;
        }

        return value;
    }

    private List<Annotation> getAnnotationChain(Method method, Class<? extends Annotation> annotationType) {
        Annotation[] annotations = method.getAnnotations();
        List<Annotation> annotationChain = new LinkedList<>();
        Set<Annotation> visited = new HashSet<>();
        for (Annotation ann : annotations) {
            recursiveFindAnnotation(annotationType, ann, annotationChain, visited);
            if (!annotationChain.isEmpty()) {
                return annotationChain;
            }
        }
        return annotationChain;
    }

    private boolean recursiveFindAnnotation(Class<? extends Annotation> annotationType, Annotation ann, List<Annotation> annotationChain, Set<Annotation> visited) {
        if (ann.annotationType().equals(annotationType)) {
            annotationChain.add(ann);
            return true;
        }
        for (Annotation metaAnn : ann.annotationType().getAnnotations()) {
            if (!ann.equals(metaAnn) && !visited.contains(metaAnn)
                    && !(metaAnn.annotationType().getPackage().getName().startsWith("java.lang"))) {
                visited.add(metaAnn);
                if (recursiveFindAnnotation(annotationType, metaAnn, annotationChain, visited)) {
                    annotationChain.add(ann);
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getActionCodes(List<Annotation> annotationChain) {
        return getValue(annotationChain.get(0), annotationChain.size() > 1 ? annotationChain.get(1) : annotationChain.get(0));
    }

    public void initGenericFsmActionBean(Class<?> clazz, Object bean) {
        for (Method method : clazz.getDeclaredMethods()) {
            List<Annotation> doActionChain = getAnnotationChain(method, DoAction.class);
            if (!doActionChain.isEmpty()) {
                String[] beanNames = getActionCodes(doActionChain);
                System.out.println("Bean Names: " + Arrays.toString(beanNames)); // Print the bean names
                // 这里可以根据 beanNames 进行其他操作，比如初始化 bean
            }
        }
    }
}

