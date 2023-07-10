FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://interfaces"

do_install:append () {
    rm ${D}/etc/network/interfaces
    install -m 0644 ${WORKDIR}/interfaces-custom ${D}/var/etc/network/interfaces
}