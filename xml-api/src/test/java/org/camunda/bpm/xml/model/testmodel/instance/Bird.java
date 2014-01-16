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
package org.camunda.bpm.xml.model.testmodel.instance;

import org.camunda.bpm.xml.model.ModelBuilder;
import org.camunda.bpm.xml.model.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.xml.model.type.ModelElementTypeBuilder;
import org.camunda.bpm.xml.model.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;

import static org.camunda.bpm.xml.model.testmodel.TestModelConstants.ELEMENT_NAME_BIRD;
import static org.camunda.bpm.xml.model.testmodel.TestModelConstants.MODEL_NAMESPACE;

/**
 * @author Daniel Meyer
 *
 */
public class Bird extends FlyingAnimal {

  public static void registerType(ModelBuilder modelBuilder) {

    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(Bird.class, ELEMENT_NAME_BIRD)
      .namespaceUri(MODEL_NAMESPACE)
      .extendsType(FlyingAnimal.class)
      .instanceProvider(new ModelTypeInstanceProvider<Bird>() {
        public Bird newInstance(ModelTypeInstanceContext instanceContext) {
          return new Bird(instanceContext);
        }
      });

    typeBuilder.build();

  }

  public Bird(ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

}
