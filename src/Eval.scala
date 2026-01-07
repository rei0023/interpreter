def eval(ex: Expr): Int = 
    ex match 
        case Num(num) => num
        case Add(left, right) => eval(left) + eval(right)
        case Sub(left, right) => eval(left) - eval(right)
    