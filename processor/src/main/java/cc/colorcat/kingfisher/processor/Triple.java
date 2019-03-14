package cc.colorcat.kingfisher.processor;

/**
 * Author: cxx
 * Date: 2019-03-14
 * GitHub: https://github.com/ccolorcat
 */
final class Triple<F, S, T> {
    final F first;
    final S second;
    final T third;

    Triple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
