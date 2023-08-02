
DESCRIPTION = "Recovery initramfs image."


PACKAGE_INSTALL = "recovery-boot \
                    dosfstools \
                    e2fsprogs \
                    util-linux-fsck \
                    kernel-modules \
                    busybox \
                    sysvinit \
                    sysvinit-inittab \
                    ${VIRTUAL-RUNTIME_base-utils} \
                    udev \
                    initscripts \
                    base-passwd \
                    ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "${MLPREFIX}recovery"
IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image extrausers

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

EXTRA_USERS_PARAMS = "usermod -p 2Pe/4xyFxsokE root "

# Use the same restriction as initramfs-module-install
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*)-(linux.*|freebsd.*)'

IMAGE_PREPROCESS_COMMAND += "image_patch;"

image_patch () {
    # delete unused image
    rm -rf ${IMAGE_ROOTFS}/boot/*
    # create /etc/fstab
    cat << EOF > ${IMAGE_ROOTFS}/etc/fstab
/dev/mmcblk0p1       /boot          auto       defaults  0  0
/dev/mmcblk0p4       /home          auto       defaults  0  0

EOF
    cat << EOF > ${IMAGE_ROOTFS}/etc/hostname
diya-recovery
EOF
}