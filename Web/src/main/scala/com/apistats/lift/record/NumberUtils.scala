package com.apistats.lift.record
import java.text.DecimalFormat

trait NumberUtils {

  def formatPercentage(perc:Double):Double = {
    if (perc > 0.000){
      val df = new DecimalFormat("#0.000");
      val percentage = df.format(perc).toDouble;
      if (percentage < 100.00){
        percentage
      }else{
        100.000
      }
    }else {
      0.000
    }
  };
  
  def formatDouble(d:Double):Double = {
    val df = new DecimalFormat("#0.000");
    df.format(d).toDouble;
  };
  
  def max (a:Long, b:Long):Long = { if (a>=b) return a ; return b};
  
  def min (a:Long, b:Long):Long = { if (a<=b) return a ; return b}
}

  
