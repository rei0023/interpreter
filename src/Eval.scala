import ast.*
import enviroment.Env
import defs.* 
import values.*

def eval(ex: Expr, env: Env): Value = 
    ex match 
        case Num(n) => NumV(n)
        case Add(left, right) => 
            eval(left, env) match {
                case NumV(n) => eval(right, env) match {
                    case NumV(m) => NumV(n + m)
                    case _ => throw new RuntimeException("not an integer")
                }
                case _ => throw new RuntimeException("not an integer")
            }
        case Sub(left, right) => 
            eval(left, env) match {
                case NumV(n) => eval (right, env) match {
                    case NumV(m) => NumV(n - m)
                    case _ => throw new RuntimeException("not an integer")
                }
                case _ => throw new RuntimeException("not an integer")
            }
        case Id(x) => env(x)
        case Fun(x, b) => CloV(x, b, env)
        case App(f, a) => eval(f, env) match {
            case CloV(x, b, fenv) => eval(b, fenv + (x -> eval(a, env)))
            case v => throw new RuntimeException(s"not a closure: $v")
        }