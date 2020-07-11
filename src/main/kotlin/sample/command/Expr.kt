package sample.command

import sample.Utils

class Expr (val tokens: List<String>) {
    enum class Op(val symbol: String) {
        ADD("+"), SUB("-"),
        MUL("*"), DIV("/"), MOD("%")
    }

    abstract class Node () {
        abstract fun toPlain(): List<Any>
        abstract fun eval(): Int
    }

    class NumberNode (val n: Int) : Node() {
        override fun toPlain(): List<Any> = listOf(n)
        override fun eval(): Int = this.n
    }

    class BinopNode (
        val op: Op,
        val left: Node,
        val right: Node
    ) : Node() {
        override fun toPlain(): List<Any> {
            return listOf(
                op.symbol,
                left.toPlain(),
                right.toPlain()
            )
        }

        override fun eval(): Int {
            return (
                when (this.op) {
                    Op.ADD -> this.left.eval() + this.right.eval()
                    Op.SUB -> this.left.eval() - this.right.eval()
                    Op.MUL -> this.left.eval() * this.right.eval()
                    Op.DIV -> this.left.eval() / this.right.eval()
                    Op.MOD -> this.left.eval() % this.right.eval()
                }
            )
        }
    }

    // --------------------------------

    final val NUMERIC_CHARS = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    var cur = 0

    class ParseException(msg: String) : RuntimeException(msg)

    fun main(): Int {
        val tree = parse()
        Utils.p(
            tree.toPlain()
        )
        return tree.eval()
    }

    fun currentToken(): String = this.tokens.get(this.cur)

    fun isAdditive(): Boolean {
        if (this.tokens.size <= this.cur) {
            // end of tokens
            return false
        }
        return listOf("+", "-").contains(currentToken())
    }

    fun isMultiply(): Boolean {
        if (this.tokens.size <= this.cur) {
            // end of tokens
            return false
        }
        return listOf("*", "/", "%").contains(currentToken())
    }

    fun consume(token: String, exception: Boolean = false): Boolean {
        if (currentToken() == token) {
            this.cur += 1
            return true
        } else {
            if (exception) {
                throw  ParseException("expected <${token}> / got <${currentToken()}>")
            }
            return false
        }
    }

    fun debugkv(k: String, v: Any?){
        // Utils.putskv(k, v)
    }

    // --------------------------------

    fun parse(): Node = parseExpression()

    fun parseExpression(): Node = parseAdditive()

    fun parseAdditive(): Node {
        debugkv("--> parseAdditive", this.cur)

        var node = parseMultiply()

        while (isAdditive()) {
            val (op, multiply) = parseAdditiveTail()
            node = BinopNode(op, node, multiply)
        }

        return node
    }

    fun parseAdditiveTail(): Pair<Op, Node> {
        debugkv("--> parseAdditiveTail", this.cur)

        val op =
            when {
                consume("+") -> Op.ADD
                consume("-") -> Op.SUB
                else -> {
                    throw ParseException("expected '+' or '-' / got <${currentToken()}>")
                }
            }

        return Pair(op, parseMultiply())
    }

    fun parseMultiply(): Node {
        debugkv("--> parseMultiply", this.cur)

        var node = parseFactor()

        while (isMultiply()) {
            val (op, factor) = parseMultiplyTail()
            node = BinopNode(op, node, factor)
        }

        return node
    }

    fun parseMultiplyTail(): Pair<Op, Node> {
        debugkv("--> parseMultiplyTail", this.cur)

        val op =
            when {
                consume("*") -> Op.MUL
                consume("/") -> Op.DIV
                consume("%") -> Op.MOD
                else -> {
                    throw ParseException("expected '*', '/' or '%' / got <${currentToken()}>")
                }
            }

        return Pair(op, parseFactor())
    }

    fun parseFactor(): Node {
        debugkv("--> parseFactor", this.cur)

        if (consume("(")) {
            val exp = parseExpression()
            consume(")")
            return exp
        } else {
            return parseNumber()
        }
    }

    fun parseNumber(): NumberNode {
        debugkv("--> parseNumber", this.cur)

        val token = currentToken()
        this.cur += 1
        if (isNumber(token)) {
            return NumberNode(
                Integer.valueOf(token)
            )
        } else {
            throw ParseException("invalid number (${ Utils.inspect(token) })")
        }
    }

    fun isNumber(token: String): Boolean {
        val firstIndex =
            if (token.get(0) == '-') {
                1
            } else {
                0
            }

        for (i in firstIndex .. (token.length - 1)) {
            val c = token.get(i)
            if (! NUMERIC_CHARS.contains(c)) {
                return false
            }
        }

        return true
    }
}
