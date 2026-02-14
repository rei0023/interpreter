package values

import ast.*
import enviroment.*

trait Value
case class NumV(n: Int) extends Value
case class CloV(p: String, b: Expr, var e: Env) extends Value  // Now, captured enviroment is mutable!
 