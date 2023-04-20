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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Check if the user is admin or not.
     * @param userName is the username of the user.
     * @param password is the password of the user.
     * @return If userName and password both equal to admin it returns true, otherwise it returns false.
     */
    public boolean isAdmin(String userName, String password) {
//        findI(userName, password);
        if (userName.equals("admin") && password.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the user has registered before or not.
     * @param userName is the username of the user.
     * @param password is the password of the user.
     * @return If userName and password both equal to userName and password while registration it returns true, otherwise it returns false.
     */
    public boolean isRegisteredBefore(String userName, String password) {
        for (int k = 0; k < userInfo.passengerUser.length; k++) {
            if ((userInfo.passengerUser[k] != null) &&
                    (userInfo.passengerUser[k].getUserName().equals(userName) &&
                            userInfo.passengerUser[k].getPassword().equals(password))) {
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
