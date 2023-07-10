FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://interfaces"

do_install:append () {
    rm ${D}/etc/network/interfaces
    install -d ${D}/var/etc/
    install -d ${D}/var/etc/network
    install -m 0644 ${WORKDIR}/interfaces ${D}/var/etc/network
}