DESCRIPTION = "virtual key event for wayland"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


DEPENDS = "wayland wayland-native wayland-protocols-native wayland-protocols libxkbcommon"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/atx/wtype.git;protocol=https;branch=master"

S="${WORKDIR}/git"

inherit meson pkgconfig

EXTRA_OEMESON += "--buildtype release"