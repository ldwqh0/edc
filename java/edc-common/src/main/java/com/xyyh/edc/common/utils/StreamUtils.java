package com.xyyh.edc.common.utils;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 对Stream的增强
 * 
 * @author LiDong
 *
 */
public interface StreamUtils {

	public static <T> void forEach(Stream<T> stream, BiConsumer<Integer, T> consumer) {
		final AtomicInteger acc = new AtomicInteger(0);
		stream.forEach(item -> consumer.accept(acc.getAndAdd(1), item));
	}

	public static <T, R> Stream<R> map(Stream<T> stream, BiFunction<Integer, T, R> f) {
		final AtomicInteger acc = new AtomicInteger(0);
		return stream.map(item -> f.apply(acc.getAndAdd(1), item));
	}

	public static <T> Stream<T> fromIterator(Iterator<T> iterator) {
		Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.NONNULL);
		return StreamSupport.stream(spliterator, false);
	}

	public static <T> Stream<T> fromIterable(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	/**
	 * 简单的对Stream进行reduce操作
	 * 
	 * @param <R>         操作的返回值类型
	 * @param <T>         操作数类型
	 * @param stream      要操作的流
	 * @param accumulator 结果累加器
	 * @param f           累加结果的方法
	 * @return
	 */
	public static <R, T> R reduce(Stream<T> stream, R accumulator, BiFunction<R, T, R> f) {
		AtomicReference<R> v = new AtomicReference<R>(accumulator);
		stream.forEach(item -> v.set(f.apply(v.get(), item)));
		return v.get();
	}
}
