
    // Name: Tran Le
    // JAV1 - 1808
    // File name: MainActivity.java

package com.sunny.android.letran_ce08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Member variables
    private Toast feedback = null;
    private ArrayList<String> collection = new ArrayList<>();
    private static final String KEY = "DATA_KEY";
    private static final String STRING_KEY = "TYPED_STRING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_Submit).setOnClickListener(submitTapped);
    }

    // Code to save ArrayList data
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(KEY, collection);
        outState.putString(STRING_KEY, ((EditText)findViewById(R.id.txt_String)).getText().toString());
    }

    // Code to retrieve ArrayList data
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        collection = savedInstanceState.getStringArrayList(KEY);
        setArrayAdapter();

        EditText strText = (EditText)findViewById(R.id.txt_String);
        strText.setText(savedInstanceState.getString(STRING_KEY));
        strText.setSelection(strText.getText().length());
    }

    // Code to handle click event of the Submit button
    private final View.OnClickListener submitTapped = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (feedback != null) {
                feedback.cancel();
            }

            EditText strText = (EditText)findViewById(R.id.txt_String);
            String trueText = strText.getText().toString();
            String trimmedText = trueText.replace(" ", "");

            if (!trueText.isEmpty() && trimmedText.isEmpty()) {
                feedback = Toast.makeText(MainActivity.this, R.string.toast_allspaces, Toast.LENGTH_SHORT);
            } else if (trueText.isEmpty()) {
                feedback = Toast.makeText(MainActivity.this, R.string.toast_empty, Toast.LENGTH_SHORT);
            } else {
                if (!collection.contains(trueText)) {
                    collection.add(trueText);
                    setArrayAdapter();
                } else {
                    feedback = Toast.makeText(MainActivity.this, R.string.toast_alreadyadded,
                            Toast.LENGTH_SHORT);
                }
            }

            strText.setText("");
            if (feedback != null) {
                feedback.show();
            }
        }
    };

    // Code to set up array adapter
    private void setArrayAdapter() {
        if (collection != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    MainActivity.this, android.R.layout.simple_list_item_1, collection);

            ((ListView) findViewById(R.id.lvw_ListView)).setAdapter(adapter);
        }
    }
}
