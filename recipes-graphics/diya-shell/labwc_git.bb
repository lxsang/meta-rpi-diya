DESCRIPTION = "lawc waylad compositor"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

#FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "wayland wayland-protocols fontconfig seatd liberation-fonts libxkbcommon libdrm eudev pixman mesa cairo"


SRCREV = "${AUTOREV}"
# SRCREV = "27d2d9f0c76b5805ff43578272e809aeb665a498"
SRC_URI = "git://github.com/labwc/labwc.git;protocol=https;branch=master;"

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS += " \
	libxml2 \
	glib-2.0 \
	cairo \
	pango \
	wayland \
	wayland-native \
	wayland-protocols \
	libdrm \
	libxkbcommon \
	libinput \
	wl-roots \
"

inherit meson

do_install:append () {
    # remove files not needed
    rm -rf ${D}/usr/local/include
    rm -rf ${D}/usr/local/lib
    rm -rf ${D}/usr/local/share
}