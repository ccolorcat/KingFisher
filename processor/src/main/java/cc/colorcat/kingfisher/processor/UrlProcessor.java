package cc.colorcat.kingfisher.processor;

import javax.lang.model.element.Element;

import cc.colorcat.kingfisher.annotation.Url;

/**
 * Author: cxx
 * Date: 2019-03-14
 * GitHub: https://github.com/ccolorcat
 */
final class UrlProcessor implements AnnotationProcessor<Url> {
    @Override
    public void process(MethodModel.Builder builder, Element element, Url url) {
        Utils.assertString(element);
        builder.url(element.getSimpleName().toString());
    }
}
