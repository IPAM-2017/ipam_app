package uescc.com.clientesipam;

import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ModificarClienteActivity extends AppCompatActivity {

    Spinner cmbModoPago;
    EditText nombreCli;
    EditText apellidosCli;
    EditText duiCli;
    EditText codigoCli;
    Button btnModificar;
    Button btnCancelar;
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
        setContentView(R.layout.activity_modificar_cliente);
        Bundle extras = getIntent().getExtras();
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        nombreCli = (EditText) findViewById(R.id.nombreText);
        //cargo el nombre
        nombreCli.setText(extras.getString("nombre"));
        apellidosCli = (EditText) findViewById(R.id.apellidoText);
        //cargo el apellido
        apellidosCli.setText(extras.getString("apellido"));
        duiCli = (EditText) findViewById(R.id.duiText);
        //cargo el dui
        duiCli.setText(extras.getString("dui"));
        codigoCli = (EditText) findViewById(R.id.codigoText);
        //cargo el codigo
        codigoCli.setText(extras.getString("codigo"));
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Seleccione Modo Pago", Toast.LENGTH_LONG).show();
            }
        });

        //Cargo el comboBox
                cmbModoPago.setSelection(adapter.getPosition(extras.getString("pago")));

        //Retorno a MainActivity
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });


        //Cargo los radioButtom
        RadioGroup rg = (RadioGroup)findViewById(R.id.tipoRgbtn);
        rg.clearCheck();
        switch (extras.getString("tipo")){
            case "Minorista":
                checked = true;
                rg.check(R.id.rbtnMinorista);
                this.cliente.setTipoCli("Minorista");
                break;

            case "Mayorista":
                checked = true;
                rg.check(R.id.rbtnMayorista);
                this.cliente.setTipoCli("Mayorista");
                break;
        }


    }


    public  void limpiarForm(){
        nombreCli.getText().clear();
        apellidosCli.getText().clear();
        duiCli.getText().clear();

    }
    //Metodo que manega en boton Crear, en el xml se define el metodo que ejecutara dicho boton y se
    // utilizan los parametros View view
    public void modificarCliente(View view){
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
            this.cliente.setCodigoCli(codigoCli.getText().toString());
            //Aca modifico al clente
            MainActivity.clientes.set(Integer.parseInt(codigoCli.getText().toString())-1, cliente);
            Toast.makeText(getApplicationContext(), "Modificado : " + MainActivity.clientes.get(MainActivity.clientes.size()-1).getNombreCli().toString(), Toast.LENGTH_LONG).show();
            this.cliente = new Cliente();
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
