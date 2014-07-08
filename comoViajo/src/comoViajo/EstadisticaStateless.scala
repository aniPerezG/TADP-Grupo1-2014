package comoViajo

class Estadistic[A](list: List[A]) {

  val from = list

  def select[B](s: A => B) = new Select[A](s, this)

}

trait Sentence[A] {
  
  def values: List[A]  
  
  def apply: List[_]
  
  def apply(l: List[A]): List[_]
  
} 

class Select[A](s: A=> _, e: Estadistic[A]) extends Sentence[A] {
  
  def apply: List[_] = apply(values)
  
  def values: List[A] = e.from
  
  def apply(l: List[A]): List[_] = l.map(s)
  
  def where(c: A => Boolean) = new Where[A](c, this)
  
}

class Where[A] (c: A => Boolean, s: Select[A]) extends Sentence[A] {
  
  def apply: List[_] = s.apply(values)
  
  def apply(l: List[A]): List[A] = l.filter(c)
  
  def values: List[A] = apply(s.values)
   
}


	

