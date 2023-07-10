FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " file://itf_diya "

do_install:append () {
    rm ${D}/etc/network/interfaces
    install -d ${D}/test/
    install -m 0644 ${WORKDIR}/itf_diya ${D}/test/interfaces
    install -m 0644 ${WORKDIR}/itf_diya ${D}/etc/network/interfaces
}