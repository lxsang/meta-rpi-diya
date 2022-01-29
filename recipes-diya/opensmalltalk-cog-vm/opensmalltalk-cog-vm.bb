DESCRIPTION = "Pharo VM"
# DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

#FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "libsdl2 mesa freetype"

# pharo 9.0
# SRCREV = "${AUTOREV}"
SRCREV = "27d2d9f0c76b5805ff43578272e809aeb665a498"
SRC_URI = "git://github.com/OpenSmalltalk/opensmalltalk-vm.git;branch=Cog;"
SRC_URI += "file://001-disable-run-test-in-cross-compile.patch"
SRC_URI += "file://svm"

S="${WORKDIR}/git/platforms/unix/config"
B="${WORKDIR}/build"
inherit autotools

TARGET_CFLAGS:raspberrypi += " -D__ARM_ARCH_6__  "
TARGET_CF_GLAGS += " -DDEBUGVM=0 "

EXTRA_OECONF:append="   --with-sysroot=${STAGING_DIR_TARGET} \
                        --without-npsqueak"
EXTRA_OECONF:raspberrypi:append = " --with-src=src/spur32.cog "
EXTRA_OECONF:raspberrypi0-2w-64:append = " --with-src=src/spur64.cog "


INSANE_SKIP += " configure-unsafe "

do_configure() {
    cd ${WORKDIR}/git/ && ./scripts/updateSCCSVersions
    cp ${WORKDIR}/git/building/linux32ARMv6/pharo.cog.spur/plugins.* ${B}/
    cd ${B}
    oe_runconf
}

do_compile() {
    cd ${B}
    mkdir -p ${B}/output
    ROOT=${B}/output make -j 4 install-squeak install-plugins
}

do_install() {
    install -d ${D}/opt/squeak
    install -d ${D}/usr/bin
    install -m 0755 ${B}/output/usr/lib/squeak/* ${D}/opt/squeak
    install -m 0755 ${WORKDIR}/svm ${D}/usr/bin
}
PACKAGES = "${PN} ${PN}-dbg" 
FILES:${PN} += "/opt/squeak/*"