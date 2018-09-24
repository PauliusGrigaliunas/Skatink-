package pg.grigaliunas.paulius.skatink;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChildActivity extends AppCompatActivity {

    private TextView mTextMessage;

    DatabaseHelper mydb;
    EditText username, password, name, parentID;
    Button addbtn;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        parentID = (EditText) findViewById(R.id.parentIDText);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        name =  (EditText)  findViewById(R.id.nameText);
        addbtn = (Button) findViewById(R.id.addBtn2);
        AddData();
    }

    public void AddData() {
        addbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertChildData(
                                Integer.parseInt(parentID.getText().toString()),
                                username.getText().toString(),
                                password.getText().toString(),
                                name.getText().toString());

                        if (isInserted == true) {
                            Toast.makeText(ChildActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ChildActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }
                    }

                }
        );
    }
}
