package net.ivoah.cars

import java.sql.ResultSet
import java.time.LocalDate

case class Vehicle(
  vin: String,
  year: Int,
  make: String,
  model: String,
  trim: Option[String],
  nickname: String,
  picture: Array[Byte],
  purchaseDate: LocalDate,
  purchasePrice: Double,
  purchaseMiles: Int,
  purchaseFrom: String,
  sellMiles: Option[Int],
  sellPrice: Option[Double],
  sellDate: Option[LocalDate],
  sellTo: Option[String]
)

object Vehicle {
  def fromResultSet(r: ResultSet): Vehicle = Vehicle(
    r.getString("vin"),
    r.getInt("year"),
    r.getString("make"),
    r.getString("model"),
    r.getStringOption("trim"),
    r.getString("nickname"),
    r.getBytes("picture"),
    r.getDate("purchase_date").toLocalDate,
    r.getDouble("purchase_price"),
    r.getInt("purchase_miles"),
    r.getString("purchase_from"),
    r.getIntOption("sell_miles"),
    r.getDoubleOption("sell_price"),
    r.getLocalDateOption("sell_date"),
    r.getStringOption("sell_to")
  )
}
