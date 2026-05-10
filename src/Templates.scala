package net.ivoah.cars

import scalatags.Text.all.*
import scalatags.Text.tags2.title

import java.time.format.{DateTimeFormatter, FormatStyle}

object Templates {
  def root(): String = doctype("html")(html(
    head(
      title("Cars")
    ),
    body(
      ul(
        for (vehicle <- Database.getVehicles()) yield {
          li(
            s"${vehicle.year} ${vehicle.make} ${vehicle.model}${vehicle.trim.map(" " + _).getOrElse("")}: ${vehicle.nickname}",
            br(),
            img(src:=s"/pictures/${vehicle.vin}")
          )
        }
      )
    )
  )).render
}
