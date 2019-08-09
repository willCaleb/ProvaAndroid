package cursoandroid.com.provaandroid.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cursoandroid.com.provaandroid.R;
import cursoandroid.com.provaandroid.classes.BancoController;
import cursoandroid.com.provaandroid.classes.CriaBanco;

public class Alterar extends AppCompatActivity {

    private EditText titulo;
    private EditText autor;
    private Button alterar;
    private Button deletar;
    private Cursor cursor;
    private BancoController crud;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        Intent intent = getIntent();

        codigo = intent.getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        titulo = findViewById(R.id.txt_alterar_titulo);
        autor = findViewById(R.id.txt_alterar_autor);

        alterar = findViewById(R.id.btn_alterar);
        deletar = findViewById(R.id.btn_deletar);

        cursor = crud.carregarDadoById(Integer.parseInt(codigo));
        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alterarRegistro(Integer.parseInt(codigo),
                        titulo.getText().toString(),
                        autor.getText().toString());
                Intent intent = new Intent(Alterar.this, Consulta.class);
                startActivity(intent);
                finish();
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletar(Integer.parseInt(codigo));
                Intent intent = new Intent(Alterar.this, Consulta.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
