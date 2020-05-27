package de.htwg.sa.wizard.cardModule.controller.controllerComponent.controllerBaseImplementation

import com.google.inject.Inject
import de.htwg.sa.wizard.cardModule.controller.controllerComponent.CardControllerInterface
import de.htwg.sa.wizard.cardModule.model.cardComponent.cardBaseImplementation.DefaultCard
import de.htwg.sa.wizard.cardModule.model.cardComponent.{CardInterface, CardStackInterface}
import de.htwg.sa.wizard.cardModule.model.fileIOComponent.FileIOInterface

import scala.util.{Failure, Success}

case class CardController @Inject()(var cardStackInterface: CardStackInterface, fileIOInterface: FileIOInterface) extends CardControllerInterface {
  override def shuffleCardStack(): List[CardInterface] = {
    cardStackInterface = cardStackInterface.shuffleCards()
    cardStackInterface.cards
  }

  def trumpColor: Option[String] = {
    val topCard = cardStackInterface.cards.head
    topCard match {
      case card: DefaultCard => Some(card.color)
      case _ => None
    }
  }

  def cardsForPlayer(playerNumber: Int, currentRound: Int): List[CardInterface] = {
    cardStackInterface.cards.slice(playerNumber * currentRound, playerNumber * currentRound + currentRound)
  }

  override def save(): Unit = {
    //fileIOInterface.save(cardStackInterface, "CardModule")
  }

  override def load(): Unit = {
   /* cardStack = fileIOInterface.load(cardStackInterface, "CardModule") match {
      case Failure(_) => return
      case Success(cardStackInterface) => cardStackInterface
    }*/
  }
}
