package com.sourav.app.listener;

import com.sourav.app.json.model.Student;
import org.springframework.batch.core.SkipListener;

public class SkipListenerImpl implements SkipListener<Student, Student> {
    @Override
    public void onSkipInRead(Throwable t) {
        SkipListener.super.onSkipInRead(t);
    }

    @Override
    public void onSkipInWrite(Student item, Throwable t) {
        SkipListener.super.onSkipInWrite(item, t);
    }

    @Override
    public void onSkipInProcess(Student item, Throwable t) {
        SkipListener.super.onSkipInProcess(item, t);
    }
}
