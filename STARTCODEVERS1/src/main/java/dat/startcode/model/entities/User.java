package dat.startcode.model.entities;

import java.util.Objects;

public class User
{
    private String username;
    private String password;

    private int idRole;
    private int idUser;



    public User(String username, String password, int idRole)
    {
        this.username = username;
        this.password = password;
        this.idRole = idRole;
    }
    public User(String username, String password, int idRole, int idUser)
    {
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.idUser=idUser;
    }





    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) &&
                user.getIdRole() == getIdRole();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUsername(), getPassword(), getIdRole());
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdUser() {
        return idUser;
    }
}
