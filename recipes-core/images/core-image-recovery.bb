
DESCRIPTION = "Recovery initramfs image."


PACKAGE_INSTALL = "busybox ${VIRTUAL-RUNTIME_base-utils} udev base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "${MLPREFIX}core-image-recovery"
IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

# Use the same restriction as initramfs-module-install
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*)-(linux.*|freebsd.*)'


python tinyinitrd () {
  # Modify our init file so the user knows we drop to shell prompt on purpose
  newinit = "/bin/busybox sh init"
  with open(d.expand('${IMAGE_ROOTFS}/init'), 'w') as init:
    init.write(newinit)
}

IMAGE_PREPROCESS_COMMAND += "tinyinitrd;"