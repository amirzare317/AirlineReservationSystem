public class User {
    private String userName;
    private String password;
    private int charge;
    private String bookedTickets;

    Passenger userInfo;
    String string = new String();

    public String getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(String bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
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

//    int index = 20;
//    public void findI(String userName,String password){
//        int flag = 0;
//        for (int i = 0; i < userInfo.passengerUser.length; i++) {
//            if ((userInfo.passengerUser[i] != null) &&
//                    (userInfo.passengerUser[i].getPassword().equals(password)) &&
//                    (userInfo.passengerUser[i].getUserName().equals(userName))){
//                index = i;
////                flag = 1;
//            }
//        }
//    }
    public boolean isAdmin(String userName , String password){
//        findI(userName, password);
        if(userName.equals("admin") && password.equals("admin")){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isRegisteredBefore(String userName, String password){
        for (int i = 0; i < userInfo.passengerUser.length; i++) {
            if (    (userInfo.passengerUser[i] != null) &&
                    (userInfo.passengerUser[i].getUserName().equals(userName) &&
                    userInfo.passengerUser[i].getPassword().equals(password))){
                return true;
            }
        }
        return false;
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
