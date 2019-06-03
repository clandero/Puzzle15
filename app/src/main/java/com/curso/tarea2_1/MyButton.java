package com.curso.tarea2_1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends android.support.v7.widget.AppCompatButton {
    private int bgId;

    public MyButton(Context context) {
        super(context);
    }
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        bgId = resId;
    }

    public int getBackgroundId() {
        return bgId;
    }
}
