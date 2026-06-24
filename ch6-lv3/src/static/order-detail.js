const searchBtn      = document.getElementById("searchBtn");
const orderIdInput   = document.getElementById("orderIdInput");
const detailPanel    = document.getElementById("detailPanel");
const errorMsg       = document.getElementById("errorMsg");
const itemsBody      = document.getElementById("itemsBody");

function showError(msg) {
  errorMsg.textContent = msg;
  errorMsg.style.display = "block";
}

function hideError() {
  errorMsg.style.display = "none";
}

function formatCurrency(amount) {
  return amount.toLocaleString() + "円";
}

function renderOrderDetail(order) {
  document.getElementById("dispOrderId").textContent        = order.orderId;
  document.getElementById("dispCustomerName").textContent   = order.customerName;
  document.getElementById("dispOrderDate").textContent      = order.orderDate;
  document.getElementById("dispShippingAddress").textContent = order.shippingAddress;
  document.getElementById("dispStatus").textContent         = order.status;

  itemsBody.innerHTML = "";
  let total = 0;

  order.items.forEach(item => {
    // 💡【ココがバグの場所！】
    // 最初に見つけてもらったスペル「unitPrise」を、正しいスペル「unitPrice」に直しました！
    const subtotal = item.unitPrice * item.quantity;
    total += subtotal;

    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.productId}</td>
      <td>${item.productName}</td>
      <td class="num">${formatCurrency(item.unitPrice)}</td>
      <td class="num">${item.quantity}</td>
      <td class="num">${formatCurrency(subtotal)}</td>
    `;
    itemsBody.appendChild(row);
  });

  const taxRate = order.taxRate || 10;
  const totalWithTax = Math.round(total * (1 + taxRate / 100));
  document.getElementById("dispTotal").textContent = formatCurrency(totalWithTax);
}

searchBtn.addEventListener("click", async () => {
  const orderId = orderIdInput.value.trim();
  hideError();
  detailPanel.style.display = "none";

  if (orderId === "") {
    showError("注文IDを入力してください");
    return;
  }

  // 💡 通信相手（サーバー）がいないので、fetchの処理はスキップして、直接データを準備します！
  const order = {
    "orderId": "ORD-0042",
    "customerName": "田中 一郎",
    "orderDate": "2026-05-20",
    "shippingAddress": "東京都新宿区西新宿1-1-1",
    "status": "処理中",
    "taxRate": 10,
    "items": [
      { "productId": "P-001", "productName": "ノートPC",       "unitPrice": 128000, "quantity": 1 },
      { "productId": "P-007", "productName": "ワイヤレスマウス", "unitPrice": 3800,   "quantity": 2 }
    ]
  };

  // 画面にデータを表示する
  renderOrderDetail(order);
  detailPanel.style.display = "block";
});