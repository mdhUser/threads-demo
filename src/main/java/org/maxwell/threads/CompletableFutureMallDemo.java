package org.maxwell.threads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/9 22:51
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("tianmao"),
            new NetMall("pdd")
    );

    /**
     * serial execute
     *
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream().map(netMall -> String.format(productName +
                "in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName).doubleValue())).collect(Collectors.toList());
    }

    /**
     * CompletableFuture并发执行
     *
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName +
                        "in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName).doubleValue())))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<String> l1 = getPrice(list, "mysql");
        l1.forEach(System.out::println);
        System.out.println("~~~costTime=" + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        List<String> l2 = getPriceByCompletableFuture(list, "mysql");
        l2.forEach(System.out::println);
        System.out.println("~~~costTime=" + (System.currentTimeMillis() - start1));
    }

}

@AllArgsConstructor
class NetMall {
    @Getter
    private String netMallName;

    public void setNetMallName(String netMallName) {
        this.netMallName = netMallName;
    }

    public BigDecimal calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0));
    }
}