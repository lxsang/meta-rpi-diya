SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "libsdl2"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "file://sdl_test.cpp file://sdlgl.c "
TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
    ${CXX} ${WORKDIR}/sdl_test.cpp `pkg-config --cflags --libs sdl2` -o ${WORKDIR}/sdl_test
    ${CC} ${WORKDIR}/sdlgl.c `pkg-config --cflags --libs sdl2` -lGL -o ${WORKDIR}/sdlgl
}

do_install() {
    install -d ${D}/usr/bin
    install  -m 0755 ${WORKDIR}/sdl_test ${D}/usr/bin
    install  -m 0755 ${WORKDIR}/sdlgl ${D}/usr/bin
    install  -m 0755 ${WORKDIR}/sdl_test /tmp
    install  -m 0755 ${WORKDIR}/sdlgl /tmp
}
