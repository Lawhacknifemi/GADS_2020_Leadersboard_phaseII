package com.example.leadersboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leadersboard.services.ApiFormSubmitService;
import com.example.leadersboard.services.ApiServiceBuilder;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {

    private Dialog dialog;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText githubLinkEditText;
    private String firstName;
    private String lastName;
    private String email;
    private String ghLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        firstNameEditText = findViewById(R.id.edTextFirstName);
        lastNameEditText = findViewById(R.id.edTectLastName);
        emailEditText = findViewById(R.id.edTextEmail);
        githubLinkEditText = findViewById(R.id.edTextGithub);

        ImageView backNavButton =  findViewById(R.id.back_imageButton);
        backNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: Validate form data before creating popup
                if (isValidFormData()) {
                    // Create dialog to confirm submit
                    createSubmitConfirmationDialog();
                }
            }
        });

}
    public void createSubmitConfirmationDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_window_submit);
        // Cancel imageButton
        ImageButton closeIBtn = dialog.findViewById(R.id.cancel_imageButton);
        // Yes Button
        Button continueBtn = dialog.findViewById(R.id.confirm_submit_button);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        closeIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Submit Form
                ApiFormSubmitService submitService = ApiServiceBuilder.buildApiService(ApiFormSubmitService.class);
                Call<Void> submitFormData = submitService.submitForm(
                        "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse",
                        firstName,
                        lastName,
                        email,
                        ghLink
                );

                submitFormData.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                        // Calls method to create and show success message dialog
                        createResponseDialog(R.drawable.ic_baseline_check_circle_24, R.string.submission_success);

                        if (response.isSuccessful()) {
                            clearFormEntry();
                            //Log.e(TAG, "post-onResponse: " + response.message() + "\n\n" + response.body() + "\n\n" + response.errorBody());
                            Toast.makeText(FormActivity.this, "Success " + response.message(), Toast.LENGTH_LONG).show();

                        }
                        else{
                            //createResponseDialog(R.drawable.ic_baseline_warning_24, R.string.submission_failure);
                            Toast.makeText(FormActivity.this, "Response Error" + response.errorBody(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                        // Calls method to create and show failure message dialog
                        createResponseDialog(R.drawable.ic_baseline_warning_24, R.string.submission_failure);
                        Toast.makeText(FormActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    public void createResponseDialog(int responseImage, int responseText) {
        dialog.dismiss();

        dialog = new Dialog(FormActivity.this);
        dialog.setContentView(R.layout.dialog_window_response);
        ImageView responseImg = dialog.findViewById(R.id.response_imageView);
        TextView responseTV = dialog.findViewById(R.id.response_textView);

        //populate with appropriate content base on response message
        responseImg.setImageResource(responseImage);
        responseTV.setText(getString(responseText));
        dialog.show();
    }

    public void clearFormEntry() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        githubLinkEditText.setText("");
    }

    public boolean isValidFormData() {
        getFormData();

        boolean isValid = false;

        if (firstName.trim().isEmpty()) {
            firstNameEditText.requestFocus();
            firstNameEditText.setError("First Name Required!");
        } else if (lastName.trim().isEmpty()) {
            lastNameEditText.requestFocus();
            lastNameEditText.setError("Last Name Required!");
        } else if (email.trim().isEmpty()) {
            emailEditText.requestFocus();
            emailEditText.setError("Email Required!");
        } else if (ghLink.trim().isEmpty()) {
            githubLinkEditText.requestFocus();
            githubLinkEditText.setError("Project Link Required!");
        } else {
            isValid = true;
        }

        return isValid;

    }

    private void getFormData() {
        firstName = firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        email = emailEditText.getText().toString();
        ghLink = githubLinkEditText.getText().toString();
    }


}