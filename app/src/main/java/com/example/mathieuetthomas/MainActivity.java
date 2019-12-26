package com.example.mathieuetthomas;

import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements Home.OnHomeInteractionListener, AddClient.OnAddClientInteractionListener {

    private NavController navController;
    private ViewModel viewModel;
    @BindView(R.id.button) Button valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // on crée une instance de notre ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onHomeActivityInteraction(Uri uri, String nomSaisi, String prenomSaisi, String dateSaisie, String villeSaisie) {
        Client nouveauClient = new Client(nomSaisi, prenomSaisi, dateSaisie, villeSaisie, "0000000000");
        EditText phoneNumber = findViewById(R.id.edit_text_phone);
        if (phoneNumber != null) {
            nouveauClient.setPhone(phoneNumber.getText().toString());
        }
        viewModel.insert(nouveauClient);
        navController.navigate(R.id.action_home_to_add_client);
    }

    public void reset(MenuItem item) {
        EditText nomSaisi = findViewById(R.id.editText2);
        if (nomSaisi != null) nomSaisi.setText("");
        EditText prenomSaisi = findViewById(R.id.editText3);
        if (prenomSaisi != null) prenomSaisi.setText("");
        EditText dateSaisie = findViewById(R.id.editText);
        if (dateSaisie != null) dateSaisie.setText("");
        Spinner villeSaisie = findViewById(R.id.editText4);
        if (villeSaisie != null) villeSaisie.setSelection(0);
        EditText phoneNumberSaisi = findViewById(R.id.edit_text_phone);
        if (phoneNumberSaisi != null) phoneNumberSaisi.setText("");
    }

    public void addPhoneNumber(MenuItem item) {
        LinearLayout rootView = findViewById(R.id.phoneNumberContainer);
        EditText phoneNumberInput = new EditText(this);
        phoneNumberInput.setId(R.id.edit_text_phone);
        phoneNumberInput.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        phoneNumberInput.setInputType(InputType.TYPE_CLASS_PHONE);
        rootView.addView(phoneNumberInput);
    }

    @Override
    public void onAddClient(Uri uri) {
        navController.navigate(R.id.action_add_client_to_home);
    }
}
