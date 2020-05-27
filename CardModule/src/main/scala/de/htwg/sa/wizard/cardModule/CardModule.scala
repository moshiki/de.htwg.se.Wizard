package de.htwg.sa.wizard.cardModule

import com.google.inject.AbstractModule
import de.htwg.sa.wizard.cardModule.model.cardComponent.CardStackInterface
import de.htwg.sa.wizard.cardModule.model.fileIOComponent._
import net.codingwell.scalaguice.ScalaModule

class CardModule extends AbstractModule with ScalaModule{
  override def configure(): Unit = {
    bind[CardStackInterface].toInstance(CardStack)
    bind[FileIOInterface].to[FileIOJSON.FileIO]
  }
}
