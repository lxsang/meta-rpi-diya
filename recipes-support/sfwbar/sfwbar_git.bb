DESCRIPTION = "sfwbar pannel for wayland"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS = "gtk+3 gtk-layer-shell json-c wayland-native"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/LBCrion/sfwbar.git;protocol=https;branch=main"
SRC_URI += " file://sfwbar.config "

S="${WORKDIR}/git"

inherit meson pkgconfig

EXTRA_OEMESON += "--buildtype release"

do_install:append () {
    install -d ${D}/${sysconfdir}/xdg/
    install -d ${D}/${sysconfdir}/xdg/sfwbar/
    install -m 0755 ${WORKDIR}/sfwbar.config ${D}/${sysconfdir}/xdg/sfwbar/
}

FILES:${PN} += "/usr/share/icons/*"
