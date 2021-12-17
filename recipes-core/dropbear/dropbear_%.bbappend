FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://default"

do_install:append () {
    rm -rf ${D}/etc/dropbear
    install -m 0644 ${WORKDIR}/default ${D}${sysconfdir}/default/dropbear
}