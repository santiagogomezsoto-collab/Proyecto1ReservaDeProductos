package model;

//interfaz para funcinario y demas <------------------
public class Usuario {

    private String id;
    private String clave;
    private Rol rol; //referencia a Rol

    public Usuario(){ //constructor vacio para la persistencia usando XML
    }

    public Usuario(String id, String clave, Rol rol){
        this.id = id;
        this.clave = clave;
        this.rol = rol;
    }


        //setters y getters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }



}
