DESCRIPTION = "leafpad for GTK3"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"


DEPENDS = "gtk+3"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/stevenhoneyman/l3afpad.git;protocol=https;branch=master"9

S="${WORKDIR}/git"

inherit autotools pkgconfig
