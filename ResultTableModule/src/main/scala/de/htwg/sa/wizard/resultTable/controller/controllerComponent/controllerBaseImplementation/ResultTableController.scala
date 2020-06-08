package de.htwg.sa.wizard.resultTable.controller.controllerComponent.controllerBaseImplementation

import com.google.inject.Inject
import de.htwg.sa.wizard.resultTable.controller.controllerComponent.ResultTableControllerInterface
import de.htwg.sa.wizard.resultTable.model.fileIOComponent.FileIOInterface
import de.htwg.sa.wizard.resultTable.model.resultTableComponent.ResultTableInterface

import scala.util.{Failure, Success}

case class ResultTableController @Inject()(var resultTableInterface: ResultTableInterface, fileIOInterface: FileIOInterface) extends ResultTableControllerInterface {
  override def updatePoints(round: Int, points: Vector[Int]): Unit = for (playerWhosePointsGetUpdated <- points.indices) {
    resultTableInterface = resultTableInterface.updatePoints(round, playerWhosePointsGetUpdated, points(playerWhosePointsGetUpdated))
  }

  override def initializeTable(numberOfRounds: Int, numberOfPlayers: Int): Unit = {
    resultTableInterface = resultTableInterface.initializeTable(numberOfRounds, numberOfPlayers)
  }

  override def save(): Unit = {
    fileIOInterface.save(resultTableInterface, "ResultTableModule")
  }

  override def load(): Unit = {
    resultTableInterface = fileIOInterface.load(resultTableInterface, "ResultTableModule") match {
      case Failure(_) => return
      case Success(resultTable) => resultTable
    }
  }

  override def pointArrayForView: Array[Array[Int]] = resultTableInterface.toArray

  override def tableAsString: String = resultTableInterface.toString

  override def playerList: List[String] = resultTableInterface.playerNames
}
