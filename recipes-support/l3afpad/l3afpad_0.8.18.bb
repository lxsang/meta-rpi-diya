DESCRIPTION = "leafpad for GTK3"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS = "gtk+3"


SRC_URI = "file://v0.8.18.1.11.tar.gz"
SRC_URI[sha256sum] = "01e4ec90dd2688f033554b440a3c8e95efe64209fc03f9b4729f32ecb08bd66c"


inherit autotools pkgconfig
