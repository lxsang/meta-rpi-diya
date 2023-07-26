DESCRIPTION = "wlopm"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"


DEPENDS = "wayland wayland-protocols"

SRCREV = "${AUTOREV}"

SRC_URI = "https://git.iohub.dev/dany/wlopm.git;branch=master;"
SRC_URI[sha256sum] = "0e5335c501ac1803932eca49bcb9344578d11911768605f31b29c02c16a142f1"

S="${WORKDIR}/git"

do_compile () {
    oe_runmake -C make/linux
}

do_install() {
     oe_runmake install DESTDIR='${D}'
}