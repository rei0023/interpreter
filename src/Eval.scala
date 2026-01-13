def eval(ex: Expr, env: Env): Int = 
    ex match 
        case Num(num) => num
        case Add(left, right) => eval(left, env) + eval(right, env)
        case Sub(left, right) => eval(left, env) - eval(right, env)
        case Val(x, i, b) => eval(b, env + (x -> eval(i, env)))
        case Id(x) => lookup(x, env)

def lookup(id: String, env: Env): Int = 
    env.getOrElse(id, throw new RuntimeException(s"free variable: $id"))