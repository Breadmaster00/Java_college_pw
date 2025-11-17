import java.util.ArrayList;
import java.util.List;

class Client {
    private String clientId;
    private String name;
    private List<Account> accounts;
    
    public Client(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }
    
    public void addAccount(Account account) {
        accounts.add(account);
    }
    
    public List<Account> getAccounts() {
        return accounts;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return String.format("Клиент ID: %s, Имя: %s, Количество счетов: %d", 
                           clientId, name, accounts.size());
    }
}