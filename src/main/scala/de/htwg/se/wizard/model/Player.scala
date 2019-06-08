package de.htwg.se.wizard.model
import de.htwg.se.wizard.model.cards.{Card, DefaultCard, JesterCard, WizardCard}

import scala.collection.mutable.ListBuffer


case class Player(name: String, playerCards: Option[List[Card]] = None) {
  override def toString: String = name
}

object Player {
  def checkNumberOfPlayers(number: Int): Boolean = {
    if (number < 3 || number > 5) return false
    true
  }


  def playerTurn(player: Player, round: Int): String = {

    val cards = player.playerCards.get


    val firstString = "Round " + round + " - Player: " + player.name
    val secondString = "Select one of the following cards:"

    firstString + "\n" + secondString + "\n" + "{ " + cards.mkString(", ") + " }"
  }

  def playerPrediction(player: Player, round: Int, trump: Option[String]): String = {
    val firstString = "Round " + round + " - Player: " + player.name
    val secondString = "Trump Color: " + trump.getOrElse("None")
    val thirdString = "Your Cards: " + "{ " + player.playerCards.get.mkString(", ") + " }"
    val string = "Enter the amount of stitches you think you will get: "
    firstString + "\n" + secondString + "\n" + thirdString + "\n" + string

  }
}