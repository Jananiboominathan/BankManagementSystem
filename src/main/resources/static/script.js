const API = "/api/bank";

function saveAccount(account) {
    localStorage.setItem("account", JSON.stringify(account));
}

function getAccount() {
    return JSON.parse(localStorage.getItem("account"));
}

async function register() {
    const account = {
        accountNumber: document.getElementById("accountNumber").value.trim(),
        customerName: document.getElementById("customerName").value.trim(),
        email: document.getElementById("email").value.trim(),
        password: document.getElementById("password").value,
        balance: Number(document.getElementById("balance").value)
    };

    const message = document.getElementById("message");

    try {
        const response = await fetch(`${API}/register`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(account)
        });

        const data = await response.text();

        if (!response.ok) {
            message.innerText = data;
            return;
        }

        message.innerText = "Account created successfully!";
        message.style.color = "green";

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1000);

    } catch (error) {
        message.innerText = "Server connection failed.";
    }
}

async function login() {
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const message = document.getElementById("message");

    try {
        const response = await fetch(`${API}/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            message.innerText = await response.text();
            return;
        }

        const account = await response.json();
        saveAccount(account);
        window.location.href = "dashboard.html";

    } catch (error) {
        message.innerText = "Server connection failed.";
    }
}

function updateDashboard(account) {
    const nameElement = document.getElementById("customerNameDisplay");
    const accountElement = document.getElementById("accountNumberDisplay");
    const balanceElement = document.getElementById("balance");

    if (nameElement) nameElement.innerText = account.customerName;
    if (accountElement) accountElement.innerText = account.accountNumber;
    if (balanceElement) {
        balanceElement.innerText =
            `₹${Number(account.balance).toFixed(2)}`;
    }
}

async function deposit() {
    await changeBalance("/deposit");
}

async function withdraw() {
    await changeBalance("/withdraw");
}

async function changeBalance(endpoint) {
    const account = getAccount();
    const amount = Number(document.getElementById("amount").value);
    const message = document.getElementById("message");

    if (!account) {
        window.location.href = "login.html";
        return;
    }

    if (!amount || amount <= 0) {
        message.innerText = "Enter a valid amount.";
        return;
    }

    try {
        const response = await fetch(`${API}${endpoint}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                accountNumber: account.accountNumber,
                amount
            })
        });

        if (!response.ok) {
            message.innerText = await response.text();
            message.style.color = "#b91c1c";
            return;
        }

        const updatedAccount = await response.json();
        saveAccount(updatedAccount);
        updateDashboard(updatedAccount);

        message.innerText = endpoint.includes("deposit")
            ? "Deposit successful."
            : "Withdrawal successful.";

        message.style.color = "#15803d";
        document.getElementById("amount").value = "";

        await loadTransactions();

    } catch (error) {
        message.innerText = "Server connection failed.";
    }
}

async function loadTransactions() {
    const account = getAccount();
    const list = document.getElementById("transactionList");

    if (!account || !list) return;

    try {
        const response = await fetch(
            `${API}/transactions/${encodeURIComponent(account.accountNumber)}`
        );

        if (!response.ok) {
            list.innerText = "Unable to load transactions.";
            return;
        }

        const transactions = await response.json();

        if (transactions.length === 0) {
            list.innerHTML = "<p>No transactions yet.</p>";
            return;
        }

        list.innerHTML = transactions.map(txn => `
            <div class="txn-item">
                <span class="${txn.type.toLowerCase()}">
                    <strong>${txn.type}</strong>
                </span>
                <span>₹${Number(txn.amount).toFixed(2)}</span>
                <span>${new Date(txn.transactionDate).toLocaleString()}</span>
            </div>
        `).join("");

    } catch (error) {
        list.innerText = "Server connection failed.";
    }
}

function logout() {
    localStorage.removeItem("account");
    window.location.href = "login.html";
}

window.addEventListener("DOMContentLoaded", () => {
    const account = getAccount();

    if (account && document.getElementById("balance")) {
        updateDashboard(account);
        loadTransactions();
    }
});
