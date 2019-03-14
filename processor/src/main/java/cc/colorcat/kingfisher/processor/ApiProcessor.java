/*
 * Copyright 2018 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.colorcat.kingfisher.processor;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import cc.colorcat.kingfisher.annotation.Api;
import cc.colorcat.kingfisher.annotation.DELETE;
import cc.colorcat.kingfisher.annotation.Down;
import cc.colorcat.kingfisher.annotation.DynamicUrl;
import cc.colorcat.kingfisher.annotation.GET;
import cc.colorcat.kingfisher.annotation.HEAD;
import cc.colorcat.kingfisher.annotation.Header;
import cc.colorcat.kingfisher.annotation.HeaderMap;
import cc.colorcat.kingfisher.annotation.OPTIONS;
import cc.colorcat.kingfisher.annotation.POST;
import cc.colorcat.kingfisher.annotation.PUT;
import cc.colorcat.kingfisher.annotation.Param;
import cc.colorcat.kingfisher.annotation.ParamMap;
import cc.colorcat.kingfisher.annotation.Path;
import cc.colorcat.kingfisher.annotation.TRACE;
import cc.colorcat.kingfisher.annotation.Up;
import cc.colorcat.kingfisher.annotation.Url;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
@AutoService(Processor.class)
public class ApiProcessor extends AbstractProcessor {
    private Map<Class<? extends Annotation>, AnnotationProcessor> processors = new HashMap<>();
    private Filer filer;
    private Elements utils;
    private Messager messager;

    {
        processors.put(GET.class, new GetProcessor());
        processors.put(HEAD.class, new HeadProcessor());
        processors.put(TRACE.class, new TraceProcessor());
        processors.put(OPTIONS.class, new OptionsProcessor());
        processors.put(POST.class, new PostProcessor());
        processors.put(PUT.class, new PutProcessor());
        processors.put(DELETE.class, new DeleteProcessor());
        processors.put(Param.class, new ParamProcessor());
        processors.put(ParamMap.class, new ParamMapProcessor());
        processors.put(Header.class, new HeaderProcessor());
        processors.put(HeaderMap.class, new HeaderMapProcessor());
        processors.put(Url.class, new UrlProcessor());
        processors.put(DynamicUrl.class, new DynamicUrlProcessor());
        processors.put(Path.class, new PathProcessor());
        processors.put(Down.class, new DownProcessor());
        processors.put(Up.class, new UpProcessor());
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.filer = processingEnvironment.getFiler();
        this.utils = processingEnvironment.getElementUtils();
        this.messager = processingEnvironment.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Api.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element api : roundEnvironment.getElementsAnnotatedWith(Api.class)) {
            ServiceFactory.Builder serviceBuilder = new ServiceFactory.Builder()
                    .setInterfaceElement(Utils.assertInterface(api))
                    .setPackageElement(utils.getPackageOf(api));

            for (Element e : api.getEnclosedElements()) {
                ExecutableElement ee = Utils.assertMethod(e);
                MethodModel.Builder methodBuilder = new MethodModel.Builder(ee);
                processAnnotation(methodBuilder, ee);

                for (VariableElement ve : ee.getParameters()) {
                    processAnnotation(methodBuilder, ve);
                }

                methodBuilder.returnType(Utils.getActualReturnTypeName(ee));
                serviceBuilder.addMethodModel(methodBuilder.build());
            }
            Utils.writeToJava(serviceBuilder.build(), filer);
        }
        return true;
    }

    private void processAnnotation(MethodModel.Builder builder, Element element) {
        for (Annotation annotation : Utils.getAllAnnotations(element)) {
            process(builder, element, annotation);
        }
    }

    @SuppressWarnings("unchecked")
    private void process(MethodModel.Builder builder, Element element, Annotation annotation) {
        AnnotationProcessor processor = this.processors.get(annotation.annotationType());
        if (processor != null) {
            processor.process(builder, element, annotation);
        } else {
            messager.printMessage(Diagnostic.Kind.WARNING, "no annotation processor for " + annotation, element);
        }
    }
}
