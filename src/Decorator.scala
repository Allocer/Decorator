trait Reader {
  def input: String
}

class InputReader extends Reader {
  println("Give me a string: ")

  override def input = scala.io.StdIn.readLine()
}

abstract class ReaderDecorator(decoratedInput: Reader) extends Reader {
  override def input = decoratedInput.input
}

class WhitespaceDecorator(decoratedInput: Reader) extends ReaderDecorator(decoratedInput) {
  override def input = super.input.trim()
}

class UpperCaseDecorator(decoratedInput: Reader) extends ReaderDecorator(decoratedInput) {
  override def input = super.input.toUpperCase()
}

class FileReader extends Reader {
  val source = scala.io.Source.fromFile("file.txt")

  override def input = try source.mkString finally source.close()
}

object DecoratorSample {
  def main(args: Array[String]) = {
    var r: Reader = new InputReader
    println("Basic input reader value: ", r.input)
    r = new WhitespaceDecorator(r)
    println("Input reader without whitespaces: ", r.input)
    r = new UpperCaseDecorator(r)
    println("Input reader upper case: ", r.input)
  }
}