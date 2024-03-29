PACKAGECONFIG:remove = " man-pages xwayland "
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " file://autostart \ 
             file://init \
             file://environment \
             file://menu.xml \
             file://rc.xml \
             file://wpp.jpg \
            "
do_install:append () {
    install -d ${D}/${sysconfdir}/xdg
    install -d ${D}/${sysconfdir}/init.d
    install -d ${D}/${sysconfdir}/xdg/labwc
    install -m 0755 ${WORKDIR}/init ${D}/${sysconfdir}/init.d/labwc
    
    install -m 0755 ${WORKDIR}/wpp.jpg ${D}/${sysconfdir}/xdg/labwc/
    install -m 0755 ${WORKDIR}/autostart ${D}/${sysconfdir}/xdg/labwc/
    install -m 0755 ${WORKDIR}/environment ${D}/${sysconfdir}/xdg/labwc/
    install -m 0755 ${WORKDIR}/menu.xml ${D}/${sysconfdir}/xdg/labwc/
    install -m 0755 ${WORKDIR}/rc.xml ${D}/${sysconfdir}/xdg/labwc/
    
}

inherit update-rc.d
INITSCRIPT_NAME = "labwc"
INITSCRIPT_PARAMS = "start 9 5 2 . stop 20 0 1 6 ."
