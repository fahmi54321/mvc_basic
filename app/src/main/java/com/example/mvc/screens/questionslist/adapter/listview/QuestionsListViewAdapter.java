package com.example.mvc.screens.questionslist.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvc.questions.Question;
import com.example.mvc.screens.questionslist.QuestionsListItemMvcImpl;
import com.example.mvc.screens.questionslist.QuestionsListItemViewMvc;


public class QuestionsListViewAdapter extends ArrayAdapter<Question> implements QuestionsListItemViewMvc.Listener {

    private final OnQuestionClickListener mOnQuestionClickListener;

    @Override
    public void onQuestionClicked(Question question) {
        mOnQuestionClickListener.onQuestionClicked(question);
    }

    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }

    public QuestionsListViewAdapter(Context context,
                                    OnQuestionClickListener onQuestionClickListener) {
        super(context, 0);
        mOnQuestionClickListener = onQuestionClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            QuestionsListItemViewMvc viewMvc = new QuestionsListItemMvcImpl(
                    LayoutInflater.from(getContext()),
                    parent
            );
            viewMvc.registerListener(this);
            convertView = viewMvc.getRootView();
            convertView.setTag(viewMvc);
        }

        final Question question = getItem(position);

        // bind the data to views
        QuestionsListItemViewMvc viewMvc = (QuestionsListItemViewMvc) convertView.getTag();
        viewMvc.bindQuestion(question);


        return convertView;
    }
}
