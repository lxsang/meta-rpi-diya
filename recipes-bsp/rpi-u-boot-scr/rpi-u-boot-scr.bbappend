FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
BOOT_SRC_FILE = "${@bb.utils.contains('ENABLE_USB_U_BOOT', '1', 'usb-boot.cmd.in', 'boot.cmd.in', d)}"
SRC_URI = "file://${BOOT_SRC_FILE}"

do_configure() {
    ([ -e "${WORKDIR}/usb-boot.cmd.in" ] && mv "${WORKDIR}/usb-boot.cmd.in" "${WORKDIR}/boot.cmd.in") || true
}
