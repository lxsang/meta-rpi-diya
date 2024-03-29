SUMMARY = "Modular initramfs system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
RDEPENDS:${PN} += "${VIRTUAL-RUNTIME_base-utils} sysvinit"
RRECOMMENDS:${PN} = "${VIRTUAL-RUNTIME_base-utils-syslog}"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PR = "r4"

inherit allarch  update-rc.d

SRC_URI = "file://init \
           file://confd \
           file://diya-update \
          "

S = "${WORKDIR}"

INITSCRIPT_NAME = "confd"
INITSCRIPT_PARAMS = "start 30 S ."

do_install() {
    install -d ${D}/etc/init.d
    install -d ${D}/sbin
    # base
    install -m 0755 ${WORKDIR}/init ${D}/init
    install -m 0755 ${WORKDIR}/confd ${D}/etc/init.d/confd
cat << EOF >> ${D}/etc/profile
export MACHINE=${MACHINE}
EOF
    install -m 0755 ${WORKDIR}/diya-update ${D}/sbin/
    
    # create symlink
    ln -sf /sbin/diya-update ${D}/sbin/diya-update-rootfs
    ln -sf /sbin/diya-update ${D}/sbin/diya-update-kernel
    ln -sf /sbin/diya-update ${D}/sbin/diya-update-initramfs
    ln -sf /sbin/diya-update ${D}/sbin/diya-update-all
    
    # Create device nodes expected by some kernels in initramfs
    # before even executing /init.
    install -d ${D}/dev
    mknod -m 622 ${D}/dev/console c 5 1
}


FILES:${PN} = "/etc /init /dev /sbin"