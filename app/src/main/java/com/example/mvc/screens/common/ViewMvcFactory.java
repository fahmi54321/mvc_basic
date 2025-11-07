package com.example.mvc.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.mvc.screens.questiondetails.QuestionDetailsViewMvc;
import com.example.mvc.screens.questiondetails.QuestionDetailsViewMvcImpl;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListItemMvcImpl;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListItemViewMvc;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.listview.QuestionsListViewMvcImpl;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater layoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent){
        return new QuestionsListViewMvcImpl(layoutInflater,parent, this);
    }

    public QuestionsListViewMvc getQuestionsRecyclerViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsRecyclerMvcImpl(layoutInflater,parent, this);
    }

    public QuestionsListItemViewMvc getQuestionsListItemViewMvc(@Nullable ViewGroup parent){
        return new QuestionsListItemMvcImpl(layoutInflater,parent);
    }

    public QuestionDetailsViewMvc getQuestionDetailsViewMvc(@Nullable ViewGroup parent) {
        return new QuestionDetailsViewMvcImpl(layoutInflater, parent);
    }
}
