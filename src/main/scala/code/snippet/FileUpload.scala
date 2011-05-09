package code 
package snippet 

import scala.xml.{NodeSeq, Text, Elem}
import net.liftweb.http.{FileParamHolder, SHtml}
import SHtml._
import net.liftweb.util._
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._

class FileUpload {
  def fileUploadForm(form:NodeSeq) : NodeSeq = {
    var fileHolder: Box[FileParamHolder] = Empty

    def handleFile() = {
      // Do something with the file.
      printBox(fileHolder)

      fileHolder.map { holder =>
        Alert("Oh, you upload file " + holder.fileName + ", yes?")
      } openOr {
        Alert("Well *that* upload failed...")
      }
    }

    val bindForm =
      "type=file" #> fileUpload((fph) => fileHolder = Full(fph)) &
      "type=submit" #> ajaxSubmit("Submit", handleFile _)

    ajaxForm(
      bindForm(form)
    )
  }

  def printRed(thing:Any) = {
    println("\u001b[0m\u001b[31m" + thing + "\u001b[0m")
  }
  def printGreen(thing:Any) = {
    println("\u001b[0m\u001b[32m" + thing + "\u001b[0m")
  }
  def printYellow(thing:Any) = {
    println("\u001b[0m\u001b[33m" + thing + "\u001b[0m")
  }
  def printBox[T](thing:Box[T]) = {
    import net.liftweb.common.{Full, Failure, Empty}
    thing match {
      case Full(_) =>
        printGreen(thing)
      case Failure(_, _, _) =>
        printYellow(thing)
      case Empty =>
        printRed(thing)
    }
  }
}
