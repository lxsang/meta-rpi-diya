DESCRIPTION = "raspi GPIO tools"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# DEPENDS = " "

# code
# SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/RPi-Distro/raspi-gpio.git;protocol=https;branch=master"

# S="${WORKDIR}/git/platforms/unix/config"
# B="${WORKDIR}/build"


inherit autotools
