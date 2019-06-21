package de.htwg.se.wizard

import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.aview.gui.SwingGui
import de.htwg.se.wizard.controller.maincontroller.{Controller, RoundManager}
import de.htwg.se.wizard.model.modelComponent.cards.StaticCard
import de.htwg.se.wizard.model.modelComponent.{ResultTableBuilder, StaticPlayer}

import scala.io.StdIn.readLine

object Wizard {
  val roundManager = RoundManager(resultTable = ResultTableBuilder().initializeTable(), staticPlayerInterface = StaticPlayer(), staticCardInterface = StaticCard())
  val controller = new Controller(roundManager, StaticPlayer(), StaticCard(), ResultTableBuilder())
  val tui = new TUI(controller)
  val gui = new SwingGui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.processInput(input)
    } while (input != "q")
  }
}
