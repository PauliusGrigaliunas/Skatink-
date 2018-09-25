package pg.grigaliunas.paulius.skatink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    private DatabaseHelper mydb;
    private EditText username, password;
    private Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mydb = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        CheckData();
        OpenWindow();
    }

    public void OpenWindow(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RegistrationActivity.class));
            }
        });
    }
    public void CheckData(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView);
                //tv.setText(mydb.Validate(String.valueOf(username.getText()), password.getText().toString()));
                if(mydb.Validate(String.valueOf(username.getText()), password.getText().toString())!= null)
                    startActivity(new Intent(LogInActivity.this, BasicActivity.class));
                else tv.setText( " incorrect username or password " );
            }
        });

    }

}
