DESCRIPTION = "Auto configuration deployment from media"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://confd \
            file://expandfs.sh \
            file://80_diya \
            file://fs_resize \
            file://boot-to-recovery"


inherit update-rc.d useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PACKAGES = "${PN}"

USERADD_PARAM:${PN} = "-u 1000 -d /home/diya -r -s /bin/sh diya"

INITSCRIPT_NAME = "confd"
INITSCRIPT_PARAMS = "start 30 S ."

do_install() {
    install -d ${D}/${sysconfdir}/init.d/
    install -d ${DEPLOY_DIR_IMAGE}
    install -m 0755 ${WORKDIR}/confd ${D}/${sysconfdir}/init.d/confd
    install -d ${D}/usr/sbin/
    install -d ${D}/etc/default/volatiles
	# install -m 0644 ${WORKDIR}/80_diya ${D}${sysconfdir}/default/volatiles
    install -m 0755 ${WORKDIR}/expandfs.sh ${D}/usr/sbin/expandfs.sh
    install -m 0755 ${WORKDIR}/boot-to-recovery ${D}/usr/sbin/boot-to-recovery
    install -m 0755 ${WORKDIR}/fs_resize ${DEPLOY_DIR_IMAGE}/

    cat << EOF >> ${DEPLOY_DIR_IMAGE}/extraconfig.txt
# initramfs recovery-${MACHINE}.cpio.gz followkernel
EOF

    install -d ${D}/var/etc
    install -d ${D}/var/etc/upper
    install -d ${D}/var/etc/work
}
