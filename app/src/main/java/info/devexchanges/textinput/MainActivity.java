package info.devexchanges.textinput;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Bind(R.id.email_field)
    TextInputLayout email;

    @Bind(R.id.username_field)
    TextInputLayout name;

    @Bind(R.id.country_field)
    TextInputLayout country;

    @Bind(R.id.age_field)
    TextInputLayout age;

    @Bind(R.id.btn_submit)
    Button btnSubmit;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordiLayout;

    @OnClick(R.id.btn_submit)
    public void submit(View view) {
        checkInputLayout();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); // inject views in this activity
    }

    private void checkInputLayout() {
        if (!validateEmail(email.getEditText())) {
            email.setError("Invalid email");
            showSnackBar("Example email: abc@gmail.com");
        } else if (!validateField(name)) {
            name.setError("Please input your name");
            showSnackBar("Example name: Hong Thai");
        } else if (!validateField(country)) {
            country.setError("Please input your country");
            showSnackBar("Example country: Vietnam");
        } else if (!validateField(age)) {
            age.setError("Please input yout age");
            showSnackBar("Put number of your age now");
        } else {
            email.setError("");
            showSnackBar("Yes, you've completed all fields with correct information");
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(coordiLayout, message, Snackbar.LENGTH_LONG)
                .setAction("OK", onSnackBarClickListener());

        snackbar.setActionTextColor(Color.GREEN);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    private View.OnClickListener onSnackBarClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked SnackBar Button", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public boolean validateEmail(EditText emailText) {
        matcher = pattern.matcher(emailText.getText().toString().trim());
        return matcher.matches();
    }

    public boolean validateField(TextInputLayout inputLayout) {
        if (inputLayout.getEditText().getText().toString().trim().equals("")) {
            return false;
        } else {
            inputLayout.setError("");
            return true;
        }
    }
}
