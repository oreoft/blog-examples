package cn.someget;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * 打乱一张扑克牌
 *
 * @author zyf
 * @date 2021-11-26 21:11
 */
public class ShuffleTest {

    /**
     * 花色的list
     */
    private final Set<String> colors = Sets.newHashSet("黑桃", "红心", "梅花", "方块");

    /**
     * 字面值的list. 使用双流合并, 因为很懒不愿意打2-10, 就用IntStream来生成了
     */
    private final Set<String> numbers = Stream.concat(IntStream.rangeClosed(2, 10).mapToObj(String::valueOf),
            Stream.of("A", "J", "K", "Q")).collect(Collectors.toSet());

    /**
     * 一副牌的总花色数
     */
    private final Integer colorCount = colors.size();

    /**
     * 一副牌的总字面值数
     */
    private final Integer numberCount = numbers.size();

    /**
     * 一副牌总牌数
     */
    private final Integer sumCount = colorCount * numberCount;


    @Test
    public void collectionShuffleTest() {
        // 进行组合
        List<String> result = colors.stream()
                .flatMap(color -> numbers.stream().map(number -> color + number))
                .collect(Collectors.toList());

        // 进行打乱
        Collections.shuffle(result);
        // 在console输出(为了方便看, 转成pretty的json)
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    @Test
    public void randomSwapTest() {
        // 进行组合
        List<String> result = colors.stream()
                .flatMap(color -> numbers.stream().map(number -> color + number))
                .collect(Collectors.toList());

        // 进行打乱54次
        for (int count = sumCount; count > 0; count--) {
            // 生成对换的随机数
            int site1 = RandomUtil.randomInt(0, sumCount);
            int site2 = RandomUtil.randomInt(0, sumCount);
            // 进行wap
            String temp = result.get(site1);
            result.set(site1, result.get(site2));
            result.set(site2, temp);
        }

        // 在console输出(为了方便看, 转成pretty的json)
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    @Test
    public void randomTransferTest() {
        // 先构建扑克牌
        List<String> rawList = colors.stream()
                .flatMap(color -> numbers.stream().map(number -> color + number))
                .collect(Collectors.toList());
        // 构建洗牌随机数和洗牌后结果
        List<String> result = new ArrayList<>(sumCount);
        Set<Integer> doneList = new HashSet<>(sumCount);
        // 进行洗牌
        while (doneList.size() < sumCount) {
            int index = RandomUtil.randomInt(0, sumCount);
            doneList.add(index);
            result.add(rawList.get(index));
        }

        // 在console输出(为了方便看, 转成pretty的json)
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }


    @Test
    @SuppressWarnings("all")
    public void parallelStreamTest() {
        // 先构建扑克牌
        List<String> rawList = colors.stream()
                .flatMap(color -> numbers.stream().map(number -> color + number))
                .collect(Collectors.toList());

        // 创建容器
        List<String> result = new ArrayList<>(sumCount);

        // 使用副作用进行并行添加到容器中
        rawList.parallelStream().forEach(result::add);

        // 在console输出(为了方便看, 转成pretty的json)
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
        System.out.println(result.size());
    }



    @Test
    @SneakyThrows
    @SuppressWarnings("all")
    public void multithreadingTest() {
        // 把花色先分好
        List<Queue<String>> rawList = colors.stream()
                .map(color -> numbers.stream().map(number -> color + number)
                        .collect(Collectors.toCollection(LinkedList::new)))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>(sumCount);
        // 偷懒使用jdk自带的线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(colorCount);

        for (int i = 0; i < colorCount; i++) {
            int current = i;
            ReentrantLock lock = new ReentrantLock();
            for (int count = 1; count <= numberCount; count++) {
                executor.execute(() -> {
                    lock.lock();
                    if (CollectionUtil.isNotEmpty(rawList.get(current))) {
                        result.add(rawList.get(current).poll());
                    }
                    lock.unlock();
                });
            }
            countDownLatch.countDown();
        }

        // 防止主线程提前结束
        countDownLatch.await(10, TimeUnit.SECONDS);

        // 在console输出(为了方便看, 转成pretty的json)
        System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
        System.out.println(result.size());
    }

}
