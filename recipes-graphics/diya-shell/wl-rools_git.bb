SUMMARY = "A modular Wayland compositor library"
DESCRIPTION = "Pluggable, composable, unopinionated modules for building a \
Wayland compositor; or about 50,000 lines of code you were \
going to write anyway."
HOMEPAGE = "https://gitlab.freedesktop.org/wlroots"
BUGTRACKER = "https://gitlab.freedesktop.org/wlroots/wlroots/-/issues"
SECTION = "graphics"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS += " \
	hwdata-native \
	libdrm \
	libxkbcommon \
	libliftoff \
	pixman \
	seatd \
	wayland \
	wayland-native \
	wayland-protocols \
"

PACKAGECONFIG[opengl] = ",,virtual/egl virtual/libgles2"
PACKAGECONFIG[gbm] = ",,virtual/libgbm"
PACKAGECONFIG[libinput] = ",,libinput"
PACKAGECONFIG[systemd] = ",,systemd"
PACKAGECONFIG[sysvinit] = ",,eudev elogind"
PACKAGECONFIG[vulkan] = ",,vulkan-loader vulkan-headers glslang-native"
PACKAGECONFIG[x11] = ",,xcb-util-renderutil"
PACKAGECONFIG[xwayland] = "-Dxwayland=enabled,-Dxwayland=disabled,xwayland xcb-util-wm,xwayland"

PACKAGECONFIG ?= " \
	${@bb.utils.filter('DISTRO_FEATURES', 'systemd sysvinit vulkan x11 xwayland opengl', d)} \
	libinput \
"

SRC_URI = "git://gitlab.freedesktop.org/wlroots/wlroots.git;branch=0.16;protocol=https"
SRCREV = "0a32b5a74db06a27bee55a47205951bb277a9657"

S = "${WORKDIR}/git"
PV = "0.16.2"

inherit meson pkgconfig features_check

EXTRA_OEMESON += "--buildtype release"