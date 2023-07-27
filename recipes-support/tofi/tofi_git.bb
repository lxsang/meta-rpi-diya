DESCRIPTION = "tofi menu for wayland"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS = "wayland freetype wayland-native wayland-protocols harfbuzz pango libxkbcommon"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/philj56/tofi.git;protocol=https;branch=master"
SRC_URI += " file://icon.svg file://config "

S="${WORKDIR}/git"

inherit meson pkgconfig

EXTRA_OEMESON += "--buildtype release"

do_install:append () {
    install -d ${D}/usr/share
    install -d ${D}/usr/share/icons
    install -d ${D}/usr/share/icons/hicolor
    install -d ${D}/usr/share/icons/hicolor/scalable
    install -d ${D}/usr/share/icons/hicolor/scalable/apps
    install -m 0755 ${WORKDIR}/icon.svg ${D}/usr/share/icons/hicolor/scalable/apps/tofi.svg

    # replace the default config
    install -m 0755 ${WORKDIR}/config ${D}/${sysconfdir}/xdg/tofi/
}

FILES:${PN} += "/usr/share/*"