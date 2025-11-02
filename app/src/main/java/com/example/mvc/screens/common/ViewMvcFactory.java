package com.example.mvc.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.mvc.screens.questionslist.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.listview.QuestionsListViewMvcImpl;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater layoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent){
        return new QuestionsListViewMvcImpl(layoutInflater,parent);
    }

    public QuestionsListViewMvc getQuestionsRecyclerViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsRecyclerMvcImpl(layoutInflater,parent);
    }
}
