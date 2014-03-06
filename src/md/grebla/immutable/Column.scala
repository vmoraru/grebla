package md.grebla.immutable

class Column(val name: String,
             val datatype: String) {

  override def toString = s"$name $datatype"

}
