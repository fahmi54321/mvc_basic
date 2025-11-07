package com.example.mvc.questions;

import android.util.Log;

import com.example.mvc.common.BaseObservable;
import com.example.mvc.common.Constants;
import com.example.mvc.networking.questions.QuestionSchema;
import com.example.mvc.networking.questions.QuestionsListResponseSchema;
import com.example.mvc.networking.StackoverflowApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchQuestionListUseCase extends BaseObservable<FetchQuestionListUseCase.Listener> {

    public interface Listener{

        void onQuestionFetchFailed();

        void onQuestionFetched(List<Question> questions);
    }

    private StackoverflowApi mStackoverflowApi;

    public FetchQuestionListUseCase(StackoverflowApi mStackoverflowApi) {
        this.mStackoverflowApi = mStackoverflowApi;
    }

    public void fetchQuestionAndNotify(){
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                        Log.e("response",""+response.body());
                        if (response.isSuccessful()) {
                            notifySuccess(response.body().getQuestions());
                        } else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                        notifyFailure();
                        Log.e("response Throwable",""+t);
                    }
                } );
    }

    private void notifyFailure() {
        for(Listener listener: getListeners()){
            listener.onQuestionFetchFailed();
        }
    }

    private void notifySuccess(List<QuestionSchema> questionSchemas) {
        for(Listener listener: getListeners()){
            List<Question> questions = new ArrayList<>(questionSchemas.size());
            for (QuestionSchema questionSchema : questionSchemas) {
                questions.add(new Question(questionSchema.getId(), questionSchema.getTitle()));
            }
            listener.onQuestionFetched(questions);
        }
    }
}
