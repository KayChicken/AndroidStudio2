package domain.sharedpref;

public interface UserPreferences {

    public void saveEmail(String email);

    public String getEmail();

    public void logOut();
}
