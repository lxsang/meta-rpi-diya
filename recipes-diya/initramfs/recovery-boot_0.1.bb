SUMMARY = "Modular initramfs system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
RDEPENDS:${PN} += "${VIRTUAL-RUNTIME_base-utils}"
RRECOMMENDS:${PN} = "${VIRTUAL-RUNTIME_base-utils-syslog}"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PR = "r4"

inherit allarch  useradd update-rc.d 

SRC_URI = "file://init \
           file://confd \
          "

S = "${WORKDIR}"

USERADD_PACKAGES = "${PN}"
GROUPADD_PACKAGES = "${PN}"

USERADD_PARAM:${PN} = "-u 0 -d /root -r -s /bin/sh root"

INITSCRIPT_NAME = "confd"
INITSCRIPT_PARAMS = "start 30 S ."

do_install() {
    install -d ${D}/init.d

    # base
    install -m 0755 ${WORKDIR}/init ${D}/init

    # udev
    # install -m 0755 ${WORKDIR}/udev ${D}/init.d/01-udev

    # debug
    # install -m 0755 ${WORKDIR}/debug ${D}/init.d/00-debug

    install -m 0755 ${WORKDIR}/confd ${D}/init.d/confd

    # Create device nodes expected by some kernels in initramfs
    # before even executing /init.
    install -d ${D}/dev
    mknod -m 622 ${D}/dev/console c 5 1
}