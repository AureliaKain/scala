package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == 0 || c == r) {
      1
    }
    else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

    def charChecker(chars: List[Char], count: Int): Boolean = {
      if (chars.isEmpty) count == 0
      else if (chars.head == '(') charChecker(chars.tail, count + 1)
      else if (chars.head == ')') charChecker(chars.tail, count - 1)
      else charChecker(chars.tail, count)
    }
    if (chars.isEmpty) true
    else if (chars.head == chars.last) false
    else charChecker(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def checkChange(money: Int, coin: List[Int]): Int = {
    if (coin.isEmpty || money < 0) 0
    else if (money == 0) 1
    else checkChange(money, coin.tail) + checkChange(money - coin.head, coin)
    }
    checkChange(money, coins)
  }
}
