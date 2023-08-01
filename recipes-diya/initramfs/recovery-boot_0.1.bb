SUMMARY = "Modular initramfs system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
RDEPENDS:${PN} += "${VIRTUAL-RUNTIME_base-utils}"
RRECOMMENDS:${PN} = "${VIRTUAL-RUNTIME_base-utils-syslog}"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PR = "r4"

inherit allarch

SRC_URI = "file://init \
           file://udev \
           file://debug \
          "

S = "${WORKDIR}"

do_install() {
    install -d ${D}/init.d

    # base
    install -m 0755 ${WORKDIR}/init ${D}/init

    # udev
    install -m 0755 ${WORKDIR}/udev ${D}/init.d/01-udev

    # debug
    install -m 0755 ${WORKDIR}/debug ${D}/init.d/00-debug


    # Create device nodes expected by some kernels in initramfs
    # before even executing /init.
    install -d ${D}/dev
    mknod -m 622 ${D}/dev/console c 5 1
}


FILES:${PN} = "/init /init.d /dev"