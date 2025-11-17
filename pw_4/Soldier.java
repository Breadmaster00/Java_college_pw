public class Soldier implements BaseSoldier {
    private String name;
    private String address;
    private String nationality;
    private String birthDay;
    private String position;
    private String rank;
    private boolean inTheService;

    public Soldier(
        String name,
        String address,
        String nationality,
        String birthday,
        String postion,
        String rank,
        boolean inTheService) {
        this.name = name;
        this.address = address;
        this.nationality = nationality;
        this.birthDay = birthday;
        this.position = postion;
        this.rank = rank;
        this.inTheService = inTheService;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getNationality() {
        return nationality;
    }

    @Override
    public String getBirthDay() {
        return birthDay;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public String getRank() {
        return rank;
    }

    @Override
    public void printInfo() {
        System.out.println(name + ' ' + address + ' ' + nationality + ' ' + birthDay + ' ' + position + ' ' + rank + ' ' + inTheService);
    }

    @Override
    public void sendToServe() {
        if (isServing()) {
            System.out.println(name + "и так на службе");
        } else {
            inTheService = true;
            System.out.println(name + " отправлен на службу");
        }
    }

    @Override
    public void sendToHomeland() {
        if (isServing()) {
            inTheService = false;
            System.out.println(name + " возвращается домой");
        } else {
            System.out.println("Нельзя вернуть, он и так дома");
        }
    }

    @Override
    public boolean isServing() {
        return inTheService;
    }
}