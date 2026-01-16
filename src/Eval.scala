import ast.*
import enviroment.Env
import defs.* 

def eval(ex: Expr, env: Env, fs: FDs): Int = 
    ex match 
        case Num(num) => num
        case Add(left, right) => eval(left, env, fs) + eval(right, env, fs)
        case Sub(left, right) => eval(left, env, fs) - eval(right, env, fs)
        case Val(x, i, b) => eval(b, env + (x -> eval(i, env, fs)), fs)
        case Id(x) => lookup(x, env)
        case App(f, a) => lookupFD(f, fs) match
            case Fdef(fname, pname, body) => val aval = eval(a, env, fs)
                                             eval(body, Map() + (pname -> aval), fs)
         

def lookup(id: String, env: Env): Int = 
    env.getOrElse(id, throw new RuntimeException(s"free variable: $id"))

def lookupFD(name: String, fs: FDs) : Fdef =
    fs match {
        case Nil => throw new RuntimeException(s"unknown function $name")
        case head :: tail => if (head.f == name) head else lookupFD(name, tail)
    }