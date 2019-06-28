package cc.colorcat.kingfisher.processor;

import javax.lang.model.element.Element;

import cc.colorcat.kingfisher.annotation.Client;

/**
 * Author: cxx
 * Date: 2019-05-08
 */
public class ClientProcessor implements AnnotationProcessor<Client> {
    @Override
    public void process(MethodModel.Builder builder, Element element, Client client) {
        builder.client(client.value());
    }
}
