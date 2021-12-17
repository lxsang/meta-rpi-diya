DESCRIPTION = "Wifi dongle firmware"
DEPENDS = ""
LICENSE="CLOSED"
LIC_FILES_CHKSUM=""


SRC_URI += "file://firmware"

FILES:${PN} += "/lib/firmware/*"
#FILES_${PN}-dev += "${libdir}/firmware/*"

do_install () {
    install -d ${D}/lib/firmware/
    cp -rf ${WORKDIR}/firmware/* ${D}/lib/firmware/
}
