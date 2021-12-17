SUMMARY = "Waveshare 2.8 inch DPI"
DESCRIPTION = "support overlay layer for Waveshare 2.8 inch DPI"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

do_deploy () {
    install -m 0755 ${THISDIR}/files/waveshare-28dpi-3b-4b.dtbo ${DEPLOY_DIR_IMAGE}/waveshare-28dpi-3b-4b.dtbo
    install -m 0755 ${THISDIR}/files/waveshare-28dpi-3b.dtbo ${DEPLOY_DIR_IMAGE}/waveshare-28dpi-3b.dtbo
    install -m 0755 ${THISDIR}/files/waveshare-28dpi-4b.dtbo ${DEPLOY_DIR_IMAGE}/waveshare-28dpi-4b.dtbo
    # install -m 0755 ${THISDIR}/files/start.mp3 ${DEPLOY_DIR_IMAGE}/start.mp3
}

addtask deploy