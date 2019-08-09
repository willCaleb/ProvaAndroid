package cursoandroid.com.provaandroid.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.R.layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import cursoandroid.com.provaandroid.R;
import cursoandroid.com.provaandroid.classes.BancoController;
import cursoandroid.com.provaandroid.classes.CriaBanco;

public class Consulta extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consulta);
        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {CriaBanco.ID, CriaBanco.TITULO, CriaBanco.AUTOR};
        int[] idViews = new int[] {R.id.idLivro, R.id.nomeLivro, R.id.idAutor};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_row,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(Consulta.this, Alterar.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
