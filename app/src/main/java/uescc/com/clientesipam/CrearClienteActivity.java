package uescc.com.clientesipam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class CrearClienteActivity extends AppCompatActivity {
    Spinner cmbModoPago;
    EditText nombreCli;
    EditText apellidosCli;
    EditText duiCli;
    EditText codigoCli;
    Button btnCrear;
    Cliente cliente = new Cliente();
    RadioGroup tipoCli;
    boolean checked;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);
        btnCrear = (Button) findViewById(R.id.btnCrear);
        nombreCli = (EditText) findViewById(R.id.nombreText);
        apellidosCli = (EditText) findViewById(R.id.apellidoText);
        duiCli = (EditText) findViewById(R.id.duiText);
        codigoCli = (EditText) findViewById(R.id.codigoText);
        limpiarForm();
        tipoCli= (RadioGroup) findViewById(R.id.tipoRgbtn);
        cmbModoPago = (Spinner) findViewById(R.id.opcionSpn);
// Crea el adaptador usando un Strign Array en este caso definido en values strings para hacer posible aplicacion multi-idioma
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_cliente, android.R.layout.simple_spinner_item);
// Especifica como sera el layout para mostrar el listado al desplegarse
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbModoPago.setAdapter(adapter);

        // Permite Seleccionar de la lista
        cmbModoPago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id) {
                Toast.makeText(getApplicationContext(), "Pago : " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Seleccione Modo Pago", Toast.LENGTH_LONG).show();
            }
        });





    }

    public  void limpiarForm(){
        nombreCli.getText().clear();
        apellidosCli.getText().clear();
        duiCli.getText().clear();
        codigoCli.setText(""+(MainActivity.clientes.size()+1));
    }
    //Metodo que manega en boton Crear, en el xml se define el metodo que ejecutara dicho boton y se
    // utilizan los parametros View view
    public void crearCliente(View view){
        if (duiCli.getText().toString().isEmpty() ||
            nombreCli.getText().toString().isEmpty() ||
            apellidosCli.getText().toString().isEmpty() ||
            !checked    )
        {
            Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_LONG).show();

        }else{

            this.cliente.setPagoCli(cmbModoPago.getSelectedItem().toString());
            this.cliente.setNombreCli(nombreCli.getText().toString());
            this.cliente.setApellidoCli(apellidosCli.getText().toString());
            this.cliente.setDuiCli(duiCli.getText().toString());
            this.cliente.setCodigoCli(""+(MainActivity.clientes.size()+1));
            MainActivity.clientes.add(cliente);
            Toast.makeText(getApplicationContext(), "Agregado : " + MainActivity.clientes.get(MainActivity.clientes.size()-1).getNombreCli().toString(), Toast.LENGTH_LONG).show();
            this.cliente = new Cliente();
            limpiarForm();
            finish();

        }

    }

    public void onRadioButtonClicked(View view) {

        checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbtnMayorista:
                if (checked)
                    this.cliente.setTipoCli("Mayorista");

                break;
            case R.id.rbtnMinorista:
                if (checked)
                    this.cliente.setTipoCli("Minorista");

                break;



        }
    }
}
