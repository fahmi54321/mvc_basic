package com.example.mvc.common.dependencyinjection.presentation;

import com.example.mvc.common.dependencyinjection.activity.ActivityComponent;
import com.example.mvc.screens.common.dialogs.infodialog.InfoDialog;
import com.example.mvc.screens.common.dialogs.promptdialog.PromptDialog;
import com.example.mvc.screens.common.main.MainActivity;
import com.example.mvc.screens.questiondetails.QuestionDetailsFragment;
import com.example.mvc.screens.questionslist.listview.QuestionsListFragment;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerListFragment;

import dagger.Component;

@PresentationScope
@Component(dependencies = {ActivityComponent.class},modules = {PresentationModule.class})
public interface PresentationComponent {
    void inject(QuestionsListFragment questionsListFragment);

    void inject(QuestionsRecyclerListFragment questionsRecyclerListFragment);

    void inject(QuestionDetailsFragment questionDetailsFragment);

    void inject(InfoDialog infoDialog);

    void inject(PromptDialog promptDialog);

    void inject(MainActivity mainActivity);
}
