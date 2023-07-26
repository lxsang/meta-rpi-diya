DESCRIPTION = "sfwbar pannel for wayland"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"


DEPENDS = "wayland wayland-native wayland-protocols"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/LBCrion/sfwbar.git;protocol=https;branch=master"
SRC_URI += " file://sfwbar.config "

S="${WORKDIR}/git"

inherit meson pkgconfig features_check

EXTRA_OEMESON += "--buildtype release"

do_install:append () {
    install -d ${D}/${sysconfdir}/sfwbar/
    install -m 0755 ${WORKDIR}/sfwbar.config ${D}/${sysconfdir}/sfwbar/
}