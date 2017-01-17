package com.pinnacle.garorasu.welcome.Quiz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pinnacle.garorasu.welcome.Quiz.Category.Category;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.AlphaPickerQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.FillBlankQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.FillTwoBlanksQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.FourQuarterQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.MultiSelectQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.PickerQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.SelectItemQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.ToggleTranslateQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.TrueFalseQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizView.AbsQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.AlphaPickerQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.FillBlankQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.FillTwoBlanksQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.FourQuarterQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.MultiSelectQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.PickerQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.SelectItemQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.ToggleTranslateQuizView;
import com.pinnacle.garorasu.welcome.Quiz.quizView.TrueFalseQuizView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by garorasu on 2/12/16.
 */

public class QuizAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Quiz> mQuizzes;
    private final Category mCategory;
    private final int mViewTypeCount;
    private List<String> mQuizTypes;

    public QuizAdapter(Context context, Category category) {
        mContext = context;
        mCategory = category;
        mQuizzes = category.getQuizzes();
        mViewTypeCount = calculateViewTypeCount();

    }

    private int calculateViewTypeCount() {
        Set<String> tmpTypes = new HashSet<>();
        for (int i = 0; i < mQuizzes.size(); i++) {
            tmpTypes.add(mQuizzes.get(i).getType().getJsonName());
        }
        mQuizTypes = new ArrayList<>(tmpTypes);
        return mQuizTypes.size();
    }

    @Override
    public int getCount() {
        return mQuizzes.size();
    }

    @Override
    public Quiz getItem(int position) {
        return mQuizzes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mQuizzes.get(position).getId();
    }

    @Override
    public int getViewTypeCount() {
        return mViewTypeCount;
    }

    @Override
    public int getItemViewType(int position) {
        return mQuizTypes.indexOf(getItem(position).getType().getJsonName());
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Quiz quiz = getItem(position);
        if (convertView instanceof AbsQuizView) {
            if (((AbsQuizView) convertView).getQuiz().equals(quiz)) {
                return convertView;
            }
        }
        convertView = getViewInternal(quiz);
        return convertView;
    }

    private AbsQuizView getViewInternal(Quiz quiz) {
        if (null == quiz) {
            throw new IllegalArgumentException("Quiz must not be null");
        }
        return createViewFor(quiz);
    }

    private AbsQuizView createViewFor(Quiz quiz) {
        switch (quiz.getType()) {
            case ALPHA_PICKER:
                return new AlphaPickerQuizView(mContext, mCategory, (AlphaPickerQuiz) quiz);
            case FILL_BLANK:
                return new FillBlankQuizView(mContext, mCategory, (FillBlankQuiz) quiz);
            case FILL_TWO_BLANKS:
                return new FillTwoBlanksQuizView(mContext, mCategory, (FillTwoBlanksQuiz) quiz);
            case FOUR_QUARTER:
                return new FourQuarterQuizView(mContext, mCategory, (FourQuarterQuiz) quiz);
            case MULTI_SELECT:
                return new MultiSelectQuizView(mContext, mCategory, (MultiSelectQuiz) quiz);
            case PICKER:
                return new PickerQuizView(mContext, mCategory, (PickerQuiz) quiz);
            case SINGLE_SELECT:
            case SINGLE_SELECT_ITEM:
                return new SelectItemQuizView(mContext, mCategory, (SelectItemQuiz) quiz);
            case TOGGLE_TRANSLATE:
                return new ToggleTranslateQuizView(mContext, mCategory,
                        (ToggleTranslateQuiz) quiz);
            case TRUE_FALSE:
                return new TrueFalseQuizView(mContext, mCategory, (TrueFalseQuiz) quiz);
        }
        throw new UnsupportedOperationException(
                "Quiz of type " + quiz.getType() + " can not be displayed.");
    }
}
