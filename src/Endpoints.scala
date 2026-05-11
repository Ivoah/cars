package net.ivoah.cars

import net.ivoah.vial.*

import java.nio.file.Paths

class Endpoints(debug: Boolean = false) {

  val router: Router = Router {
    case (_, _, _, e) if debug =>
      e.printStackTrace()
      Response.InternalServerError(e)

    case ("GET", "/", _) => Response(Templates.root())
    
    case ("GET", s"/pictures/$vin", _) =>
      Database.getVehicle(vin)
        .map(v => Response(Seq(v.picture)))
        .getOrElse(Response.NotFound())

    case ("GET", s"/vehicle/$vin", _) =>
      Database.getVehicle(vin)
        .map(v => Response(Templates.vehicle(v)))
        .getOrElse(Response.NotFound())

    case ("GET", s"/static/$file", _) =>
      Response.forFile(Paths.get("static"), Paths.get(file))
  }
}
