import munit.* 
import ast.* 
import values.* 

class EvalSuite extends FunSuite:
    test("Function application"):
        val e = App(Fun("x", Add(Id("x"), Id("x"))), Num(2))
        assertEquals(eval(e, Map()), NumV(4))
    
    test("Function with two arguments"):
        val e = App(App(Fun("x", Fun("y", Add(Id("x"), Id("y")))), Num(10)), Num(20))
        assertEquals(eval(e, Map()), NumV(30))

    test("Recursive countdown function f(10) = 10"):
        val expr0 : Expr = App(Id("f"), Num(10))
        val bodyF : Expr = if0 (
            Id("x"),
            Num(0),
            Add(Num(1), App(Id("f"), Sub(Id("x"), Num(1))))
        )
        val e : Expr = Rec("f", "x", bodyF, expr0)

        assertEquals(eval(e, Map()), NumV(10))