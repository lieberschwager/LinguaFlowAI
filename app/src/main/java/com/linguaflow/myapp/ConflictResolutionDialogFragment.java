package com.linguaflow.myapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConflictResolutionDialogFragment extends DialogFragment {

    public interface ConflictResolutionListener {
        void onKeepExisting();
        void onOverwrite();
    }

    private String word;
    private String existingGerman;
    private String existingExample;
    private String newGerman;
    private String newExample;
    private ConflictResolutionListener listener;

    public ConflictResolutionDialogFragment(String word,
                                            String existingGerman, String existingExample,
                                            String newGerman, String newExample,
                                            ConflictResolutionListener listener) {
        this.word = word;
        this.existingGerman = existingGerman;
        this.existingExample = existingExample;
        this.newGerman = newGerman;
        this.newExample = newExample;
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = "⚠️ Konflikt bei \"" + word + "\"\n\n"
                + "🔹 Vorhanden:\n" + existingGerman + "\n" + existingExample + "\n\n"
                + "🔸 Neu:\n" + newGerman + "\n" + newExample;

        return new AlertDialog.Builder(getActivity())
                .setTitle("Konflikt lösen")
                .setMessage(message)
                .setPositiveButton("Überschreiben", (dialog, which) -> {
                    if (listener != null) listener.onOverwrite();
                })
                .setNegativeButton("Behalten", (dialog, which) -> {
                    if (listener != null) listener.onKeepExisting();
                })
                .create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Optional: Kontextprüfung
    }
}