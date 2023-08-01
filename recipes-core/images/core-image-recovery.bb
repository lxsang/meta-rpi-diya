
DESCRIPTION = "Recovery initramfs image."


PACKAGE_INSTALL = "recovery-boot e2fsprogs kernel-modules  busybox sysvinit sysvinit-inittab ${VIRTUAL-RUNTIME_base-utils} udev base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "${MLPREFIX}recovery"
IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

# Use the same restriction as initramfs-module-install
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*)-(linux.*|freebsd.*)'

IMAGE_PREPROCESS_COMMAND += "image_patch;"

image_patch () {
    # delete unused image
    rm -rf ${IMAGE_ROOTFS}/boot/*
    # setup hostname
cat << 'EOF' >> "${IMAGE_ROOTFS}/etc/hostname"
diyaid
EOF
    # enable autologin
    sed -i 's/options="/&--autologin root /' \
			"${IMAGE_ROOTFS}${base_bindir}/start_getty"
}