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

import java.util.Collections;
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
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import cc.colorcat.kingfisher.annotation.Api;

/**
 * Author: cxx
 * Date: 2018-09-29
 * GitHub: https://github.com/ccolorcat
 */
@AutoService(Processor.class)
public class ApiProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;
    private Elements utils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.filer = processingEnvironment.getFiler();
        this.messager = processingEnvironment.getMessager();
        this.utils = processingEnvironment.getElementUtils();
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
        Set<? extends Element> allApi = roundEnvironment.getElementsAnnotatedWith(Api.class);
        for (Element api : allApi) {
            Utils.assertInterface(api);
            ServiceFactory.Builder builder = new ServiceFactory.Builder()
                    .setPackageElement(utils.getPackageOf(api))
                    .setInterfaceElement((TypeElement) api);
            for (Element element : api.getEnclosedElements()) {
                if (element instanceof ExecutableElement) {
                    builder.addExecutableElement((ExecutableElement) element);
                    TypeMirror returnType = ((ExecutableElement) element).getReturnType();
                    messager.printMessage(Diagnostic.Kind.NOTE, returnType.toString());
                    messager.printMessage(Diagnostic.Kind.NOTE, returnType.getKind().name());
                    messager.printMessage(Diagnostic.Kind.NOTE, returnType.getKind().toString());
                }
            }
            builder.build().writeOut(filer);
        }
        return true;
    }
}
