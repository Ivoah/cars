package net.ivoah.cars

object Database {
  given Connector = Connector(
    Config.database.connectionString,
    Config.database.user,
    Config.database.password
  )
  
  def getVehicles(): Seq[Vehicle] = {
    sql"""
      SELECT *
      FROM vehicle
      ORDER BY purchase_date
    """.query(Vehicle.fromResultSet)
  }

  def getVehicle(vin: String): Option[Vehicle] = {
    sql"""
      SELECT *
      FROM vehicle
      WHERE vin=$vin
    """.query(Vehicle.fromResultSet).headOption
  }
}
