package uescc.com.clientesipam;

public class Cliente {
    private String nombreCli;
    private String apellidoCli;
    private String codigoCli;
    private String tipoCli;
    private String pagoCli;
    private String duiCli;

public Cliente(){}
public Cliente(String codigo, String nombre, String apellido, String tipo, String pago, String dui){
    this.codigoCli = codigo;
    this.nombreCli =nombre;
    this.apellidoCli = apellido;
    this.tipoCli = tipo;
    this.pagoCli = pago;
    this.duiCli = dui;

}

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getApellidoCli() {
        return apellidoCli;
    }

    public void setApellidoCli(String apellidoCli) {
        this.apellidoCli = apellidoCli;
    }

    public String getCodigoCli() {
        return codigoCli;
    }

    public void setCodigoCli(String codigoCli) {
        this.codigoCli = codigoCli;
    }

    public String getTipoCli() {
        return tipoCli;
    }

    public void setTipoCli(String tipoCli) {
        this.tipoCli = tipoCli;
    }

    public String getPagoCli() {
        return pagoCli;
    }

    public void setPagoCli(String pagoCli) {
        this.pagoCli = pagoCli;
    }

    public String getDuiCli() {
        return duiCli;
    }

    public void setDuiCli(String duiCli) {
        this.duiCli = duiCli;
    }




}
