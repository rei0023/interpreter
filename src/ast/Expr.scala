package ast

import values.*

trait Expr
case class Num(num: Int) extends Expr
case class Add(left: Expr, right: Expr) extends Expr
case class Sub(left: Expr, right: Expr) extends Expr
//case class Val(x: String, i: Expr, b: Expr) extends Expr
case class Id(x: String) extends Expr
case class Fun(x: String, b: Expr) extends Expr
case class App(f: Expr, a: Expr) extends Expr
case class if0(c : Expr, t: Expr, f: Expr) extends Expr
case class Rec(f : String, x : String, b: Expr, e: Expr) extends Expr