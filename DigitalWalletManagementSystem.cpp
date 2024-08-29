#include <bits/stdc++.h>
using namespace std;

struct UserAccount {
    int id;
    int funds;
};

class Bank {
private:
    vector<UserAccount> accounts;
    unordered_map<int, int> idToIndex;

public:
    void addAccount(int id, int funds) {
        accounts.push_back({id, funds});
        idToIndex[id] = accounts.size() - 1;
    }

    void transferFunds(int fromID, int toID, int amount) {
        if (idToIndex.find(fromID) == idToIndex.end() || idToIndex.find(toID) == idToIndex.end()) {
            cout << "Failure: One or both accounts do not exist." << endl;
            return;
        }

        int fromIndex = idToIndex[fromID];
        int toIndex = idToIndex[toID];

        if (accounts[fromIndex].funds >= amount) {
            accounts[fromIndex].funds -= amount;
            accounts[toIndex].funds += amount;
            cout << "Success" << endl;
        } else {
            cout << "Failure: Insufficient funds." << endl;
        }
    }

    void printAccounts() {
        sort(accounts.begin(), accounts.end(), [](const UserAccount &a, const UserAccount &b) {
            if (a.funds == b.funds)
                return a.id < b.id;
            return a.funds < b.funds;
        });
        
        for (const auto &account : accounts) {
            cout << account.id << " " << account.funds << endl;
        }
    }
};

int main() {
    Bank bank;
    int totalAccounts;
    cin >> totalAccounts;

    for (int i = 0; i < totalAccounts; ++i) {
        int id, funds;
        cin >> id >> funds;
        bank.addAccount(id, funds);
    }

    int transactionCount;
    cin >> transactionCount;

    for (int i = 0; i < transactionCount; ++i) {
        int fromID, toID, amount;
        cin >> fromID >> toID >> amount;
        bank.transferFunds(fromID, toID, amount);
    }

    cout << endl;
    bank.printAccounts();

    return 0;
}
