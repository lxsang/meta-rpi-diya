FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://itf_diya"

do_install:append () {
    install -d ${D}/var/etc
    install -d ${D}/var/etc/network
    rm ${D}/etc/network/interfaces
    install -m 0644 ${WORKDIR}/itf_diya ${D}/var/etc/network/
}