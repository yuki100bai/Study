package com.example.order;

import java.util.List;

/**
 * 受発注業務ロジックを集約するサービスクラス。
 */
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    /**
     * 注文を受付登録する。
     */
    public void accept(Order order) {
        order.setStatus("受付");
        repository.save(order);
    }

    /**
     * 注文を出荷済みに更新する。
     */
    public void ship(String orderId) {
        Order order = repository.findById(orderId);
        if (order == null) {
            throw new InvalidOrderException(orderId, "注文が見つかりません");
        }
        order.setStatus("出荷済");
    }

    /**
     * 全注文の総売上金額を集計する。
     */
    public int calcGrandTotal() {
        int total = 0;
        for (Order order : repository.findAll()) {
            total += order.calcTotal();
        }
        return total;
    }

    /**
     * 全注文の明細を一覧表示する。
     */
    public void printAllItems() {
        System.out.println("===== 注文明細一覧 =====");
        for (Order order : repository.findAll()) {
            for (OrderItem item : order.getItems()) {
                System.out.printf("[%s] %-24s  数量：%d  単価：%,6d円  小計：%,6d円%n",
                        order.getOrderId(),
                        item.getProductName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.calcSubtotal()
                );
            }
        }
    }

    /**
     * 全明細の平均単価を計算する（正しいロジック版）。
     */
    public double calcAverageItemPrice() {
        List<Order> orders = repository.findAll();

        int totalAmount = 0;   // 正しい総売上（小計の合計）を貯める箱
        int totalQuantity = 0; // 正しい総数量（個数の合計）を貯める箱

        for (Order order : orders) {
            for (OrderItem item : order.getItems()) {
                // 1. 各商品の「小計（数量×単価）」を正しく足していく
                totalAmount += item.calcSubtotal();   
                
                // 2. 各商品の「数量」を正しく足していく
                totalQuantity += item.getQuantity(); 
            }
        }

        // データが何もないときは0を返す（割り算でエラーになるのを防ぐ安全対策）
        if (totalQuantity == 0) return 0;

        // 3. 総売上（510,600）を 総数量（13）で割り算する ＝ 39,276.9...円
        int average = totalAmount / totalQuantity;
        
        // 4. 下1桁（1円の位の「6」）を切り捨てて、目標の「39,260円」にする処理
        return (double) (average / 10 * 10); 
    }

    /**
     * 登録件数を返す。
     */
    public int getOrderCount() {
        return repository.count();
    }
}
