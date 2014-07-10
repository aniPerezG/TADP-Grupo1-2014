package comoViajo

class Statistics[A](list: List[A]) {

  val from = list

  def select[B](transformation: A => B) = new Select[A](transformation, this)

}

trait Sentence[A] {
  
  def values: List[A]  
  
  def apply: List[_]
  
  def apply(list: List[A]): List[_]
  
  def groupBy(criterion: A => _): GroupBy[A]
  
  def reduce[B, _](reduction: List[_] => B) = new Reduce[B](reduction, apply)
    
} 

class Select[A](transformation: A=> _, statistics: Statistics[A]) extends Sentence[A] {
  
  def apply: List[_] = apply(values)
  
  def values: List[A] = statistics.from
  
  def apply(list: List[A]): List[_] = list.map(transformation)
  
  def where(criterion: A => Boolean) = new Where[A](criterion, this)
  
  def groupBy(transformation: A => _) = new GroupBy[A](transformation, this)
    
}

class Where[A] (criterion: A => Boolean, select: Select[A]) extends Sentence[A] {
  
  def apply: List[_] = select.apply(values)
  
  def apply(list: List[A]): List[_] = select.apply(applyConditions(list))
  
  def applyConditions(list: List[A]) = list.filter(criterion)
  
  def values: List[A] = applyConditions(select.values)
   
  def groupBy(transformation: A => _) = new GroupBy[A](transformation, this)
}

class GroupBy[A](transformation: A =>_, sentence: Sentence[A]) {
  
  def values = sentence.values.groupBy(transformation)

  def apply = values.map{case (key,results) => (key, sentence.apply(results))}

  def reduce[B](reduction: List[_] => B) = new ReduceMap[B](reduction, apply)

  
}

class ReduceMap[B](reduction: List[_] => B, results: Map[_,List[_]]) {
  
  def apply =  results.map{case (key, values) => (key, new Reduce(reduction, values).apply)}
  
}

class Reduce[B](reduction: List[_] => B, list: List[_]){
  
  def apply = reduction(list)
  
}