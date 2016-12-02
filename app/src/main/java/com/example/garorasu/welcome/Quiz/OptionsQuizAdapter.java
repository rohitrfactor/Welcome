package com.example.garorasu.welcome.Quiz;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.garorasu.welcome.R;

/**
 * Created by garorasu on 2/12/16.
 */

public class OptionsQuizAdapter extends BaseAdapter {

    private final String[] mOptions;
    private final int mLayoutId;
    private final String[] mAlphabet;

    /**
     * Creates an {@link OptionsQuizAdapter}.
     *
     * @param options The options to add to the adapter.
     * @param layoutId Must consist of a single {@link TextView}.
     */
    public OptionsQuizAdapter(String[] options, @LayoutRes int layoutId) {
        mOptions = options;
        mLayoutId = layoutId;
        mAlphabet = null;
    }

    /**
     * Creates an {@link OptionsQuizAdapter}.
     *
     * @param options The options to add to the adapter.
     * @param layoutId Must consist of a single {@link TextView}.
     * @param context The context for the adapter.
     * @param withPrefix True if a prefix should be given to all items.
     */
    public OptionsQuizAdapter(String[] options, @LayoutRes int layoutId,
                              Context context, boolean withPrefix) {
        mOptions = options;
        mLayoutId = layoutId;
        if (withPrefix) {
            mAlphabet = context.getResources().getStringArray(R.array.alphabet);
        } else {
            mAlphabet = null;
        }
    }

    @Override
    public int getCount() {
        return mOptions.length;
    }

    @Override
    public String getItem(int position) {
        return mOptions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        /* Important to return tru ein order to get checked items from this adapter correctly */
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(mLayoutId, parent, false);
        }
        String text = getText(position);
        ((TextView) convertView).setText(text);
        return convertView;
    }

    private String getText(int position) {
        String text;
        if (mAlphabet == null) {
            text = getItem(position);
        } else {
            text = getPrefix(position) + getItem(position);
        }
        return text;
    }

    private String getPrefix(int position) {
        final int length = mAlphabet.length;
        if (position >= length || 0 > position) {
            throw new IllegalArgumentException(
                    "Only positions between 0 and " + length + " are supported");
        }
        StringBuilder prefix;
        if (position < length) {
            prefix = new StringBuilder(mAlphabet[position]);
        } else {
            int tmpPosition = position % length;
            prefix = new StringBuilder(tmpPosition);
            prefix.append(getPrefix(position - tmpPosition));
        }
        prefix.append(". ");
        return prefix.toString();
    }
}

