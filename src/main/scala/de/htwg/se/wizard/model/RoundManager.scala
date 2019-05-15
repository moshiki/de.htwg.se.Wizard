package de.htwg.se.wizard.model

import de.htwg.se.wizard.model.cards.Card

class RoundManager {
  val initialCardStack: List[Card] = CardStack.initialize
  var needsSetup: Boolean = true
  var numberOfPlayers: Int = 0
  var players: List[Player] = Nil
  var currentPlayer: Int = 0
  var currentRound: Int = 0
  var gameOver: Boolean = false

  def setNumberOfPlayers(number: Int): Unit = {
    numberOfPlayers = Player.getNumberOfPlayers(number)
  }

  def addPlayer(input: String): Unit = {
        updatePlayers(input)
        if (players.size == numberOfPlayers) needsSetup = false
  }

  def evaluate(selectedCard: Int): Unit = {
    // Put method that moves cards onto new stack here
    currentRound = selectedCard // REMOVE THIS LATER!
  }

  def updatePlayers(input: String): Unit = {
    players = players ::: List(Player(input))
  }

  def getSetupStrings: String = {
    currentPlayer = nextPlayerSetup
    "Player " + currentPlayer + ", please enter your name:"
  }

  def nextPlayerSetup: Int = {
    if (currentPlayer < numberOfPlayers) currentPlayer + 1
    else 0
  }

  def getPlayerStateStrings: String = {
    currentPlayer = nextPlayer
    if (currentRound == roundsForThisGame && currentPlayer == 0) gameOver = true
    if (gameOver) return
    if (currentPlayer == 0 && currentRound != roundsForThisGame) currentRound = currentRound + 1
    Player.playerTurn(players(currentPlayer), currentRound, initialCardStack)
  }

  def roundsForThisGame: Int = {
    numberOfPlayers match {
      case 0 => 0
      case 3 => 20
      case 4 => 15
      case 5 => 12
      case _ => throw new IllegalArgumentException
    }
  }

  def nextPlayer: Int = {
    if (currentPlayer < numberOfPlayers - 1) currentPlayer + 1
    else 0
  }
}