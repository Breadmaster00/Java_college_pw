class Soldier implements BasicSoldier {
    private String lastname;
    private String name;
    private String fathername;
    private String address;
    private String nationality;
    private String birthday;
    private String position;
    private String rank;

    public Soldier(String lastname, String name, String fathername, String address, String nationality, String birthday, String position, String rank) {
        this.lastname = lastname;
        this.name = name;
        this.fathername = fathername;
        this.address = address;
        this.nationality = nationality;
        this.birthday = birthday;
        this.position = position;
        this.rank = rank;
    }

    @Override
    public String getFullName() {
        return this.lastname + " " + this.name + " " + this.fathername;
    }

    @Override
    public String 
}

