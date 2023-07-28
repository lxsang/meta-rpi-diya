DESCRIPTION = "leafpad for GTK3"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

S="${WORKDIR}"

DEPENDS = "gtk+3 pango cairo harfbuzz gdk-pixbuf  intltool-native"

inherit pkgconfig


SRC_URI = "file://l3afpad-0.8.18.1.11.tar.gz"
SRC_URI[sha256sum] = "005457fa35a7e37024e403852a21a5c7362a0314a8de9b7fff73b1e7802d8959"

do_configure () {
   ./configure --host=x86_64-unknown-linux --target=aarch64-poky-linux
}

do_compile () {
   make
}

do_install () {
   DESTDIR=${D} make install
}

FILES:${PN} += "/usr/local/*"