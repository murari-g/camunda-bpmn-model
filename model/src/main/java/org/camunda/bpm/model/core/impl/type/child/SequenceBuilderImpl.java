/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.model.core.impl.type.child;

import org.camunda.bpm.model.core.impl.type.ModelElementTypeImpl;
import org.camunda.bpm.model.core.instance.ModelElementInstance;

/**
 * @author Daniel Meyer
 *
 */
public class SequenceBuilderImpl implements SequenceBuilder {

  protected final ModelElementTypeImpl modelType;

  public SequenceBuilderImpl(ModelElementTypeImpl modelType) {
    this.modelType = modelType;
  }

  public <T extends ModelElementInstance> ChildElementBuilder<T> element(Class<T> childElementType, String elementName) {
    return new ChildElementBuilderImpl<T>(childElementType, elementName, modelType);
  }

  public <T extends ModelElementInstance> ChildElementCollectionBuilder<T> elementCollection(Class<T> childElementType, String localName) {
    return elementCollection(childElementType, localName, modelType.getTypeNamespace());
  }

  public <T extends ModelElementInstance> ChildElementCollectionBuilder<T> elementCollection(Class<T> childElementType, String localName, String namespaceUri) {
    return new ChildElementCollectionBuilderImpl<T>(childElementType, localName, namespaceUri, modelType);
  }

  public <T extends ModelElementInstance> ChildElementCollectionBuilder<T> elementCollection(Class<T> childElementType) {
    return new ChildElementCollectionBuilderImpl<T>(childElementType, modelType);
  }

}