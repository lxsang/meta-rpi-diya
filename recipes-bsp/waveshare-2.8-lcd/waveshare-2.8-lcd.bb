SUMMARY = "Waveshare 2.8 inch DPI"
DESCRIPTION = "support overlay layer for Waveshare 2.8 inch DPI"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
inherit devicetree

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = " file://dts/ads1115-i2c-gpio.dts "

S = "${WORKDIR}/dts"
DT_FILES = "ads1115-i2c-gpio.dtbo"

COMPATIBLE_MACHINE = "raspberrypi.*"

do_deploy:append () {
    install -d ${DEPLOY_DIR_IMAGE}
    install -m 0755 ${B}/ads1115-i2c-gpio.dtbo ${DEPLOY_DIR_IMAGE}/ads1115-i2c-gpio.dtbo
    install -m 0755 ${THISDIR}/files/vc4-kms-DPI-28inch.dtbo ${DEPLOY_DIR_IMAGE}/vc4-kms-DPI-28inch.dtbo
    install -m 0755 ${THISDIR}/files/waveshare-28dpi-3b-4b.dtbo ${DEPLOY_DIR_IMAGE}/waveshare-28dpi-3b-4b.dtbo
    install -m 0755 ${THISDIR}/files/waveshare-28dpi-3b.dtbo ${DEPLOY_DIR_IMAGE}/waveshare-28dpi-3b.dtbo
    install -m 0755 ${THISDIR}/files/waveshare-28dpi-4b.dtbo ${DEPLOY_DIR_IMAGE}/waveshare-28dpi-4b.dtbo
    # install -m 0755 ${THISDIR}/files/start.mp3 ${DEPLOY_DIR_IMAGE}/start.mp3
}

# addtask deploy