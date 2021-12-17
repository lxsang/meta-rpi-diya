DESCRIPTION = "Auto configuration deployment from media"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://confd \
            file://expandfs.sh \
            file://80_diya \
            file://fs_resize"


inherit update-rc.d useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PACKAGES = "${PN}"

USERADD_PARAM:${PN} = "-u 1000 -d /home/diya -r -s /bin/sh diya"

INITSCRIPT_NAME = "confd"
INITSCRIPT_PARAMS = "start 80 S ."

do_install() {
    install -d ${D}/${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/confd ${D}/${sysconfdir}/init.d/confd
    install -d ${D}/usr/bin/
    install -d ${D}/etc/default/volatiles
	install -m 0644 ${WORKDIR}/80_diya ${D}${sysconfdir}/default/volatiles
    install -m 0755 ${WORKDIR}/expandfs.sh ${D}/usr/bin/expandfs.sh
    install -m 0755 ${WORKDIR}/fs_resize ${DEPLOY_DIR_IMAGE}/
}

