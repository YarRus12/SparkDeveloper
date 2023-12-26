import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import java.time._
import java.sql.Timestamp

object HelloSpark {
  def main(args: Array[String]): Unit = {

    // Создаём SparkSession
    val spark = SparkSession.builder
      .appName("HelloSpark")
      .getOrCreate()

    import spark.implicits._

    println(s"spark.version == ${spark.version}")

    val df = List(
      (1L, 2.0, "string1", LocalDate.of(2000, 1, 1), Timestamp.valueOf("2000-01-01 12:00:00")),
      (2L, 3.0, "string2", LocalDate.of(2000, 2, 1), Timestamp.valueOf("2000-02-01 12:00:00")),
      (3L, 4.0, "string3", LocalDate.of(2000, 3, 1), Timestamp.valueOf("2000-03-01 12:00:00"))
    ).toDF("a", "b", "c", "d", "e")

    df.printSchema()
    df.show()

    df.withColumn("upper_c", upper($"c")).show()
    df.select(col("c")).show()
    df.filter($"a" === 1L).show()

    spark.stop()
  }
}
