PACKAGECONFIG:remove = " man-pages xwayland "
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " file://init \
             file://autostart \
             file://environment \
             file://menu.xml \
             file://rc.xml \
             file://wpp.jpg \
            "
do_install:append () {
    install -d ${D}/${sysconfdir}/init.d
    install -d ${D}/${sysconfdir}/labwc
    install -m 0755 ${WORKDIR}/init ${D}/${sysconfdir}/init.d/labwc

    install -m 0755 ${WORKDIR}/autostart ${D}/${sysconfdir}/labwc/
    install -m 0755 ${WORKDIR}/environment ${D}/${sysconfdir}/labwc/
    install -m 0755 ${WORKDIR}/menu.xml ${D}/${sysconfdir}/labwc/
    install -m 0755 ${WORKDIR}/rc.xml ${D}/${sysconfdir}/labwc/
    install -m 0755 ${WORKDIR}/wpp.jpg ${D}/${sysconfdir}/labwc/
}

inherit update-rc.d
INITSCRIPT_NAME = "labwc"
INITSCRIPT_PARAMS = "start 9 5 2 . stop 20 0 1 6 ."