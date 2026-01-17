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