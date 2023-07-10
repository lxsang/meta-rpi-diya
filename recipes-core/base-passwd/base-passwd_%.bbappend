
do_patch_ro_roofs() {
    install -d ${D}/var/etc
    install -m 0644 ${D}/etc/passwd ${D}/var/etc/
    install -m 0644 ${D}/etc/group ${D}/var/etc/
    rm ${D}/etc/passwd
    rm ${D}/etc/group
}

addtask patch_ro_roofs after do_populate_sysroot