package cc.colorcat.kingfisher.processor;

import javax.lang.model.element.Element;

import cc.colorcat.kingfisher.annotation.DynamicUrl;

/**
 * Author: cxx
 * Date: 2019-03-14
 * GitHub: https://github.com/ccolorcat
 */
final class DynamicUrlProcessor implements AnnotationProcessor<DynamicUrl> {
    @Override
    public void process(MethodModel.Builder builder, Element element, DynamicUrl dynamicUrl) {
        Utils.assertString(element);
        builder.dynamicUrl(element.getSimpleName().toString());
    }
}
