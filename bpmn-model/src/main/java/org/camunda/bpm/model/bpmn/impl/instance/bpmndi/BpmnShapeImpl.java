/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.camunda.bpm.model.bpmn.impl.instance.bpmndi;

import org.camunda.bpm.model.bpmn.impl.instance.di.LabeledShapeImpl;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.bpmndi.ParticipantBandKind;
import org.camunda.bpm.model.bpmn.instance.di.LabeledShape;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.attribute.Attribute;
import org.camunda.bpm.model.xml.type.child.ChildElement;
import org.camunda.bpm.model.xml.type.child.SequenceBuilder;
import org.camunda.bpm.model.xml.type.reference.AttributeReference;

import static org.camunda.bpm.model.bpmn.impl.BpmnModelConstants.*;
import static org.camunda.bpm.model.xml.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;

/**
 * The BPMNDI BPMNShape element
 *
 * @author Sebastian Menski
 */
public class BpmnShapeImpl extends LabeledShapeImpl implements BpmnShape {

  private static AttributeReference<BaseElement> bpmnElementAttribute;
  private static Attribute<Boolean> isHorizontalAttribute;
  private static Attribute<Boolean> isExpandedAttribute;
  private static Attribute<Boolean> isMarkerVisibleAttribute;
  private static Attribute<Boolean> isMessageVisibleAttribute;
  private static Attribute<ParticipantBandKind> participantBandKindAttribute;
  private static AttributeReference<BpmnShape> choreographyActivityShapeAttribute;
  private static ChildElement<BpmnLabel> bpmnLabelChild;

  public static void registerType(ModelBuilder modelBuilder) {
    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(BpmnShape.class, BPMNDI_ELEMENT_BPMN_SHAPE)
      .namespaceUri(BPMNDI_NS)
      .extendsType(LabeledShape.class)
      .instanceProvider(new ModelTypeInstanceProvider<BpmnShape>() {
        public BpmnShape newInstance(ModelTypeInstanceContext instanceContext) {
          return new BpmnShapeImpl(instanceContext);
        }
      });

    bpmnElementAttribute = typeBuilder.stringAttribute(BPMNDI_ATTRIBUTE_BPMN_ELEMENT)
      .namespace(BPMNDI_NS)
      .qNameAttributeReference(BaseElement.class)
      .build();

    isHorizontalAttribute = typeBuilder.booleanAttribute(BPMNDI_ATTRIBUTE_IS_HORIZONTAL)
      .namespace(BPMNDI_NS)
      .build();

    isExpandedAttribute = typeBuilder.booleanAttribute(BPMNDI_ATTRIBUTE_IS_EXPANDED)
      .namespace(BPMNDI_NS)
      .build();

    isMarkerVisibleAttribute = typeBuilder.booleanAttribute(BPMNDI_ATTRIBUTE_IS_MARKER_VISIBLE)
      .namespace(BPMNDI_NS)
      .build();

    isMessageVisibleAttribute = typeBuilder.booleanAttribute(BPMNDI_ATTRIBUTE_IS_MESSAGE_VISIBLE)
      .namespace(BPMNDI_NS)
      .build();

    participantBandKindAttribute = typeBuilder.enumAttribute(BPMNDI_ATTRIBUTE_PARTICIPANT_BAND_KIND, ParticipantBandKind.class)
      .namespace(BPMNDI_NS)
      .build();

    choreographyActivityShapeAttribute = typeBuilder.stringAttribute(BPMNDI_ATTRIBUTE_CHOREOGRAPHY_ACTIVITY_SHAPE)
      .namespace(BPMNDI_NS)
      .qNameAttributeReference(BpmnShape.class)
      .build();

    SequenceBuilder sequenceBuilder = typeBuilder.sequence();

    bpmnLabelChild = sequenceBuilder.element(BpmnLabel.class)
      .build();

    typeBuilder.build();
  }

  public BpmnShapeImpl(ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public BaseElement getBpmnElement() {
    return bpmnElementAttribute.getReferenceTargetElement(this);
  }

  public void setBpmnElement(BaseElement bpmnElement) {
    bpmnElementAttribute.setReferenceTargetElement(this, bpmnElement);
  }

  public boolean isHorizontal() {
    return isHorizontalAttribute.getValue(this);
  }

  public void setHorizontal(boolean isHorizontal) {
    isHorizontalAttribute.setValue(this, isHorizontal);
  }

  public boolean isExpanded() {
    return isExpandedAttribute.getValue(this);
  }

  public void setExpanded(boolean isExpanded) {
    isExpandedAttribute.setValue(this, isExpanded);
  }

  public boolean isMarkerVisible() {
    return isMarkerVisibleAttribute.getValue(this);
  }

  public void setMarkerVisible(boolean isMarkerVisible) {
    isMarkerVisibleAttribute.setValue(this, isMarkerVisible);
  }

  public boolean isMessageVisible() {
    return isMessageVisibleAttribute.getValue(this);
  }

  public void setMessageVisible(boolean isMessageVisible) {
    isMessageVisibleAttribute.setValue(this, isMessageVisible);
  }

  public ParticipantBandKind getParticipantBandKind() {
    return participantBandKindAttribute.getValue(this);
  }

  public void setParticipantBandKind(ParticipantBandKind participantBandKind) {
    participantBandKindAttribute.setValue(this, participantBandKind);
  }

  public BpmnShape getChoreographyActivityShape() {
    return choreographyActivityShapeAttribute.getReferenceTargetElement(this);
  }

  public void setChoreographyActivityShape(BpmnShape choreographyActivityShape) {
    choreographyActivityShapeAttribute.setReferenceTargetElement(this, choreographyActivityShape);
  }

  public BpmnLabel getBpmnLabel() {
    return bpmnLabelChild.getChild(this);
  }

  public void setBpmnLabel(BpmnLabel bpmnLabel) {
    bpmnLabelChild.setChild(this, bpmnLabel);
  }
}
