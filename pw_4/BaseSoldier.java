public interface BaseSoldier {
    String getName();
    String getAddress();
    String getNationality();
    String getBirthDay();
    String getPosition();
    String getRank();
    
    void printInfo();

    void sendToServe();
    void sendToHomeland();

    boolean isServing();
}
