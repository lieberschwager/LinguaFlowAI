package com.linguaflow.myapp;

import android.content.Context;

/**
 * Liefert visuelle Labels für den Lernstatus einer Vokabel.
 * Nutzt ProgressTracker zur Klassifizierung.
 */
public class ProgressBadge {

    public enum Status {
        NEW, REPEATING, MASTERED
    }

    public static Status getStatus(Context context, String word) {
        int count = ProgressTracker.getCount(context, word);

        if (count == 0) {
            return Status.NEW;
        } else if (count < 5) {
            return Status.REPEATING;
        } else {
            return Status.MASTERED;
        }
    }

    public static String getLabel(Context context, String word) {
        Status status = getStatus(context, word);
        switch (status) {
            case NEW:
                return "🆕 Neu";
            case REPEATING:
                return "🔄 Wiederholen";
            case MASTERED:
                return "✅ Gelernt";
            default:
                return "";
        }
    }
}