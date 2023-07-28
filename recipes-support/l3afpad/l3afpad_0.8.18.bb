DESCRIPTION = "leafpad for GTK3"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

S="${WORKDIR}/l3afpad-0.8.18.1.11"

DEPENDS = "gtk+3"


SRC_URI = "file:/l3afpad-0.8.18.1.11.tar.gz"
SRC_URI[sha256sum] = "7fc154f12733b00d31767b3f1255c3f12249065406e7cddafd9888fc83720fd9"


inherit autotools pkgconfig
