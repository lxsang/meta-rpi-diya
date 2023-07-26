DESCRIPTION = "wlopm"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"


DEPENDS = "wayland  wayland-native wayland-protocols"

SRCREV = "${AUTOREV}"

SRC_URI = "git://git.iohub.dev/dany/wlopm.git;protocol=https;branch=master"

S="${WORKDIR}/git"

do_compile () {
    oe_runmake
}

do_install() {
     oe_runmake install DESTDIR='${D}'
}

FILES:${PN} += "/usr/local/*"