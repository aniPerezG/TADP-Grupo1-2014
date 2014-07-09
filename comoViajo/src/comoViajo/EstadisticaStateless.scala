package comoViajo

class Statistics[A](list: List[A]) {

  val from = list

  def select[B](s: A => B) = new Select[A](s, this)

}

trait Sentence[A] {
  
  def values: List[A]  
  
  def apply: List[_]
  
  def apply(l: List[A]): List[_]
  
  def groupBy(t: A => _): GroupBy[A]
  
  def reduce[B, _](f: List[_] => B): B = f(apply)
    
} 

class Select[A](s: A=> _, e: Statistics[A]) extends Sentence[A] {
  
  def apply: List[_] = apply(values)
  
  def values: List[A] = e.from
  
  def apply(l: List[A]): List[_] = l.map(s)
  
  def where(c: A => Boolean) = new Where[A](c, this)
  
  def groupBy(t: A => _) = new GroupBy[A](t, this)
    
}

class Where[A] (c: A => Boolean, s: Select[A]) extends Sentence[A] {
  
  def apply: List[_] = s.apply(values)
  
  def apply(l: List[A]): List[_] = s.apply(applyConditions(l))
  
  def applyConditions(l: List[A]) = l.filter(c)
  
  def values: List[A] = applyConditions(s.values)
   
  def groupBy(t: A => _) = new GroupBy[A](t, this)
}

class GroupBy[A](t: A =>_, s: Sentence[A]) {
  
  def values = s.values.groupBy(t)

  def apply = values.map{case (k,v) => (k, s.apply(v))}

  def reduce[B](f: List[_] => B) = apply.map{case (k, v) => (k,f(v))}
  
}


	

