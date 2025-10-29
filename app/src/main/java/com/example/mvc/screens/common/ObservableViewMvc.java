package com.example.mvc.screens.common;

import com.example.mvc.screens.questionslist.QuestionsListItemViewMvc;

public interface ObservableViewMvc<ListenerType> extends ViewMvc{
    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);
}
