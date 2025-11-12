package com.example.mvc.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.example.mvc.screens.common.navdrawer.NavDrawerViewMvc;
import com.example.mvc.screens.common.navdrawer.NavDrawerViewMvcImpl;
import com.example.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.example.mvc.screens.questiondetails.QuestionDetailsViewMvc;
import com.example.mvc.screens.questiondetails.QuestionDetailsViewMvcImpl;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListItemMvcImpl;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListItemViewMvc;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.listview.QuestionsListViewMvcImpl;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater layoutInflater;
    private final NavDrawerHelper navDrawerHelper;
    public ViewMvcFactory(LayoutInflater layoutInflater, NavDrawerHelper navDrawerHelper) {
        this.layoutInflater = layoutInflater;
        this.navDrawerHelper = navDrawerHelper;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent){
        return new QuestionsListViewMvcImpl(layoutInflater,parent, this, navDrawerHelper);
    }

    public QuestionsListViewMvc getQuestionsRecyclerViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsRecyclerMvcImpl(layoutInflater,parent, this);
    }

    public QuestionsListItemViewMvc getQuestionsListItemViewMvc(@Nullable ViewGroup parent){
        return new QuestionsListItemMvcImpl(layoutInflater,parent);
    }

    public QuestionDetailsViewMvc getQuestionDetailsViewMvc(@Nullable ViewGroup parent) {
        return new QuestionDetailsViewMvcImpl(layoutInflater, parent, this);
    }

    public ToolbarViewMvc getToolbarViewMvc(@Nullable ViewGroup parent) {
        return new ToolbarViewMvc(
                layoutInflater,
                parent
        );
    }

    public NavDrawerViewMvc getNavDrawerViewMvc(@Nullable ViewGroup parent) {
        return new NavDrawerViewMvcImpl(layoutInflater, parent);
    }
}
