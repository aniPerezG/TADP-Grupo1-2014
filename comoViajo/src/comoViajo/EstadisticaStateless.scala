package comoViajo

class Estadistic[A](list: List[A]) {

  val from = list

  def select[B](s: A => B) = new Select[A](s, this)

}

trait Sentence[A] {
  
  def values: List[A]  
  
  def apply: List[_]
  
  def apply(l: List[A]): List[_]
  
  def groupBy(t: A => _): GroupBy[A]
  
} 

class Select[A](s: A=> _, e: Estadistic[A]) extends Sentence[A] {
  
  def apply: List[_] = apply(values)
  
  def values: List[A] = e.from
  
  def apply(l: List[A]): List[_] = l.map(s)
  
  def where(c: A => Boolean) = new Where[A](c, this)
  
  def groupBy(t: A => _) = new GroupBy[A](t, this)
  
}

class Where[A] (c: A => Boolean, s: Select[A]) extends Sentence[A] {
  
  def apply: List[_] = s.apply(values)
  
  def apply(l: List[A]): List[_] = s.apply(l.filter(c))
  
  def values: List[A] = s.values.filter(c)
   
  def groupBy(t: A => _) = new GroupBy[A](t, this)
}

class GroupBy[A](t: A =>_, s: Sentence[A]) {
  
  def values = s.values.groupBy(t)

  def apply = values.map{case (k,v) => (k, s.apply(v))}
  
}


	

