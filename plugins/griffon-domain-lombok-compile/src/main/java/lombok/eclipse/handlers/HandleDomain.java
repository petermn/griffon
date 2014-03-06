/*
 * Copyright 2008-2014 the original author or authors.
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
package lombok.eclipse.handlers;

import griffon.transform.Domain;
import lombok.core.AnnotationValues;
import lombok.core.handlers.DomainHandler;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.eclipse.handlers.ast.EclipseType;
import org.codehaus.griffon.compile.domain.DomainConstants;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.kordamp.jipsy.ServiceProviderFor;

import static lombok.core.util.ErrorMessages.canBeUsedOnClassAndEnumOnly;

/**
 * @author Andres Almiray
 */
@ServiceProviderFor(EclipseAnnotationHandler.class)
public class HandleDomain extends EclipseAnnotationHandler<Domain> {
    private final EclipseDomainHandler handler = new EclipseDomainHandler();

    @Override
    public void handle(AnnotationValues<Domain> annotation, Annotation source, EclipseNode annotationNode) {
        EclipseType type = EclipseType.typeOf(annotationNode, source);
        if (type.isAnnotation() || type.isInterface()) {
            annotationNode.addError(canBeUsedOnClassAndEnumOnly(Domain.class));
            return;
        }

        EclipseUtil.addInterface(type.get(), DomainConstants.GRIFFON_DOMAIN_TYPE, source);
        handler.addDomainSupport(type);
        type.editor().rebuild();
    }

    private static class EclipseDomainHandler extends DomainHandler<EclipseType> {
    }
}
