package com.pinnacle.garorasu.welcome.Quiz;

import com.pinnacle.garorasu.welcome.Quiz.quizModel.AlphaPickerQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.FillBlankQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.FillTwoBlanksQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.FourQuarterQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.MultiSelectQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.PickerQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.SelectItemQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.ToggleTranslateQuiz;
import com.pinnacle.garorasu.welcome.Quiz.quizModel.TrueFalseQuiz;

/**
 * Created by garorasu on 2/12/16.
 */

public enum QuizType {

    ALPHA_PICKER(JsonAttributes.QuizType.ALPHA_PICKER, AlphaPickerQuiz.class),
    FILL_BLANK(JsonAttributes.QuizType.FILL_BLANK, FillBlankQuiz.class),
    FILL_TWO_BLANKS(JsonAttributes.QuizType.FILL_TWO_BLANKS, FillTwoBlanksQuiz.class),
    FOUR_QUARTER(JsonAttributes.QuizType.FOUR_QUARTER, FourQuarterQuiz.class),
    MULTI_SELECT(JsonAttributes.QuizType.MULTI_SELECT, MultiSelectQuiz.class),
    PICKER(JsonAttributes.QuizType.PICKER, PickerQuiz.class),
    SINGLE_SELECT(JsonAttributes.QuizType.SINGLE_SELECT, SelectItemQuiz.class),
    SINGLE_SELECT_ITEM(JsonAttributes.QuizType.SINGLE_SELECT_ITEM, SelectItemQuiz.class),
    TOGGLE_TRANSLATE(JsonAttributes.QuizType.TOGGLE_TRANSLATE, ToggleTranslateQuiz.class),
    TRUE_FALSE(JsonAttributes.QuizType.TRUE_FALSE, TrueFalseQuiz.class);

    private final String mJsonName;
    private final Class<? extends Quiz> mType;

    QuizType(final String jsonName, final Class<? extends Quiz> type) {
        mJsonName = jsonName;
        mType = type;
    }

    public String getJsonName() {
        return mJsonName;
    }

    public Class<? extends Quiz> getType() {
        return mType;
    }
}
