package md.grebla.immutable

class Table(val name: String,
            val columns: List[Column]) {

  if (columns.isEmpty) {
    throw new IllegalArgumentException("at least one column is required")
  }

  def this(name: String, column: Column) = this(name, List(column))

  def addColumn(column: Column) = new Table(name, column :: columns)

  def addColumns(columns: List[Column]) = {
    columns.foldLeft[Table](this)((t, c) => t.addColumn(c))
  }

  override def toString = {
    s"create table $name" + columns.reverse.mkString(" (", ", ", ");")
  }

}

object TableDemo extends App {

  try {
    new Table("TABLE_WITH_NO_COLUMNS", List())
  } catch {
    case iae: IllegalArgumentException => iae.printStackTrace()
  }

  val table = new Table("TRANSPORTUNIT", new Column("TU_REF", "number"))

  val newTable = table.addColumns(List(
    new Column("TU_ID", "varchar(255)"),
    new Column("TU_STATUS", "number")
  ))

  println(newTable)
  println(table)

}
