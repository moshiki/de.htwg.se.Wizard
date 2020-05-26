package de.htwg.se.wizard

import com.google.inject.{Guice, Injector}
import de.htwg.sa.wizard.resultTable.ResultTable
import de.htwg.se.wizard.aview.gui.SwingGui
import de.htwg.se.wizard.aview.{HttpTui, TUI}
import de.htwg.se.wizard.controller.controllerComponent.controllerBaseImpl.Controller

import scala.io.StdIn.readLine

object Wizard {
  val injector: Injector = Guice.createInjector(new WizardModule)
  val controller: Controller = injector.getInstance(classOf[Controller])

  val tui = new TUI(controller)
  val gui = new SwingGui(controller)
  val httpTui = new HttpTui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    ResultTable.main(Array())
    var input: String = ""
    do {
      input = readLine()
      tui.processInput(input)
    } while (input != "q")
    httpTui.shutdownWebServer()
    ResultTable.shutdownServer()
  }
}
