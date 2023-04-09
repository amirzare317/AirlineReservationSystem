public class User {
    private String userName;
    private String password;
    private int charge;
    String string = new String();


//    public User(String userName, String password, int charge) {
//        this.userName = userName;
//        this.password = password;
//        this.charge = charge;
//    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public boolean isAdmin(String userName , String password){

        if(userName.equals("admin") && password.equals("admin")){
            return true;
        }
        else {
            return false;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }
}
