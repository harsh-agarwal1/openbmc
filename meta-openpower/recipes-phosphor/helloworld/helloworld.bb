LICENSE = "CLOSED"

DEPENDS += "phosphor-logging"

inherit meson pkgconfig systemd
S = "${WORKDIR}/home/harsh/test0/exp0"
SRC_URI = "file:///home/harsh/test0/exp0"

FILES:${PN}  += "${systemd_system_unitdir}/hworld.service"
