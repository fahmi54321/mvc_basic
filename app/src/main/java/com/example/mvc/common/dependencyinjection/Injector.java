package com.example.mvc.common.dependencyinjection;

import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.questiondetails.QuestionDetailsController;
import com.example.mvc.screens.questionslist.QuestionsListController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector {
    private final PresentationCompositionRoot presentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        this.presentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client) {
        for (Field field : getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field);
            }
        }
    }

    private Field[] getAllFields(Object client) {
        Class<?> clientClass = client.getClass();
        return clientClass.getDeclaredFields();
    }

    private boolean isAnnotatedForInjection(Field field) {
        Annotation[] fieldAnnotations = field.getAnnotations();
        for (Annotation annotation : fieldAnnotations) {
            if (annotation.annotationType().equals(Service.class)) {
                return true;
            }
        }
        return false;
    }

    private void injectField(Object client, Field field) {
        boolean isAccessibleInitially = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(client, getServiceForClass(field.getType()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to inject field: " + field.getName(), e);
        }
        field.setAccessible(isAccessibleInitially);
    }

    private Object getServiceForClass(Class<?> type) {
        if (type.equals(QuestionsListController.class)) {
            return presentationCompositionRoot.getQuestionsListController();
        } else if (type.equals(ViewMvcFactory.class)) {
            return presentationCompositionRoot.getViewMvcFactory();
        } else if (type.equals(QuestionDetailsController.class)) {
            return presentationCompositionRoot.getQuestionDetailsController();
        } else {
            throw new RuntimeException("Unsupported service type: " + type);
        }
    }
}
