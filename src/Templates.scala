package net.ivoah.cars

import scalatags.Text.all.*
import scalatags.Text.tags2.title

import java.time.format.{DateTimeFormatter, FormatStyle}

object Templates {
  def page(_title: String)(_body: Frag*): String = doctype("html")(html(
    head(
      link(rel:="icon", `type`:="image/png", href:="/static/favicon.png"),
      link(rel:="stylesheet", href:="/static/style.css"),
      title(_title)
    ),
    body(_body)
  )).render

  def root(): String = page("Vehicles")(
    h1("Noah's vehicles"),
    table(
      thead(tr(th("Nickname"), th("Year"), th("Make"), th("Model"), th("Trim"), th("Picture"))),
      for (v <- Database.getVehicles()) yield {
        tr(
          td(a(href:=s"/vehicle/${v.vin}", v.nickname)),
          td(v.year),
          td(v.make),
          td(v.model),
          td(v.trim),
          td(padding:="0", img(width:="100px", src:=s"/pictures/${v.vin}"))
        )
      }
    )
  )

  def vehicle(v: Vehicle): String = page(s"Cars - ${v.nickname}")(
    h1(s"${v.nickname}: ${v.fullName}"),
    img(src:=s"/pictures/${v.vin}")
  )
}
