package Modelos;

public class UsuarioMOD {

    private int codigo;
    private String idUsuario;
    private String contrasenha;
    private String nombre;
    private String rol;

    public UsuarioMOD() {
    }

    public UsuarioMOD(int codigo, String idUsuario, String contrasenha, String nombre, String rol) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.contrasenha = contrasenha;
        this.nombre = nombre;
        this.rol = rol;
    }

    public UsuarioMOD(String idUsuario, String contrasenha, String nombre, String rol) {
        this.idUsuario = idUsuario;
        this.contrasenha = contrasenha;
        this.nombre = nombre;
        this.rol = rol;
    }

    public UsuarioMOD(int codigo, String idUsuario, String nombre, String rol) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.rol = rol;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void verificarCamposRegistro() throws Exception {
        if (idUsuario == null || idUsuario.isBlank()) {
            throw new Exception("El usuario no puede estar vacío.");
        }
        if (contrasenha == null || contrasenha.isBlank()) {
            throw new Exception("La contraseña no puede estar vacía.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre no puede estar vacío.");
        }
        if (rol == null || rol.isBlank()) {
            throw new Exception("El rol no puede estar vacío.");
        }
    }

    public void verificarCamposEdicion() throws Exception {
        if (idUsuario == null || idUsuario.isBlank()) {
            throw new Exception("El usuario no puede estar vacío.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre no puede estar vacío.");
        }
        if (rol == null || rol.isBlank()) {
            throw new Exception("El rol no puede estar vacío.");
        }
    }
}
