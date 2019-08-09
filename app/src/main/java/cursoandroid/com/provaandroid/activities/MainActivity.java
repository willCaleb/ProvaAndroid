package cursoandroid.com.provaandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.com.provaandroid.R;
import cursoandroid.com.provaandroid.classes.BancoController;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button botaoCadastrar = findViewById(R.id.button);
         Button listar = findViewById(R.id.btn_listar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.editText);
                EditText autor = (EditText)findViewById((R.id.editText2));
                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String resultado;

                resultado = crud.insereDado(tituloString,autorString);

                titulo.setText("");
                autor.setText("");
                Toast.makeText(getApplicationContext(), "Livro inserido com sucesso!", Toast.LENGTH_SHORT);
                titulo.requestFocus();
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Consulta.class);
                startActivity(intent);
            }
        });
    }
}
