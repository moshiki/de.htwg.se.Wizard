package de.htwg.se.wizard.model.modelComponent.cards

import de.htwg.se.wizard.model.PlayerInterface

case class DefaultCard(color: String, number: Int, owner: Option[PlayerInterface] = None)
  extends Card(owner) with Ordered[DefaultCard] {
  require(number >= 1 && number <= 13)
  require(color == "blue" || color == "red" || color == "yellow" || color == "green")

  def hasColor: Boolean = true

  def isWizard: Boolean = false

  def isJester: Boolean = false

  override def getStringRep: String = color + " " + number

  override def compare(that: DefaultCard): Int = this.number - that.number

  override def equals(obj: Any): Boolean = {
    obj match {
      case defaultCard: DefaultCard => compare(defaultCard) == 0
      case _ => false
    }
  }

  override def toXML: String = {
    var ownerXML = "None"
    if (owner.isDefined) ownerXML = owner.get.toXML
    <DefaultCard>
      <color>{color}</color>
      <number>{number}</number>
      <owner>{ownerXML}</owner>
    </DefaultCard>
  }
}
